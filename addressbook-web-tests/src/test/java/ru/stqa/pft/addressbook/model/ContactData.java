package ru.stqa.pft.addressbook.model;

public class ContactData {
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

    public ContactData(String firstName, String lastName, String nick, String title, String company, String address, String phone, String email, String byear, String notes) {
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

    public String getCompany() {
        return company;
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
}
