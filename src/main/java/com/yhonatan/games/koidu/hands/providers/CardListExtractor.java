package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;

import java.util.List;

public interface CardListExtractor {

    List<Card> extractHand(final List<Card> cardList);

}
