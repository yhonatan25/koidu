package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;

import java.util.List;
import java.util.Optional;

public class ChainHandProvider implements HandProvider {

    private final HandProvider firstHandProvider;
    private final HandProvider secondHandProvider;

    public ChainHandProvider(final HandProvider firstHandProvider, final HandProvider secondHandProvider) {
        this.firstHandProvider = firstHandProvider;
        this.secondHandProvider = secondHandProvider;
    }

    @Override
    public Optional<Hand> getHand(final List<Card> cardList) {
        return firstHandProvider.getHand(cardList)
                .or(() -> secondHandProvider.getHand(cardList))
                .or(Optional::empty);
    }
}
