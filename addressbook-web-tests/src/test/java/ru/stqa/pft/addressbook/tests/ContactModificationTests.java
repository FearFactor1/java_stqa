package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData(
                "Petrov", "Vasek", "Г. Саратов, ул. Озёрная, д.45, кв. 23",
                "ferdcvb@yandex.ru", "+79253478354"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();

    }
}