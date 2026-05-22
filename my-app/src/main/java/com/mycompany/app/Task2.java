package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

public final class Task2 {

    private static final String IPIFY_URL = "https://api.ipify.org/?format=json";

    private Task2() {
    }

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(IPIFY_URL);

        String jsonStr = PageTextReader.read(webDriver);
        JSONObject obj = (JSONObject) new JSONParser().parse(jsonStr);
        String ip = (String) obj.get("ip");

        System.out.println("2) IP-адрес: " + ip);
    }
}
