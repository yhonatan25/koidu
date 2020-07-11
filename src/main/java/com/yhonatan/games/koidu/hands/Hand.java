package com.yhonatan.games.koidu.hands;

import com.yhonatan.games.koidu.cards.Card;

import java.util.List;

public class Hand {
    private final HandCategory handCategory;
    private final List<Card> cardList;

    public Hand(final HandCategory handCategory, final List<Card> cardList) {
        this.handCategory = handCategory;
        this.cardList = cardList;
    }

    public HandCategory getHandCategory() {
        return handCategory;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
