package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMainPageTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsMainPage() throws IOException {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Petrov").
                    withFirstname("Vasek").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withAllEmail2("vaxa@mail.ru").
                    withAllEmail3("vautsiy@rambler.ru").withAllPhones("+79253478354").
                    withMobilePhone("+79245645246").withWorkPhone("94578902"));
        }
    }

    @Test
    public void testContactMainPage() throws Exception {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), Matchers.equalTo(mergeAddress(contactInfoFromEditForm)));
        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
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

    private String mergeAddress(ContactData contact) {
        return Collections.singletonList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactMainPageTests::cleanedAddress)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedAddress(String address) {
        return address.replaceAll("\\s*,\\s*$", "");
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
