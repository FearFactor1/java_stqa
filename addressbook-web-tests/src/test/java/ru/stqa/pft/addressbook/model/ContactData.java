package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String lastname;
    @Expose
    private String firstname;
    @Expose
    private String address;
    @Expose
    private String all_email;
    @Expose
    private String all_email2;
    @Expose
    private String all_email3;
    @Expose
    private String all_phones;
    @Expose
    private String group;
    @Expose
    private String home_phone;
    @Expose
    private String mobile;
    @Expose
    private String work_phone;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public String getAllEmail() {
        return all_email;
    }

    public String getAllEmail2() {
        return all_email2;
    }

    public String getAllEmail3() {
        return all_email3;
    }

    public String getAllPhones() {
        return all_phones;
    }

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return home_phone;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work_phone;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllEmail(String all_email) {
        this.all_email = all_email;
        return this;
    }

    public ContactData withAllEmail2(String all_email2) {
        this.all_email2 = all_email2;
        return this;
    }

    public ContactData withAllEmail3(String all_email3) {
        this.all_email3 = all_email3;
        return this;
    }

    public ContactData withAllPhones(String all_phones) {
        this.all_phones = all_phones;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHomePhone(String home_phone) {
        this.home_phone = home_phone;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work_phone) {
        this.work_phone = work_phone;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname);
    }
}
