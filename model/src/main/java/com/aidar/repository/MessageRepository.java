package com.aidar.repository;

import com.aidar.enums.MessageStatus;
import com.aidar.model.Message;
import com.aidar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by paradise on 16.04.16.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.sender = :sender and m.recipient = :recipient " +
            "or m.sender = :recipient and m.recipient = :sender order by m.createdAt")
    List<Message> getDialog(@Param("sender") User sender,
                            @Param("recipient") User recipient);

    List<Message> findAllBySenderOrRecipient(User sender, User recipient);

    List<Message> findAllBySenderAndRecipientAndStatus(User sender, User recipient,
                                                       MessageStatus status);

}
