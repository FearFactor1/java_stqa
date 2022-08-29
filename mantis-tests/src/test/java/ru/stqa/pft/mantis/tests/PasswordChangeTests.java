package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.lang.Math.random;
import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        app.mail().start();
        if ( app.db().users().size()==0) {
            long now = System.currentTimeMillis();
            String mail = String.format("user%s@localhost.localadmin", now);
            String userName = String.format("user%s", now);
            String password = "password";
            app.registration().start(userName, mail);
            List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
            String confirmationLink = app.passwordChange().findConfLink(mailMessages, mail);
            app.registration().finish(confirmationLink, password);
        }
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException, InterruptedException {
        app.passwordChange().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Users allUser = app.db().users();
        UserData user = allUser.iterator().next();
        app.passwordChange().resetPassword(String.valueOf(user));
        long now = System.currentTimeMillis();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
        String confirmationLink = app.passwordChange().findConfLink(mailMessages, String.valueOf(user));
        String password = String.valueOf(random());
        app.passwordChange().changePassInEmailForm(String.valueOf(user),
                confirmationLink, password);
        assertTrue(app.newSession().login(String.valueOf(user), password));
    }

}


