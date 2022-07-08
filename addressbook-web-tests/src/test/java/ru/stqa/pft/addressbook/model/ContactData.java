package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String lastname;
    private final String firstname;
    private final String address;
    private final String all_email;
    private final String all_phones;
    private String group;

    public ContactData(String lastname, String firstname, String address, String all_email, String all_phones,
                       String group) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.all_email = all_email;
        this.all_phones = all_phones;
        this.group = group;
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

}
