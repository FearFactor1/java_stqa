package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroupTests extends TestBase {

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
    public void testContactAddInGroup() throws Exception {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        app.goTo().gotoContactHome();
        app.contact().addContactInGroup(before.iterator().next());
        Contacts after = app.db().contacts();
        Assert.assertTrue(app.contact().checkGroup(after.iterator().next().getId(), groups.iterator().next()));
    }
}
