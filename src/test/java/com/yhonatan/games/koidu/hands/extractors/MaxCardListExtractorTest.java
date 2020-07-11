package com.yhonatan.games.koidu.hands.extractors;

import com.yhonatan.games.koidu.cards.Card;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.yhonatan.games.koidu.cards.Card.ACE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.ACE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.ACE_HEART;
import static com.yhonatan.games.koidu.cards.Card.FIVE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.FIVE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FOUR_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FOUR_HEART;
import static com.yhonatan.games.koidu.cards.Card.FOUR_SPADE;
import static com.yhonatan.games.koidu.cards.Card.JACK_CLUB;
import static com.yhonatan.games.koidu.cards.Card.JACK_HEART;
import static com.yhonatan.games.koidu.cards.Card.JACK_SPADE;
import static com.yhonatan.games.koidu.cards.Card.KING_CLUB;
import static com.yhonatan.games.koidu.cards.Card.KING_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.KING_SPADE;
import static com.yhonatan.games.koidu.cards.Card.NINE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.NINE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.SIX_HEART;
import static com.yhonatan.games.koidu.cards.Card.TEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.TEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.THREE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.THREE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.THREE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TWO_HEART;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MaxCardListExtractorTest {

    private MaxCardListExtractor maxCardListExtractor = new MaxCardListExtractor();

    @ParameterizedTest
    @MethodSource("getTestArguments")
    void maxCardListExtractorGetHand(final List<Card> cardList, final Card expectedHighCard) {
        final List<Card> hand = maxCardListExtractor.extractHand(cardList);
        assertThat(hand.size(), is(1));
        assertThat(hand.stream().findAny().get(), is(expectedHighCard));
    }

    private static Stream<Arguments> getTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, TWO_HEART, THREE_SPADE, SIX_HEART, KING_SPADE), ACE_CLUB),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, NINE_SPADE, KING_SPADE), KING_SPADE),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, TEN_SPADE, KING_DIAMOND), KING_DIAMOND),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, SIX_HEART, KING_SPADE), ACE_DIAMOND),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, THREE_SPADE, SIX_HEART, QUEEN_SPADE), KING_CLUB),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SIX_HEART, SEVEN_DIAMOND), TEN_CLUB),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, THREE_SPADE, SIX_HEART, KING_SPADE), KING_SPADE),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, SIX_HEART, ACE_HEART), ACE_HEART),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, SIX_HEART, JACK_CLUB), KING_DIAMOND),
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART), QUEEN_HEART)
        );
    }

}