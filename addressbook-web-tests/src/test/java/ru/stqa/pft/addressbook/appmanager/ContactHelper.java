package ru.stqa.pft.addressbook.appmanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public boolean fillContactForm(ContactData contactData) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(
                "src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());

            List<WebElement> contactValues = wd.findElements(By.xpath("//select[@name='new_group']/option"));
            navigationHelper = new NavigationHelper(wd);
            groupHelper = new GroupHelper(wd);
            if (contactValues.size() == 1) {
                navigationHelper.groupPage();
                groupHelper.create(groups.get(0));
                navigationHelper.contactPage();
            }

            type(By.name("lastname"), contactData.getLastname());
            type(By.name("firstname"), contactData.getFirstname());
            type(By.name("address"), contactData.getAddress());
            type(By.name("email"), contactData.getAllEmail());
            type(By.name("email2"), contactData.getAllEmail2());
            type(By.name("email3"), contactData.getAllEmail3());
            type(By.name("home"), contactData.getAllPhones());
            type(By.name("mobile"), contactData.getMobilePhone());
            type(By.name("work"), contactData.getWorkPhone());


            try {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groups.get(0).getName());
                return true;
            } catch (NoSuchElementException e) {
                return true;
            }

        }
    }



    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void selectGroupInContact(String name) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "'" + "]")).click();
    }

    public void groupsPage(String name) {
        wd.findElement(By.xpath(
                "//a[contains(text(),'group page " + '"' + name + '"' + "'" + ")" + "]")).click();
    }

    public void submitAddContactInGroup() {
        click(By.name("add"));
    }

    public void submitDeleteContactFromGroup() {
        click(By.name("remove"));
    }

    public void create(ContactData contact) throws IOException {
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();
    }

    public void createContact(ContactData contactData) {

        type(By.name("lastname"), contactData.getLastname());
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getAllEmail());
        type(By.name("email2"), contactData.getAllEmail2());
        type(By.name("email3"), contactData.getAllEmail3());
        type(By.name("home"), contactData.getAllPhones());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());

        submitContactCreation();
        returnToContactPage();

    }

    public void modify(ContactData contact) throws IOException {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        isAlertAccept();
        contactCache = null;
    }

    public void addContactInGroup(ContactData contact) throws IOException {
        // заходим в писок групп и выбираем [none]
        selectGroupInContact("[none]");
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        // если спиок не пустой, то берём контакт и добавляем его в группу
        if (elements.size() > 0) {
            selectContactById(contact.getId());
            submitAddContactInGroup();
            contactCache = null;
            navigationHelper = new NavigationHelper(wd);
            navigationHelper.gotoContactHome();
            // если список [none] пуст, то создаю контакт и добавлюя в группу
        } else {
            navigationHelper = new NavigationHelper(wd);
            navigationHelper.contactPage();
            createContact(new ContactData().withLastname("Kozlov").
                    withFirstname("Sergey").withAddress("Г. Саратов, ул. Озёрная, д.45, кв. 23").
                    withAllEmail("ferdcvb@yandex.ru").withHomePhone("+79253478354"));;
            selectGroupInContact("[none]");
            boxContact();
            submitAddContactInGroup();
            navigationHelper.gotoContactHome();
        }
    }

    public void deleteFromGroup(ContactData contact, GroupData group) {
        selectGroupInContact(group.getName());
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        if (elements.size() == 0) {
            selectGroupInContact("[all]");
            selectContactById(contact.getId());
            submitAddContactInGroup();
            contactCache = null;
            groupsPage(group.getName());
        }
        boxContact();
        submitDeleteContactFromGroup();
        contactCache = null;
        groupsPage(group.getName());
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void boxContact() {
        click(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String emails = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).
                    withAllPhones(allPhones).withAddress(address).withAllEmail(emails));
        }
        return new Contacts(contactCache);
    }

    public ContactData InfoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).
                withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).
                withAddress(address).withAllEmail(email).withAllEmail2(email2).withAllEmail3(email3);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("a[href='edit.php?id=%s']", id))).click();
    }

}
