package ru.stqa.pft.rest.tests.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.tests.appmanager.ApplicationManager;

import ru.stqa.pft.rest.tests.model.Issue;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Set;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    private Properties properties;

    public boolean isIssueOpen(int issueId) {
        String json = RestAssured.get(("https://bugify.stqa.ru/api/" + "issues/" + issueId + ".json")).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> currentIssues = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
        for (Issue currentIssue : currentIssues) {
            if (currentIssue.getStateName().equals("Open") | currentIssue.getStateName().equals("In Progress")) {
                System.out.println("баг не закрыт");
                return true;
            } else {
                if (currentIssue.getStateName().equals("Closed") | currentIssue.getStateName().equals("Resolved")) {
                    System.out.println("баг закрыт");
                }
            }
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
