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
}
