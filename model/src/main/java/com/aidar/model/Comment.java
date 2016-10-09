package com.aidar.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paradise on 13.04.16.
 */
@Entity
@Table(name = "comment")
@SequenceGenerator(name = "comment_gen",
        sequenceName = "comment_seq", allocationSize = 1)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_gen")
    private long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Comment() {
    }

    public Comment(String text, Request request, User author) {
        this.text = text;
        this.request = request;
        this.author = author;
        createdAt = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getId() != comment.getId()) return false;
        if (!getText().equals(comment.getText())) return false;
        if (!getRequest().equals(comment.getRequest())) return false;
        return getAuthor().equals(comment.getAuthor());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getText().hashCode();
        result = 31 * result + getRequest().hashCode();
        result = 31 * result + getAuthor().hashCode();
        return result;
    }
}
