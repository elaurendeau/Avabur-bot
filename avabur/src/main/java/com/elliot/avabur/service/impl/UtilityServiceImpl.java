package com.elliot.avabur.service.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.elliot.avabur.constant.GameConstant;
import com.elliot.avabur.service.IUtilityService;
import com.elliot.avabur.util.WebDriverUtil;

public class UtilityServiceImpl implements IUtilityService {

    private static final Logger logger = Logger.getLogger(UtilityServiceImpl.class);

    @Override
    public void login(WebDriver driver, String username, String password) throws InterruptedException {
        if (WebDriverUtil.searchElementVisibleById(driver, "acctname") != null
                && WebDriverUtil.searchElementVisibleById(driver, "password")  != null 
                && WebDriverUtil.searchElementVisibleById(driver, "login")  != null) {

            logger.info("Logging in " + username);
            WebDriverUtil.sendKeysById(driver, "acctname", username);
            Thread.sleep(GameConstant.SLEEP_TIME);
            WebDriverUtil.sendKeysById(driver, "password", password);
            Thread.sleep(GameConstant.SLEEP_TIME);
            WebDriverUtil.clickById(driver, "login");
        }

    }

    @Override
    public WebDriver startGame(String browser) throws MalformedURLException {

        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
        } else if (browser.equals("Internet Explorer")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/driver/IEDriverServer.exe");
        }

        logger.info("Starting Avabur");

        WebDriver driver;

        driver = new ChromeDriver();

        driver.get("https://www.avabur.com/index.php");
        

        return driver;
    }

    @Override
    public void restartGame(WebDriver driver) {
        driver.get("https://www.avabur.com/game.php");
    }

    @Override
    public boolean verifyError(WebDriver driver) {
        boolean hasError = false;
        if (WebDriverUtil.searchElementVisibleById(driver, "confirmOverlay" ,1) != null) {
            logger.info("Closing error popup");
            hasError = WebDriverUtil.clickById(driver, "confirmButtons");
        }
    
        return hasError;
    }
}
