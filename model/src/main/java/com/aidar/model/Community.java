package com.aidar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by paradise on 13.04.16.
 */
@Entity
@Table(name = "community")
@SequenceGenerator(name = "community_gen",
        sequenceName = "community_seq", allocationSize = 1)
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "community_gen")
    private long id;

    @Size(min = 1, max = 20, message = "From 1 to 20 symbols")
    private String name;

    @NotEmpty(message = "Provide some description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "founder_id")
    private User founder;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "community")
    private List<News> news;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Community)) return false;

        Community community = (Community) o;

        return getId() == community.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
