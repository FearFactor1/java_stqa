package ru.stqa.pft.rest.tests.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class BugifyTests extends TestBase {

    @Test
    public void testBugify() throws IOException {
        app.api().getIssues();
    }

}
