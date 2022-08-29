package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;
    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private HelperBase helperBase;
    private ContactHelper contactHelper;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load((new FileReader(new File(String.format("src/test/resources/%s.properties", target)))));

        dbHelper = new DbHelper();

        if ("".equals(properties.getProperty("selenium.server"))) {
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
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
        helperBase = new HelperBase(wd);
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }


    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public HelperBase getHelperBase() {
        return helperBase;
    }

    public DbHelper db() {
        return dbHelper;
    }


}
