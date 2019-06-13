package ru.javawebinar.basejava.model;

public enum ContactType {
    LANDLINE("Phone"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOME_PAGE("HomePage");


    private String title;

    ContactType(String title) {
        this.title = title;
    }
}
