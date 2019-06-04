package ru.javawebinar.basejava.model;

import java.io.File;

public enum ContactType {
    LANDLINE("Phone", null),
    SKYPE("Skype", null),
    EMAIL("Email", null),
    LINKEDIN("LinkedIn", null),
    GITHUB("GitHub", null),
    STACKOVERFLOW("Stackoverflow", null),
    HOME_PAGE("HomePage", null);


    private String title;
    private File logo;

    ContactType(String title, File logo) {
        this.title = title;
        this.logo = logo;
    }
}
