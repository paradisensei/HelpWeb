package com.aidar.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paradise on 30.04.16.
 */
@Entity
@Table(name = "news")
@SequenceGenerator(name = "news_gen",
        sequenceName = "news_seq", allocationSize = 1)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_gen")
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public News() {
    }

    public News(String text, Community community, User author) {
        this.text = text;
        this.community = community;
        this.author = author;
        createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
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
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (getId() != null ? !getId().equals(news.getId()) : news.getId() != null) return false;
        if (!getText().equals(news.getText())) return false;
        if (!getCommunity().equals(news.getCommunity())) return false;
        return getAuthor().equals(news.getAuthor());

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getText().hashCode();
        result = 31 * result + getCommunity().hashCode();
        result = 31 * result + getAuthor().hashCode();
        return result;
    }
}
