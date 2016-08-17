package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private final String firstName;
    private final String lastName;
    private final String nick;
    private final String title;
    private final String company;
    private final String address;
    private final String phone;
    private final String email;
    private final String byear;
    private final String notes;
    private String group;

    public ContactData(int id, String firstName, String lastName, String nick, String title, String company, String address, String phone, String email, String byear, String notes, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.byear = byear;
        this.notes = notes;
        this.group = group;
    }

    public ContactData(String firstName, String lastName, String nick, String title, String company, String address, String phone, String email, String byear, String notes, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.byear = byear;
        this.notes = notes;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNick() {
        return nick;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getByear() {
        return byear;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }


}