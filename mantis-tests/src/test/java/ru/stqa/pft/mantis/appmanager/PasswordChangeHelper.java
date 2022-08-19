package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordChangeHelper extends HelperBase {

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Вход']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Вход']"));
    }
}
