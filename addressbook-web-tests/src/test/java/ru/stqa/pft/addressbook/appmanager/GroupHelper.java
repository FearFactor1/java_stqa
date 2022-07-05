package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getAllEmail());
        type(By.name("home"), contactData.getAllPhones());
    }

    private void surname(ContactData contactData) {
        click(By.name("lastname"));
        clear(By.name("lastname"));
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }
}
