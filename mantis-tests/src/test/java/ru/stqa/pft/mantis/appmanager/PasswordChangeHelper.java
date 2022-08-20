package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import javax.mail.MessagingException;

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

    public void resetPassword(String username) throws MessagingException, InterruptedException {
        wd.findElement(By.xpath("//span[contains(text(), ' Управление ')]")).click();
        click(By.linkText("Управление пользователями"));
        click(By.linkText(username));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }
}
