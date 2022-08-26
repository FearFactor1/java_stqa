package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.random;
import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    @Test
    public void testPasswordChange() throws IOException, MessagingException, InterruptedException {
        app.mail().start();
        app.passwordChange().start("administrator", "root");
        app.passwordChange().resetPassword("adm");
        long now = System.currentTimeMillis();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
        String confirmationLink = app.passwordChange().findConfLink(mailMessages, "adm@localhost");
        String password = "adm" + random();
        app.passwordChange().changePassInEmailForm("adm",
                confirmationLink, password);
        assertTrue(app.newSession().login("adm", password));
    }

}


