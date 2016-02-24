package com.elliot.avabur.service;

import org.openqa.selenium.WebDriver;

public interface IJobService {
    public void startBattle(WebDriver driver, String monster) throws InterruptedException;
    
    public void startHarvesting(WebDriver driver, String harvesting) throws InterruptedException;
    
    public boolean verifyFatigue(WebDriver driver) throws InterruptedException;
    
    public void verifyQuest(WebDriver driver, String quest) throws InterruptedException;
}
