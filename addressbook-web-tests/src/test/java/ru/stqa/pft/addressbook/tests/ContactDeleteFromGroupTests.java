package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withLastname("Kozlov").
                    withFirstname("Sergey").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withHomePhone("+79253478354"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactDeleteFromGroup() throws Exception {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().gotoContactHome();
        app.contact().deleteFromGroup(before.iterator().next(), groups.iterator().next());
        Contacts after = app.db().contacts();
        Assert.assertTrue(app.contact().checkDeleteGroup(after.iterator().next().getId()));
    }
}
