package com.aidar.service.impl;

import com.aidar.enums.MessageStatus;
import com.aidar.model.Message;
import com.aidar.model.User;
import com.aidar.repository.MessageRepository;
import com.aidar.repository.UserRepository;
import com.aidar.service.MessageService;
import com.aidar.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by paradise on 28.04.16.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<User> getMyPenFriends() {
        User principal = securityService.getPersistedPrincipal();
        List<Message> messages = messageRepository.findAllBySenderOrRecipient(principal, principal);
        Set<User> penFriends = new HashSet<>();
        messages.forEach(m -> {
            User sender = m.getSender();
            penFriends.add(sender.equals(principal) ? m.getRecipient() : sender);
        });
        return penFriends;

    }

    @Override
    public List<Message> getDialog(Long id) {
        User principal = securityService.getPersistedPrincipal();
        User friend = userRepository.findOne(id);
        List<Message> messages = messageRepository.getDialog(principal, friend);
        messages.stream()
                .filter(m -> m.getSender().equals(friend))
                .forEach(m -> m.setStatus(MessageStatus.READ));
        return messages;
    }

    @Override
    public List<Message> getNew(Long id) {
        User principal = securityService.getPersistedPrincipal();
        User friend = userRepository.findOne(id);
        List<Message> messages = messageRepository
                .findAllBySenderAndRecipientAndStatus(friend, principal, MessageStatus.NEW);
        messages.forEach(m -> m.setStatus(MessageStatus.READ));
        return messages;
    }

    @Override
    public Message add(Long id, String text) {
        User principal = securityService.getPersistedPrincipal();
        User friend = userRepository.findOne(id);
        Message message = new Message(text, principal, friend);
        return messageRepository.save(message);
    }

}
