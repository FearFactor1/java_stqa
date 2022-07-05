package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private HelperBase helperBase;

    public void init() {
        System.setProperty("webdriver.chrome.driver", "F:\\IdeaProjects\\java_stqa\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
        helperBase = new HelperBase(wd);
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void stop() {
        wd.quit();
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
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
