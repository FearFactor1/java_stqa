package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Kozlov").
                    withFirstname("Sergey").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withHomePhone("+79253478354"));
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after,  equalTo(before.without(deletedContact)));
    }

}
