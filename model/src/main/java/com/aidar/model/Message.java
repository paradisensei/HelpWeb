package com.aidar.model;

import com.aidar.enums.MessageStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paradise on 13.04.16.
 */
@Entity
@Table(name = "message")
@SequenceGenerator(name = "message_gen",
        sequenceName = "message_seq", allocationSize = 1)
public class
Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_gen")
    private long id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    public Message() {
    }

    public Message(String text, User sender, User recipient) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        createdAt = new Date();
        status = MessageStatus.NEW;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (getId() != message.getId()) return false;
        if (!getText().equals(message.getText())) return false;
        if (!getSender().equals(message.getSender())) return false;
        return getRecipient().equals(message.getRecipient());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getText().hashCode();
        result = 31 * result + getSender().hashCode();
        result = 31 * result + getRecipient().hashCode();
        return result;
    }
}
