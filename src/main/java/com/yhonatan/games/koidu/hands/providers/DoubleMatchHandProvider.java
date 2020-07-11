package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.HandCategory;

import java.util.List;
import java.util.Optional;

public class DoubleMatchHandProvider implements HandProvider {

    private final CardListExtractor firstListExtractor;
    private final CardListExtractor secondCardListExtractor;
    private final HandCategory handCategory;

    public DoubleMatchHandProvider(final CardListExtractor firstListExtractor, final CardListExtractor secondCardListExtractor, final HandCategory handCategory) {
        this.firstListExtractor = firstListExtractor;
        this.secondCardListExtractor = secondCardListExtractor;
        this.handCategory = handCategory;
    }

    @Override
    public Optional<Hand> getHand(final List<Card> cardList) {
        final List<Card> firstCardList = firstListExtractor.extractHand(cardList);
        if (firstCardList.isEmpty()) {
            return Optional.empty();
        }

        final List<Card> secondCardList = secondCardListExtractor.extractHand(cardList);
        if (secondCardList.isEmpty()) {
            return Optional.empty();
        }

        if (!firstCardList.equals(secondCardList)) {
            return Optional.empty();
        }

        return Optional.of(new Hand(handCategory, firstCardList));
    }
}
