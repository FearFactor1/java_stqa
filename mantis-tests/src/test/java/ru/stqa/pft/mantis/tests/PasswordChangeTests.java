package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class PasswordChangeTests extends TestBase {

    @Test
    public void testPasswordChange() throws IOException, MessagingException, InterruptedException {
        app.passwordChange().start("administrator", "root");
        app.passwordChange().resetPassword("adm");
        long now = System.currentTimeMillis();
        //String user = String.format("user%s", now);
        String password = "password";
        List<MailMessage> mailMessages = app.james().waitForMail("adm", password, 919167000);
        System.out.println(mailMessages);
    }
}
