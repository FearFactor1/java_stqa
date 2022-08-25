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
    private WebDriver wd;

    private String browser;
    private ApiHelper apiHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public WebDriver getDriver() {
        if(wd == null) {
            if (Objects.equals(browser, BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "F:\\IdeaProjects\\java_stqa\\chromedriver.exe");
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "F:\\IdeaProjects\\java_stqa\\geckodriver.exe");
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver", "F:\\IdeaProjects\\java_stqa\\IEDriverServer.exe");
                wd = new InternetExplorerDriver();
            }
            wd.get(properties.getProperty("web.baseUrl"));
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
        return wd;
    }

    public ApiHelper api() {
        if (apiHelper == null) {
            apiHelper = new ApiHelper(this);
        }
        return apiHelper;
    }

}
