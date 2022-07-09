package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoContactHome();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                    "Petrov", "Vasek", "Г. Саратов, ул. Озёрная, д.45, кв. 23",
                    "ferdcvb@yandex.ru", "+79253478354", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getHelperBase().isAlertAccept();
        app.getSessionHelper().logout();
    }

}
