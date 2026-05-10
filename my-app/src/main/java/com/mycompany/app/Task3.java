package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task3 {

    private static final String METEO_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain"
                    + "&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(METEO_URL);
        WebElement elem;
        try {
            elem = webDriver.findElement(By.tagName("pre"));
        } catch (NoSuchElementException e) {
            elem = webDriver.findElement(By.tagName("body"));
        }
        String jsonStr = elem.getText();
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonStr);
        JSONObject hourly = (JSONObject) root.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temps = (JSONArray) hourly.get("temperature_2m");
        JSONArray rains = (JSONArray) hourly.get("rain");

        StringBuilder sb = new StringBuilder();
        sb.append("| № | Дата/время | Температура | Осадки (мм) |\n");
        sb.append("| -- | ------------- | ----------- | ------------ |\n");

        System.out.println("Задание 3 - прогноз погоды (Нижний Новгород, сутки)");
        System.out.print(sb);

        for (int i = 0; i < times.size(); i++) {
            String row = String.format("| %d | %s | %s | %s |\n",
                    i + 1,
                    times.get(i),
                    temps.get(i),
                    rains.get(i));
            System.out.print(row);
            sb.append(row);
        }

        Files.createDirectories(Paths.get("result"));
        Files.write(Paths.get("result", "forecast.txt"), sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
