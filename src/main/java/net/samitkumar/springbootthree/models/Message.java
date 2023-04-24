package net.samitkumar.springbootthree.models;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record Message(String message) implements Serializable {}
