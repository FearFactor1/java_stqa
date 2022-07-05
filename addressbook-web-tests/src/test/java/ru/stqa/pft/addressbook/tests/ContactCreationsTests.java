package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationsTests extends TestBase {

    @Test
    public void testContactCreations() throws Exception {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData(
                "Petrov", "Vasek", "Г. Саратов, ул. Озёрная, д.45, кв. 23",
                "ferdcvb@yandex.ru", "+79253478354"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }


}
