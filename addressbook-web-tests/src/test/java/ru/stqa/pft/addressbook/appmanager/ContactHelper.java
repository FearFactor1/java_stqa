package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
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

    public void fillContactForm(ContactData contactData) {

        List<WebElement> contactValues = wd.findElements(By.xpath("//select[@name='new_group']/option"));
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        if (contactValues.size() == 1) {
            navigationHelper.groupPage();
            groupHelper.create(new GroupData().withName(contactData.getGroup()));
            navigationHelper.contactPage();
        }
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getAllEmail());
        type(By.name("home"), contactData.getAllPhones());

        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "'" + "]")).click();
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        isAlertAccept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            List <WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname));
        }
        return contacts;
    }

}
