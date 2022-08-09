package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "address")
    private String address;
    @Expose
    @Column(name = "email")
    private String allEmail;
    @Expose
    @Column(name = "email2")
    private String allEmail2;
    @Expose
    @Column(name = "email3")
    private String allEmail3;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "home")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    private String mobile;
    @Expose
    @Column(name = "work")
    private String workPhone;
    @Expose
    @Transient
    @Column(name = "photo")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public Set<GroupData> getGroups() {
        return groups;
    }

//    public Groups getGroups() {
//        return new Groups(groups);
//    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
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
        return allEmail;
    }

    public String getAllEmail2() {
        return allEmail2;
    }

    public String getAllEmail3() {
        return allEmail3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return workPhone;
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

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withAllEmail2(String allEmail2) {
        this.allEmail2 = allEmail2;
        return this;
    }

    public ContactData withAllEmail3(String allEmail3) {
        this.allEmail3 = allEmail3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
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
        return id == that.id && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, address);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
