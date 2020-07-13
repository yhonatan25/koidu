package com.yhonatan.games.koidu.hands.extractors;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.providers.CardListExtractor;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;

import static java.util.Comparator.comparingInt;

public class SequenceCardListExtractor implements CardListExtractor {
    @Override
    public List<Card> extractHand(final List<Card> cardList) {
        if (isSequence(cardList, c -> c.getRank().getRank())) {
            return cardList;
        }

        if (isSequence(cardList, c -> c.getRank().getRank() % 13)) {
            return cardList;
        }

        return List.of();
    }

    private boolean isSequence(final List<Card> cardList, ToIntFunction<Card> keyExtractor) {
        if (cardList.stream().map(Card::getRank).distinct().count() < 5) {
            return false;
        }

        Optional<Card> optionalMin = cardList.stream()
                .min(comparingInt(keyExtractor));

        Optional<Card> optionalMax = cardList.stream()
                .max(comparingInt(keyExtractor));

        return optionalMin.filter(c -> keyExtractor.applyAsInt(c) > 0)
                .map(c -> keyExtractor.applyAsInt(optionalMax.get()) - keyExtractor.applyAsInt(c))
                .map(i -> i == 4)
                .orElse(false);
    }


}
