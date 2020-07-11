package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.HandCategory;

import java.util.List;
import java.util.Optional;

public class ExtractorDelegateHandProvider implements HandProvider {

    private final CardListExtractor cardListExtractor;
    private final HandCategory handCategory;

    public ExtractorDelegateHandProvider(final CardListExtractor cardListExtractor, final HandCategory handCategory) {
        this.cardListExtractor = cardListExtractor;
        this.handCategory = handCategory;
    }

    @Override
    public Optional<Hand> getHand(final List<Card> cardList) {
        final List<Card> handCardList = cardListExtractor.extractHand(cardList);
        if (handCardList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Hand(handCategory, handCardList));
    }
}
