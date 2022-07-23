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

    public void fillContactForm(ContactData contactData) {

        List<WebElement> contactValues = wd.findElements(By.xpath("//select[@name='new_group']/option"));
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        if (contactValues.size() == 1) {
            navigationHelper.gotoGroupPage();
            groupHelper.createGroup(new GroupData("test1", null, null));
            navigationHelper.gotoContactPage();
        }
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getAllEmail());
        type(By.name("home"), contactData.getAllPhones());

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

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            List <WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(
                    id, lastname, firstname, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

}
