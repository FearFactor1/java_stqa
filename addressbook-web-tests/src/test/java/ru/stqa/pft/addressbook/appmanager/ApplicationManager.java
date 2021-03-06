package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.time.Duration;
import java.util.Objects;

public class ApplicationManager {

    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private HelperBase helperBase;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
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
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
        helperBase = new HelperBase(wd);
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public HelperBase getHelperBase() {
        return helperBase;
    }


}
