package com.yhonatan.games.koidu;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.providers.HandProvider;
import com.yhonatan.games.koidu.hands.providers.HandProviderFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class Koidu {

    private static final Koidu KOIDU = new Koidu();

    private final HandProvider handProvider;

    public static Koidu getInstance() {
        return KOIDU;
    }

    private Koidu() {
        handProvider = HandProviderFactory.getChainHandProvider();
    }

    public Hand getHand(final List<Card> cardList) {
        return handProvider.getHand(cardList).get();
    }

    public List<Card> getCardList(final int size) {
        final List<Card> cards = Arrays.asList(Card.values());
        if (size < 1 || size > cards.size()) {
            throw new IllegalArgumentException("Closed range for size is: [1,52]");
        }

        final List<Card> cardList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            getRandomCard(cards, cardList)
                    .ifPresent(cardList::add);
        }

        return cardList;
    }

    private Optional<Card> getRandomCard(final List<Card> cardList, final List<Card> excludeCardList) {
        final List<Card> filteredCardList = cardList.stream()
                .filter(c -> !excludeCardList.contains(c))
                .collect(toList());

        if (filteredCardList.isEmpty()) {
            return Optional.empty();
        }

        final int randomIndex = new Random().nextInt(filteredCardList.size());
        return Optional.of(filteredCardList.get(randomIndex));
    }

}
