package net.samitkumar.springbootthree.models;

public record Address(String street, String suite, String city, String zipcode, Geo geo) {
}
