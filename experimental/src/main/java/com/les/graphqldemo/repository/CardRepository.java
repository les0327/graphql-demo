package com.les.graphqldemo.repository;

import com.les.graphqldemo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, String> {

    Optional<Card> findByUserId(String userId);
}
