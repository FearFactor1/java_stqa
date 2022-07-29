package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMainPageTests extends TestBase {

    public void ensurePreconditionsPhone() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Petrov").
                    withFirstname("Vasek").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withAllPhones("+79253478354").
                    withGroup("test1").withMobilePhone("+79245645246").withWorkPhone("94578902"));
        }
    }

    @Test
    public void testContactPhones() throws Exception {
        ensurePreconditionsPhone();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactMainPageTests::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public void ensurePreconditionsAddress() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Petrov").
                    withFirstname("Vasek").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withAllPhones("+79253478354").
                    withGroup("test1"));
        }
    }

    @Test
    public void testContactAddress() throws Exception {
        ensurePreconditionsAddress();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

        assertThat(contact.getAddress(), Matchers.equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Collections.singletonList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactMainPageTests::cleanedAddress)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedAddress(String address) {
        return address.replaceAll("\\s*,\\s*$", "");
    }

    public void ensurePreconditionsEmail() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Petrov").
                    withFirstname("Vasek").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23")
                    .withAllPhones("+79253478354")
                    .withGroup("test1").withAllEmail("ferdcvb@yandex.ru")
                    .withAllEmail2("vaxa@mail.ru").withAllEmail3("vautsiy@rambler.ru"));
        }
    }

    @Test
    public void testContactEmails() throws Exception {
        ensurePreconditionsEmail();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getAllEmail(), contact.getAllEmail2(), contact.getAllEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactMainPageTests::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedEmail(String email) {
        return email.replaceAll("\\s", "");
    }
}
