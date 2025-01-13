package com.nnk.springboot.service;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.repositories.LinkRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.form.SignUpForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public LinkService(LinkRepository linkRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public Link createLink(User user1, String user2Mail) {
        Link link = new Link();
        User user2 = userRepository.findUserByMail(user2Mail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + user2Mail));
        link.setUser1(user1);
        link.setUser2(user2);

        user1.getLinks().add(link);

        linkRepository.save(link);
        userRepository.save(user1);

        return link;
    }

    public boolean checkLink(User user1, String user2Mail) {
        User user2 = userRepository.findUserByMail(user2Mail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + user2Mail));
        return linkRepository.existsByUsers(user1.getId(), user2.getId());
    }

    public void deleteLink(User user1, User user2) {
        Link link = linkRepository.selectByUsers(user1.getId(), user2.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with these users"));;
        boolean removed = user1.getLinks().remove(link);
        if (!removed) {
            throw new IllegalStateException("The link was not found in user1's links");
        }
        userRepository.save(user1);
        linkRepository.delete(link);
    }
}
