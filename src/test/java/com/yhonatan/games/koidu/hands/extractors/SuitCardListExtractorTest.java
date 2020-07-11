package com.yhonatan.games.koidu.hands.extractors;

import com.yhonatan.games.koidu.cards.Card;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.yhonatan.games.koidu.cards.Card.ACE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.ACE_HEART;
import static com.yhonatan.games.koidu.cards.Card.ACE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_HEART;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FIVE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.FIVE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FIVE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FOUR_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FOUR_HEART;
import static com.yhonatan.games.koidu.cards.Card.FOUR_SPADE;
import static com.yhonatan.games.koidu.cards.Card.JACK_CLUB;
import static com.yhonatan.games.koidu.cards.Card.JACK_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.JACK_HEART;
import static com.yhonatan.games.koidu.cards.Card.JACK_SPADE;
import static com.yhonatan.games.koidu.cards.Card.KING_CLUB;
import static com.yhonatan.games.koidu.cards.Card.KING_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.KING_HEART;
import static com.yhonatan.games.koidu.cards.Card.KING_SPADE;
import static com.yhonatan.games.koidu.cards.Card.NINE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.NINE_HEART;
import static com.yhonatan.games.koidu.cards.Card.NINE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.SIX_CLUB;
import static com.yhonatan.games.koidu.cards.Card.SIX_HEART;
import static com.yhonatan.games.koidu.cards.Card.SIX_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.TEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.TEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.TEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.THREE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.THREE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.THREE_HEART;
import static com.yhonatan.games.koidu.cards.Card.THREE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TWO_CLUB;
import static com.yhonatan.games.koidu.cards.Card.TWO_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.TWO_HEART;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SuitCardListExtractorTest {

    private SuitCardListExtractor suitCardListExtractor = new SuitCardListExtractor();

    @ParameterizedTest
    @MethodSource("getTestArguments")
    void suitCardListExtractorReturnsHand(final List<Card> cardList, final List<Card> expectedHand) {
        final List<Card> hand = suitCardListExtractor.extractHand(cardList);
        assertThat(hand, is(expectedHand));
    }

    private static Stream<Arguments> getTestArguments() {
        return Stream.of(
                //Positive
                Arguments.of(List.of(ACE_HEART, THREE_HEART, FOUR_HEART, SIX_HEART, KING_HEART), List.of(ACE_HEART, THREE_HEART, FOUR_HEART, SIX_HEART, KING_HEART)),
                Arguments.of(List.of(SIX_SPADE, TEN_SPADE, JACK_SPADE, QUEEN_SPADE, KING_SPADE), List.of(SIX_SPADE, TEN_SPADE, JACK_SPADE, QUEEN_SPADE, KING_SPADE)),
                Arguments.of(List.of(TWO_DIAMOND, FIVE_DIAMOND, TEN_DIAMOND, JACK_DIAMOND, QUEEN_DIAMOND), List.of(TWO_DIAMOND, FIVE_DIAMOND, TEN_DIAMOND, JACK_DIAMOND, QUEEN_DIAMOND)),
                Arguments.of(List.of(TWO_CLUB, THREE_CLUB, KING_CLUB, JACK_CLUB, SIX_CLUB), List.of(TWO_CLUB, THREE_CLUB, KING_CLUB, JACK_CLUB, SIX_CLUB)),
                //Negative
                Arguments.of(List.of(THREE_SPADE, THREE_HEART, THREE_DIAMOND, SIX_HEART, KING_SPADE), List.of()),
                Arguments.of(List.of(KING_HEART, TEN_SPADE, FOUR_DIAMOND, KING_CLUB, KING_SPADE), List.of()),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, QUEEN_HEART, QUEEN_DIAMOND), List.of()),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, SIX_SPADE, SIX_HEART, SIX_CLUB), List.of()),
                Arguments.of(List.of(TEN_CLUB, TEN_SPADE, TEN_HEART, THREE_CLUB, QUEEN_SPADE), List.of()),
                Arguments.of(List.of(FIVE_DIAMOND, SEVEN_SPADE, THREE_SPADE, SEVEN_HEART, SEVEN_DIAMOND), List.of()),
                Arguments.of(List.of(EIGHT_DIAMOND, EIGHT_SPADE, EIGHT_HEART, SIX_HEART, THREE_HEART), List.of()),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, ACE_SPADE, ACE_DIAMOND, ACE_HEART), List.of()),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, NINE_SPADE, NINE_CLUB, NINE_HEART), List.of()),
                Arguments.of(List.of(FIVE_DIAMOND, FIVE_DIAMOND, THREE_SPADE, FIVE_SPADE, QUEEN_HEART), List.of())
        );
    }
}