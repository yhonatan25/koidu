package com.yhonatan.games.koidu.hands.extractors;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.providers.CardListExtractor;

import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class MaxCardListExtractor implements CardListExtractor {
    @Override
    public List<Card> extractHand(final List<Card> cardList) {
        return cardList.stream()
                .max(comparingInt(c -> c.getRank().getRank()))
                .stream()
                .collect(toList());
    }
}
