package ru.stqa.pft.rest.tests.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;

    private String browser;
    private ApiHelper apiHelper;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void setUp() throws Exception {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public ApiHelper api() {
        if (apiHelper == null) {
            apiHelper = new ApiHelper(this);
        }
        return apiHelper;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


}
