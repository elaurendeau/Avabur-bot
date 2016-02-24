package com.elliot.avabur.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.elliot.avabur.constant.GameConstant;

public class WebDriverUtil {

    private static final Logger logger = Logger.getLogger(WebDriverUtil.class);

    public static boolean clickById(WebDriver driver, String id) {
        boolean value = false;

        WebElement element = searchElementVisibleById(driver, id);
        if (element != null) {
            element.click();
            value = true;
        }

        return value;
    }

    public static boolean sendKeysById(WebDriver driver, String id, String keys) {
        boolean value = false;

        WebElement element = searchElementVisibleById(driver, id);
        if (element != null) {
            element.sendKeys(keys);
            value = true;
        }

        return value;
    }

    public static boolean clickByClassName(WebDriver driver, String className) {
        boolean value = false;

        WebElement element = searchElementVisibleByClass(driver, className);
        if (element != null) {
            element.click();
            value = true;
        }

        return value;
    }

    public static boolean clickByXPath(WebDriver driver, String xpath) {
        boolean value = false;

        WebElement element = searchElementVisibleByXPath(driver, xpath);
        if (element != null) {
            element.click();
            value = true;
        }

        return value;
    }

    public static boolean sendKeysByClassName(WebDriver driver, String className, String keys) {
        boolean value = false;

        WebElement element = searchElementVisibleByClass(driver, className);
        if (element != null) {
            element.sendKeys(keys);
            value = true;
        }

        return value;
    }

    public static WebElement searchElementVisibleById(WebDriver driver, String id, int waitTime) {
        return searchElementVisibleByBy(driver, By.id(id), waitTime);
    }
    
    public static WebElement searchElementVisibleById(WebDriver driver, String id) {
        return searchElementVisibleByBy(driver, By.id(id), GameConstant.DEFAULT_ELEMENT_WAIT_TIME);
    }

    public static WebElement searchElementVisibleByXPath(WebDriver driver, String xPath) {
        return searchElementVisibleByBy(driver, By.xpath(xPath), GameConstant.DEFAULT_ELEMENT_WAIT_TIME);
    }

    public static WebElement searchElementVisibleByClass(WebDriver driver, String className) {
        return searchElementVisibleByBy(driver, By.className(className), GameConstant.DEFAULT_ELEMENT_WAIT_TIME);
    }

    public static WebElement searchElementVisibleByClass(WebDriver driver, String className, int waitTime) {
        return searchElementVisibleByBy(driver, By.className(className), waitTime);
    }
    public static WebElement searchElementVisibleByCssSelector(WebDriver driver, String cssSelector) {
        return searchElementVisibleByBy(driver, By.cssSelector(cssSelector), GameConstant.DEFAULT_ELEMENT_WAIT_TIME);
    }
    
    private static WebElement searchElementVisibleByBy(WebDriver driver, By by, int waitTime) {
        WebElement element = null;

        try {
            WebDriverWait driverWait = (new WebDriverWait(driver, waitTime));

            List<WebElement> elements = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

            for(WebElement anElement : elements) {
                if (anElement != null && anElement.isDisplayed()) {
                    element = anElement;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return element;
    }

}
