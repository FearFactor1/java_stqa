package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String lastname;
    private final String firstname;
    private final String address;
    private final String all_email;
    private final String all_phones;
    private final String group;

    public ContactData(String lastname, String firstname, String address, String all_email, String all_phones,
                       String group) {
        this.id = 0;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.all_email = all_email;
        this.all_phones = all_phones;
        this.group = group;
    }

    public ContactData(int id, String lastname, String firstname, String address, String all_email, String all_phones,
                       String group) {
        this.id = Integer.MAX_VALUE;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.all_email = all_email;
        this.all_phones = all_phones;
        this.group = group;
    }

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

    public String getAllPhones() {
        return all_phones;
    }

    public String getGroup() {
        return group;
    }

    public void setId(int id) {
        this.id = id;
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
