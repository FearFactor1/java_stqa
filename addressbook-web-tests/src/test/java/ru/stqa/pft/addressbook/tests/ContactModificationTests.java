package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withLastname("Kozlov").
                    withFirstname("Sergey").withAddress("г. Клин, ул. Большая, кв. 45, д. 8").
                    withAllEmail("gytrvbn.mail.ru").withAllPhones("89452367354").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
//        app.contact().selectContact(before.size() - 1);
//        app.contact().initContactModification(before.size() - 1);
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Kozlov").
                withFirstname("Sergey").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                withAllEmail("ferdcvb@yandex.ru").withAllPhones("+79253478354").withGroup("test1");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after,  equalTo(before.without(modifiedContact).withAdded(contact)));

    }

}
