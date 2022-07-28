package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Petrov").
                    withFirstname("Vasek").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withAllPhones("+79253478354").
                    withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones() throws Exception {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Collections.singletonList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String address) {
        return address.replaceAll("\\s*,\\s*$", "");
    }


}
