package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Task3 {

    private static final String METEO_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain"
                    + "&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
    private static final String FORECAST_TITLE =
            "3) прогноз погоды (Нижний Новгород, сутки)";
    private static final String FORECAST_HEADER =
            "| № | Дата/время | Температура | Осадки (мм) |\n"
                    + "| -- | ------------- | ----------- | ------------ |\n";

    private Task3() {
    }

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(METEO_URL);

        String jsonStr = PageTextReader.read(webDriver);
        JSONObject root = (JSONObject) new JSONParser().parse(jsonStr);
        JSONObject hourly = (JSONObject) root.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temps = (JSONArray) hourly.get("temperature_2m");
        JSONArray rains = (JSONArray) hourly.get("rain");

        StringBuilder forecastTable = new StringBuilder();
        forecastTable.append(FORECAST_HEADER);

        System.out.println(FORECAST_TITLE);
        System.out.print(forecastTable);

        for (int i = 0; i < times.size(); i++) {
            String row = formatForecastRow(i, times, temps, rains);
            System.out.print(row);
            forecastTable.append(row);
        }

        Files.createDirectories(Paths.get("result"));
        Files.writeString(Paths.get("result", "forecast.txt"), forecastTable.toString(), StandardCharsets.UTF_8);
    }

    private static String formatForecastRow(int index, JSONArray times, JSONArray temps, JSONArray rains) {
        return String.format("| %d | %s | %s | %s |\n",
                index + 1,
                times.get(index),
                temps.get(index),
                rains.get(index));
    }
}
