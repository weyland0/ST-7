package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            String password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("#resultid .verybigtext b"))).getText();
            System.out.println("Задание 1 - сгенерированный пароль: " + password);

            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
