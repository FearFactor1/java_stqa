package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getAllEmail());
        type(By.name("home"), contactData.getAllPhones());

        if (creation) {
            String groupValue = wd.findElement(By.name("new_group")).getAttribute("value");
            List<WebElement> groupValues = wd.findElements(By.xpath("//select[@name='new_group']/option"));
            navigationHelper = new NavigationHelper(wd);
            groupHelper = new GroupHelper(wd);
            if (groupValues.size() <= 1) {
                navigationHelper.gotoGroupPage();
                groupHelper.createGroup(new GroupData("test1", null, null));
                createContact(new ContactData(
                        "Petrov", "Vasek", "Г. Саратов, ул. Озёрная, д.45, кв. 23",
                        "ferdcvb@yandex.ru", "+79253478354", "test1"), true);
            } else {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
                //Assert.assertFalse(isElementPresent(By.name("new_group")));
                submitContactCreation();
                returnToContactPage();
            }


        }

    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, true);
        //submitContactCreation();
        //returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

}
