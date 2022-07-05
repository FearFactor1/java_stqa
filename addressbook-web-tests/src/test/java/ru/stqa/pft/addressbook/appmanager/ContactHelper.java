package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

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
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getAllEmail());
        type(By.name("home"), contactData.getAllPhones());
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
}
