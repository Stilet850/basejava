package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.Objects;

public class Link  implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String name;
    private final String url;

    public Link(String name, String url) {
        Objects.requireNonNull(name, "Name must not be null");
        this.name = name;
        this.url = url;
    }
}
