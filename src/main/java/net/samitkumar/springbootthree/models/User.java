package net.samitkumar.springbootthree.models;

public record User(int id, String name, String email, Address address, String phone, String website, Company company) {
}
