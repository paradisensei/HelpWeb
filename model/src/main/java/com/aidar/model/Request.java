package com.aidar.model;

import com.aidar.enums.RequestStatus;
import com.aidar.enums.ServiceType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by paradise on 13.04.16.
 */
@Entity
@Table(name = "request")
@SequenceGenerator(name = "request_gen",
        sequenceName = "request_seq", allocationSize = 1)
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_gen")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "needy_id")
    private User needy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    private User volunteer;

    private String address;

    private double latitude;

    private double longitude;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "request")
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getNeedy() {
        return needy;
    }

    public void setNeedy(User needy) {
        this.needy = needy;
    }

    public User getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(User volunteer) {
        this.volunteer = volunteer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        return getId() == request.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
