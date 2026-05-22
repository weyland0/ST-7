package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class App {

    private static final String PASSWORD_GENERATOR_URL =
            "https://www.calculator.net/password-generator.html";
    private static final By PASSWORD_RESULT_SELECTOR =
            By.cssSelector("#resultid .verybigtext b");
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(20);

    private App() {
    }

    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get(PASSWORD_GENERATOR_URL);

            WebDriverWait wait = new WebDriverWait(webDriver, WAIT_TIMEOUT);
            String password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(PASSWORD_RESULT_SELECTOR)).getText();

            System.out.println("1) полученный пароль: " + password);

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
