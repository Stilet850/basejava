package ru.javawebinar.basejava.serializer;

import ru.javawebinar.basejava.model.Link;
import ru.javawebinar.basejava.model.Organization;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.content.ListSection;
import ru.javawebinar.basejava.model.content.OrganizationSection;
import ru.javawebinar.basejava.model.content.TextSection;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer{
    private final XmlParser parser;

    public XmlStreamSerializer() {
        this.parser = new XmlParser(Resume.class, Organization.class, Link.class, OrganizationSection.class, TextSection.class, ListSection.class, Organization.Position.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            parser.marshall(r, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)){
            return parser.unmarshal(reader);
        }
    }
}
