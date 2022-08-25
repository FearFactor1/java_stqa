package ru.stqa.pft.rest.tests.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.tests.appmanager.ApplicationManager;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Objects;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty(
            "browser", BrowserType.CHROME));

//    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
//        MantisConnectPortType mc = getMantisConnect();
//        IssueData bugReport = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
//        String bugStatus = String.valueOf(bugReport.getStatus().getName());
//        if (Objects.equals(bugStatus, "new")) {
//            return false;
//        } else {
//            return true;
//        }
//    }

//    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
//        if (isIssueOpen(issueId)) {
//            throw new SkipException("Ignored because of issue " + issueId);
//        }
//    }

}
