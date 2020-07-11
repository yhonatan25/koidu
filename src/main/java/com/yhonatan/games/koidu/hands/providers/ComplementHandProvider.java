package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.HandCategory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ComplementHandProvider implements HandProvider {

    private final CardListExtractor firstCardListExtractor;
    private final CardListExtractor secondCardListExtractor;
    private final HandCategory handCategory;

    public ComplementHandProvider(final CardListExtractor firstCardListExtractor, final CardListExtractor secondCardListExtractor, final HandCategory handCategory) {
        this.firstCardListExtractor = firstCardListExtractor;
        this.secondCardListExtractor = secondCardListExtractor;
        this.handCategory = handCategory;
    }

    @Override
    public Optional<Hand> getHand(final List<Card> cardList) {
        final List<Card> firstCardList = firstCardListExtractor.extractHand(cardList);
        if (firstCardList.isEmpty()) {
            return Optional.empty();
        }

        final List<Card> complementCardList = cardList.stream()
                .filter(c -> !firstCardList.contains(c))
                .collect(toList());
        final List<Card> secondCardList = secondCardListExtractor.extractHand(complementCardList);
        if (secondCardList.isEmpty()) {
            return Optional.empty();
        }

        final List<Card> handCardList = Stream.concat(firstCardList.stream(), secondCardList.stream())
                .collect(toList());
        return Optional.of(new Hand(handCategory, handCardList));
    }
}
