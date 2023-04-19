package net.samitkumar.springbootthree.models;

import lombok.Builder;

@Builder
public record User(int id, String name, String email, Address address, String phone, String website, Company company) {
}
