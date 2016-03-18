package com.elliot.avabur.application;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;

import com.elliot.avabur.constant.GameConstant;
import com.elliot.avabur.service.ICaptchaService;
import com.elliot.avabur.service.IJobService;
import com.elliot.avabur.service.IUtilityService;
import com.elliot.avabur.service.impl.CaptchaServiceImpl;
import com.elliot.avabur.service.impl.JobServiceImpl;
import com.elliot.avabur.service.impl.UtilityServiceImpl;

public class Application implements Runnable {

    private static final Logger logger = Logger.getLogger(Application.class);

    private String username;
    private String password;

    private String quest;
    private String monster;
    private String harvesting;

    private String browser;

    private IJobService jobService;
    private IUtilityService utilityService;
    private ICaptchaService captchaService;

    private boolean isRunning;

    private boolean requestReset;
    private boolean loggedIn;
    private boolean isCombat;
    private boolean autoSwitchMode;

    private WebDriver driver;

    public Application() {
        requestReset = false;
        loggedIn = false;
        jobService = new JobServiceImpl();
        utilityService = new UtilityServiceImpl();
        captchaService = new CaptchaServiceImpl();

    }

    @Override
    public void run() {

        isRunning = true;

        try {

            if (!loggedIn) {
                driver = utilityService.startGame(browser);
                utilityService.login(driver, username, password);
                loggedIn = true;
            } else {
                utilityService.restartGame(driver);
            }

            jobService.verifyQuest(driver, quest);
            if (isCombat) {
                jobService.startBattle(driver, monster);
            } else {
                jobService.startHarvesting(driver, harvesting);
            }

            int count = 0;

            while (isRunning) {

                captchaService.checkCaptcha(driver);

                if (count > 180) {
                    utilityService.restartGame(driver);
                    Thread.sleep(GameConstant.SLEEP_TIME);
                    
                    if(autoSwitchMode) {
                        isCombat = !isCombat;
                    }
                    
                    if (isCombat) {
                        jobService.startBattle(driver, monster);
                    } else {
                        jobService.startHarvesting(driver, harvesting);
                    }
                    count = 0;
                }

                if (utilityService.verifyError(driver) || jobService.verifyFatigue(driver) || count % 30 == 0 || requestReset) {
                    int sleepTime = ThreadLocalRandom.current().nextInt(2000, 15000 + 1);
                    logger.info("Combat reset in " + sleepTime / 1000 + " seconds");
                    Thread.sleep(sleepTime);

                    try {
                        jobService.verifyQuest(driver, quest);
                        Thread.sleep(GameConstant.SLEEP_TIME);
                        if (isCombat) {
                            jobService.startBattle(driver, monster);
                        } else {
                            jobService.startHarvesting(driver, harvesting);
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();

                    }

                    requestReset = false;
                }
                Thread.sleep(5000);
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop() {

        isRunning = false;

        WindowsUtils.killByName("chromedriver.exe");

        if (driver != null) {
            driver.close();
        }
    }

    public void requestReset() {
        requestReset = true;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the quest
     */
    public String getQuest() {
        return quest;
    }

    /**
     * @param quest the quest to set
     */
    public void setQuest(String quest) {
        this.quest = quest;
    }

    /**
     * @return the monster
     */
    public String getMonster() {
        return monster;
    }

    /**
     * @param monster the monster to set
     */
    public void setMonster(String monster) {
        this.monster = monster;
    }

    public String getHarvesting() {
        return harvesting;
    }

    public void setHarvesting(String harvesting) {
        this.harvesting = harvesting;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public IJobService getCombatService() {
        return jobService;
    }

    public void setCombatService(IJobService combatService) {
        this.jobService = combatService;
    }

    public IUtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(IUtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public ICaptchaService getCaptchaService() {
        return captchaService;
    }

    public void setCaptchaService(ICaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRequestReset() {
        return requestReset;
    }

    public void setRequestReset(boolean requestReset) {
        this.requestReset = requestReset;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isCombat() {
        return isCombat;
    }

    public void setCombat(boolean isCombat) {
        this.isCombat = isCombat;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAutoSwitchMode() {
        return autoSwitchMode;
    }

    public void setAutoSwitchMode(boolean autoSwitchMode) {
        this.autoSwitchMode = autoSwitchMode;
    }

}
