package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;

import java.util.List;
import java.util.Optional;

public interface HandProvider {

    Optional<Hand> getHand(final List<Card> cardList);

}
