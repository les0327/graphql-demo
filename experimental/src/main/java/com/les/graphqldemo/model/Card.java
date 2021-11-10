package com.les.graphqldemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    private String id;

    private String number;

    @OneToOne(targetEntity = User.class)
    private User user;

    public Card() {
    }

    public Card(String id, String number, User user) {
        this.id = id;
        this.number = number;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id) && number.equals(card.number) && user.equals(card.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, user);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", user=" + user +
                '}';
    }
}
