package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Objects;

import static ru.stqa.pft.mantis.appmanager.SoapHelper.getMantisConnect;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty(
            "browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php",
                "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData bugReport = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        String bugStatus = String.valueOf(bugReport.getStatus().getName());
        if (Objects.equals(bugStatus, "new") | (Objects.equals(bugStatus, "feedback")) |
                (Objects.equals(bugStatus, "acknowledged")) | (Objects.equals(bugStatus, "assigned"))
                | (Objects.equals(bugStatus, "confirmed"))) {
            System.out.println("баг не закрыт");
            return true;
        } else {
            if (Objects.equals(bugStatus, "resolved") | (Objects.equals(bugStatus, "closed"))) {
                System.out.println("баг закрыт");
            }
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
