package ru.stqa.pft.rest.tests.tests;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class BugifyTests extends TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @Test
    public void testOpenBug() {
        boolean iss = isIssueOpen(2109);
        System.out.println(iss);
        Assert.assertTrue(iss);
    }

    @Test
    public void testInProgressBug() {
        boolean iss = isIssueOpen(2173);
        System.out.println(iss);
        Assert.assertTrue(iss);
    }

    @Test
    public void testResolvedBug() {
        boolean iss = isIssueOpen(2125);
        System.out.println(iss);
        Assert.assertFalse(iss);
    }

    @Test
    public void testCloseBug() {
        boolean iss = isIssueOpen(2109);
        System.out.println(iss);
        Assert.assertFalse(iss);
    }

    @Test
    public void testSkipBug() {
        skipIfNotFixed(2178);
        boolean iss = isIssueOpen(2178);
        System.out.println(iss);
    }

}
