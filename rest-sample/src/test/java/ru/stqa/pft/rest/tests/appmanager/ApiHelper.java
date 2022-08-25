package ru.stqa.pft.rest.tests.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;

import java.io.IOException;

public class ApiHelper {

    private ApplicationManager app;

    public ApiHelper(ApplicationManager app) {
        this.app = app;
    }

    public void getIssues() throws IOException {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        //return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }
}
