package com.aidar.model;

import javax.persistence.*;

/**
 * Created by paradise on 16.04.16.
 */
@Entity
@Table(name = "user_community")
@SequenceGenerator(name = "user_community_gen",
        sequenceName = "user_community_seq", allocationSize = 1)
public class UserCommunity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_community_gen")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "community_id")
    private Community community;

    public UserCommunity() {
    }

    public UserCommunity(User user, Community community) {
        this.user = user;
        this.community = community;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCommunity)) return false;

        UserCommunity that = (UserCommunity) o;

        if (getId() != that.getId()) return false;
        if (!getUser().equals(that.getUser())) return false;
        return getCommunity().equals(that.getCommunity());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getCommunity().hashCode();
        return result;
    }
}
