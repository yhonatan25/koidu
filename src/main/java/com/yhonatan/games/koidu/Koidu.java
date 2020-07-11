package com.yhonatan.games.koidu;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.providers.HandProvider;
import com.yhonatan.games.koidu.hands.providers.HandProviderFactory;

import java.util.List;

public class Koidu {

    private static final Koidu KOIDU = new Koidu();

    private final HandProvider handProvider;

    public static Koidu getInstance(){
        return KOIDU;
    }

    private Koidu() {
        handProvider = HandProviderFactory.getChainHandProvider();
    }

    public Hand getHand(final List<Card> cardList) {
        return handProvider.getHand(cardList).get();
    }

}
