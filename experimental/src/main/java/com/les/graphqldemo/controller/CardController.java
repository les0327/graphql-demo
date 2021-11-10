package com.les.graphqldemo.controller;

import com.les.graphqldemo.exception.UserNotFoundException;
import com.les.graphqldemo.model.Card;
import com.les.graphqldemo.repository.CardRepository;
import com.les.graphqldemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CardController {

    private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    @Autowired
    public CardController(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping(name = "card")
    public Card findByUserId(@Argument String userId) {
        LOG.info("FindByUserId {}", userId);
        return cardRepository
                .findByUserId(userId)
                .orElse(null);
    }

    @QueryMapping(name = "cards")
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @MutationMapping(name = "createCard")
    public Card create(@Argument String number, @Argument String userId) {
        return cardRepository.save(
                new Card(
                        UUID.randomUUID().toString(),
                        number,
                        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId))
                )
        );
    }
}
