package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactHome();
        app.getGroupHelper().selectContact();
        app.getGroupHelper().initContactModification();
        app.getGroupHelper().fillContactForm(new ContactData(
                "Petrov", "Vasek", "Г. Саратов, ул. Озёрная, д.45, кв. 23",
                "ferdcvb@yandex.ru", "+79253478354"));
        app.getGroupHelper().submitContactModification();
        app.getGroupHelper().returnToContactPage();
        app.getSessionHelper().logout();

    }
}
