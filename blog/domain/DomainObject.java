package edu.jpa.blog.domain;

import javax.persistence.*;

public abstract class DomainObject {
    private int id;
    private int version;

    public int getId() {
        return id;
    }
}
