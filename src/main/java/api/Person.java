package api;

public class Person {

    private String firstName;
    private String lastName;
    private Address address;
    private String mail;

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Person setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Person setMail(String mail) {
        this.mail = mail;
        return this;
    }
}
