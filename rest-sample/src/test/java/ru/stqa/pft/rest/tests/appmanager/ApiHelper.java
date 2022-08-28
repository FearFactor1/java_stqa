package ru.stqa.pft.rest.tests.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.tests.model.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiHelper {

    private ApplicationManager app;

    public ApiHelper(ApplicationManager app) {
        this.app = app;
    }
    
    public Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get(app.getProperty("api.issues")).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return  new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "").
                execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
