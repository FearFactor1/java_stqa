package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;

import static ru.stqa.pft.mantis.tests.TestBase.app;

public class PasswordChangeTests {

    @Test
    public void testPasswordChange() throws IOException {
        app.passwordChange().start("administrator", "root");
    }
}
