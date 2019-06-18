package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Link {
    private final String name;
    private final String url;

    public Link(String name, String url) {
        Objects.requireNonNull(name, "Name must not be null");
        this.name = name;
        this.url = url;
    }
}
