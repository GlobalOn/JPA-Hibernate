package edu.jpa.blog.domain;

import javax.persistence.*;
import java.util.List;

public class Blog extends DomainObject {
    private String name;
    private String author;
    private List<BlogPost> posts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<BlogPost> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogPost> posts) {
        this.posts = posts;
    }
}
