package com.elliot.avabur.service;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public interface IUtilityService {

    public void login(WebDriver driver, String username, String password) throws InterruptedException;
    
    public WebDriver startGame(String browser) throws MalformedURLException;
    
    public void restartGame(WebDriver driver);
    
    public boolean verifyError(WebDriver driver);
}
