package model;

public record Person(
        String firstName,
        String lastName,
        Address address,
        Email email
) {
}
