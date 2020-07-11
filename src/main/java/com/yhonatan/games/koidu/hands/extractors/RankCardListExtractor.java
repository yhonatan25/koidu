package com.yhonatan.games.koidu.hands.extractors;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.cards.Rank;
import com.yhonatan.games.koidu.hands.providers.CardListExtractor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class RankCardListExtractor implements CardListExtractor {

    private final int handSize;

    public RankCardListExtractor(final int handSize) {
        this.handSize = handSize;
    }

    @Override
    public List<Card> extractHand(final List<Card> cardList) {
        final Map<Rank, List<Card>> groupedCards = cardList.stream()
                .collect(groupingBy(Card::getRank, toList()));

        return groupedCards.values().stream()
                .filter(l -> l.size() == handSize)
                .max(Comparator.comparingInt(l -> l.stream().findAny().get().getRank().getRank()))
                .orElse(List.of());
    }
}
