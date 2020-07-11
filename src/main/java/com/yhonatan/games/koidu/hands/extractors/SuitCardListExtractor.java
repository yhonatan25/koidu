package com.yhonatan.games.koidu.hands.extractors;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.cards.Suit;
import com.yhonatan.games.koidu.hands.providers.CardListExtractor;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class SuitCardListExtractor implements CardListExtractor {
    @Override
    public List<Card> extractHand(final List<Card> cardList) {
        final Map<Suit, List<Card>> groupedCards = cardList.stream()
                .collect(groupingBy(Card::getSuit, toList()));

        return groupedCards.values().stream()
                .filter(l -> l.size() == 5)
                .findAny()
                .orElse(List.of());
    }
}
