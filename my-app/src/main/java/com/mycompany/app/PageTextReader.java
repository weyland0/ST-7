package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

final class PageTextReader {

    private PageTextReader() {
    }

    static String read(WebDriver webDriver) {
        WebElement elem;
        try {
            elem = webDriver.findElement(By.tagName("pre"));
        } catch (NoSuchElementException e) {
            elem = webDriver.findElement(By.tagName("body"));
        }
        return elem.getText();
    }
}
