package com.elliot.avabur.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.elliot.avabur.constant.GameConstant;
import com.elliot.avabur.service.IJobService;
import com.elliot.avabur.util.WebDriverUtil;

public class JobServiceImpl implements IJobService {
    
    private static final Logger logger = Logger.getLogger(JobServiceImpl.class);

    @Override
    public void startBattle(WebDriver driver, String monster) throws InterruptedException {
        
        logger.info("Battle versus [" + monster + "]");
        
        if(WebDriverUtil.searchElementVisibleById(driver, "basePage") != null ) {
            WebDriverUtil.clickById(driver, "basePage");
            
            Thread.sleep(GameConstant.SLEEP_TIME);
            
            if(WebDriverUtil.searchElementVisibleById(driver, "loadMobList") != null ) {
                WebDriverUtil.clickById(driver, "loadMobList");
                
                Thread.sleep(GameConstant.SLEEP_TIME);
                
                if(WebDriverUtil.searchElementVisibleById(driver, "enemyList") != null ) {
                    WebDriverUtil.sendKeysById(driver, "enemyList", monster);
                    Thread.sleep(GameConstant.SLEEP_TIME);
                    WebDriverUtil.clickById(driver, "autoEnemy");
                }
            }
        }
    }

    @Override
    public boolean verifyFatigue(WebDriver driver) throws InterruptedException {
       
        boolean fatigue = false;
        
        if(WebDriverUtil.searchElementVisibleByClass(driver, "fatigueWrapper") != null) {
            fatigue = true;
        }
        
        return fatigue;
    }

    @Override
    public void verifyQuest(WebDriver driver, String quest) throws InterruptedException {
        
        if(WebDriverUtil.searchElementVisibleByClass(driver, "questCenter", 1) != null) {
            WebDriverUtil.clickByClassName(driver, "questCenter");
            
            Thread.sleep(GameConstant.SLEEP_TIME);
            
            if(WebDriverUtil.searchElementVisibleByClass(driver, "completeQuest") != null ) {
                WebDriverUtil.clickByClassName(driver, "completeQuest");
                
                logger.info("Picking up new quest " + quest);
                
               
            }
            
            Thread.sleep(GameConstant.SLEEP_TIME);
            
            if(WebDriverUtil.searchElementVisibleById(driver, "quest_enemy_list") != null ) {
                WebDriverUtil.sendKeysById(driver, "quest_enemy_list", quest);
                
                Thread.sleep(GameConstant.SLEEP_TIME);
                WebDriverUtil.clickByXPath(driver, "//input[@data-questtype='kill']");
            } else if(WebDriverUtil.searchElementVisibleByCssSelector(driver, ".questButton.active") != null ) {
                WebDriverUtil.clickByXPath(driver, "//input[@data-questtype='tradeskill']");
            }
            
            Thread.sleep(GameConstant.SLEEP_TIME);
            WebDriverUtil.clickByClassName(driver, "closeModal");
        }
        
        
        
    }

    @Override
    public void startHarvesting(WebDriver driver, String harvesting) throws InterruptedException {
        logger.info("Harvesting [" + harvesting + "]");
        
        if(WebDriverUtil.clickById(driver, "basePage")) {
            
            Thread.sleep(GameConstant.SLEEP_TIME);
            
            if(harvesting.equals("Fish")) {
                WebDriverUtil.clickById(driver, "loadFishing");
            } else if(harvesting.equals("Wood")) {
                WebDriverUtil.clickById(driver, "loadWoodcutting");
            } else if(harvesting.equals("Iron")) {
                WebDriverUtil.clickById(driver, "loadMining");
            } else {
                WebDriverUtil.clickById(driver, "loadStonecutting");
            }
        }
    }

}
