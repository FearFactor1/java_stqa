package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationsTests extends TestBase {

    @Test
    public void testContactCreations() throws Exception {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withLastname("Kozlov").
                withFirstname("Sergey").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                withAllEmail("ferdcvb@yandex.ru").withAllPhones("+79253478354").withGroup("test1")
                .withPhoto(photo);
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(),  equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
