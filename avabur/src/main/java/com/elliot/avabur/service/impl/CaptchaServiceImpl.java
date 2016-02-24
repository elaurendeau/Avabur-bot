package com.elliot.avabur.service.impl;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elliot.avabur.constant.GameConstant;
import com.elliot.avabur.domain.Captcha;
import com.elliot.avabur.service.ICaptchaService;
import com.elliot.avabur.util.WebDriverUtil;

import ru.fourqube.antigate.AntigateClient;
import ru.fourqube.antigate.AntigateClientBuilder;
import ru.fourqube.antigate.CaptchaStatus;

public class CaptchaServiceImpl implements ICaptchaService {
    
    AntigateClient client;
    private static final Logger logger = Logger.getLogger(CaptchaServiceImpl.class);
    
    public CaptchaServiceImpl() {
        client = AntigateClientBuilder.create().setKey("035539e105d45e64e66571ef7c73a918").build();
    }

    @Override
    public void checkCaptcha(WebDriver driver) {
        
        try {
            if(WebDriverUtil.searchElementVisibleById(driver, "recaptcha_challenge_image", 1)  != null) {
                
                logger.info("Captcha test detected. " + client.getBalance() + " left");
                
                Thread.sleep(GameConstant.SLEEP_TIME);
                String url = WebDriverUtil.searchElementVisibleById(driver, "recaptcha_challenge_image").getAttribute("src");
             
                Captcha captcha = getCaptchaByUrl(url);
                
                logger.info("Captcha awnser: " + captcha.getValue());
                
                Thread.sleep(GameConstant.SLEEP_TIME);
                
                WebDriverUtil.sendKeysById(driver, "recaptcha_response_field", captcha.getValue());
                
                Thread.sleep(GameConstant.SLEEP_TIME);
                
                WebDriverUtil.clickById(driver, "captcha_submit");
                
                Thread.sleep(GameConstant.SLEEP_TIME);
                
                if(WebDriverUtil.searchElementVisibleById(driver, "captcha_explanation") != null && driver.findElement(By.id("captcha_explanation")).getText().equals(GameConstant.INVALID_CAPTCHA)) {
                    logger.info("Invalid captcha awnser");
                    
                    client.reportBad(captcha.getId());
                } else {
                    WebDriverUtil.clickById(driver, "captchaRewardCollect");
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private Captcha getCaptchaByUrl(String url) throws Exception {

        Captcha captcha = new Captcha();

        captcha.setId(client.upload(new URL(url)));
        int count = 0;
        CaptchaStatus cs;
        do {
            
            if(count > 60) {
                throw new Exception("Unable to get captcha ready");
            }
            
            Thread.sleep(GameConstant.SLEEP_TIME);
            cs = client.checkStatus(captcha.getId());
            count++;
        } while(cs != null && !cs.isReady());
        
        captcha.setValue(cs.getText());

        return captcha;
    }

}