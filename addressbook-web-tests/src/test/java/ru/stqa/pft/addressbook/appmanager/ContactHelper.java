package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

        if (creation) {
            String contactValue = wd.findElement(By.name("new_group")).getAttribute("value");
            List<WebElement> contactValues = wd.findElements(By.xpath("//select[@name='new_group']/option"));
            navigationHelper = new NavigationHelper(wd);
            groupHelper = new GroupHelper(wd);
            if (contactValues.size() <= 1) {
                navigationHelper.gotoGroupPage();
                groupHelper.createGroup(new GroupData("test1", null, null));
                createContact(new ContactData(
                        "Kozlov", "Sergey", "Г. Саратов, ул. Озёрная, д.45, кв. 23",
                        "ferdcvb@yandex.ru", "+79253478354", "test1"), true);
            } else {
                type(By.name("lastname"), contactData.getLastname());
                type(By.name("firstname"), contactData.getFirstname());
                type(By.name("address"), contactData.getAddress());
                type(By.name("email"), contactData.getAllEmail());
                type(By.name("home"), contactData.getAllPhones());

                //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
                //Assert.assertFalse(isElementPresent(By.name("new_group")));
                submitContactCreation();
                returnToContactPage();
            }

        }


    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String[] firstname = element.getText().split(" ");
            String[] lastname = element.getText().split(" ");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(
                    id, lastname[0], firstname[1], null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

}
