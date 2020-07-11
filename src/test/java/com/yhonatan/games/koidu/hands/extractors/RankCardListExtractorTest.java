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
import static com.yhonatan.games.koidu.cards.Card.ACE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_CLUB;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_HEART;
import static com.yhonatan.games.koidu.cards.Card.EIGHT_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FIVE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.FIVE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FIVE_HEART;
import static com.yhonatan.games.koidu.cards.Card.FIVE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FOUR_CLUB;
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
import static com.yhonatan.games.koidu.cards.Card.NINE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.NINE_HEART;
import static com.yhonatan.games.koidu.cards.Card.NINE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.SEVEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.SIX_CLUB;
import static com.yhonatan.games.koidu.cards.Card.SIX_DIAMOND;
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
import static com.yhonatan.games.koidu.cards.Card.TWO_SPADE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RankCardListExtractorTest {

    @ParameterizedTest
    @MethodSource("getTestArguments")
    void rankCardListExtractorReturnsHand(final List<Card> cardList, final List<Card> expectedHand, final int handSize) {
        final RankCardListExtractor rankCardListExtractor = new RankCardListExtractor(handSize);
        final List<Card> hand = rankCardListExtractor.extractHand(cardList);
        assertThat(hand, is(expectedHand));
    }

    private static Stream<Arguments> getTestArguments() {
        return Stream.of(
                //PAIRS - Positive
                Arguments.of(List.of(TWO_SPADE, TWO_HEART, THREE_SPADE, SIX_HEART, KING_SPADE), List.of(TWO_SPADE, TWO_HEART), 2),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, KING_CLUB, KING_SPADE), List.of(KING_CLUB, KING_SPADE), 2),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, QUEEN_HEART, KING_DIAMOND), List.of(QUEEN_SPADE, QUEEN_HEART), 2),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, SIX_HEART, SIX_CLUB), List.of(SIX_HEART, SIX_CLUB), 2),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, THREE_SPADE, THREE_CLUB, QUEEN_SPADE), List.of(THREE_SPADE, THREE_CLUB), 2),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SEVEN_HEART, SEVEN_DIAMOND), List.of(SEVEN_HEART, SEVEN_DIAMOND), 2),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, THREE_SPADE, SIX_HEART, KING_SPADE), List.of(THREE_DIAMOND, THREE_SPADE), 2),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, ACE_DIAMOND, ACE_HEART), List.of(ACE_DIAMOND, ACE_HEART), 2),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, JACK_DIAMOND, JACK_CLUB), List.of(JACK_DIAMOND, JACK_CLUB), 2),
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART), List.of(THREE_CLUB, THREE_SPADE), 2),
                //PAIRS - Negative
                Arguments.of(List.of(TWO_SPADE, EIGHT_CLUB, THREE_SPADE, SIX_HEART, KING_SPADE), List.of(), 2),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, KING_CLUB, TWO_CLUB), List.of(), 2),
                Arguments.of(List.of(SIX_DIAMOND, JACK_HEART, FOUR_CLUB, QUEEN_HEART, KING_DIAMOND), List.of(), 2),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, TEN_DIAMOND, SIX_CLUB), List.of(), 2),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, TWO_DIAMOND, THREE_CLUB, QUEEN_SPADE), List.of(), 2),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SEVEN_HEART, QUEEN_CLUB), List.of(), 2),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, SEVEN_CLUB, SIX_HEART, KING_SPADE), List.of(), 2),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, ACE_DIAMOND, NINE_CLUB), List.of(), 2),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, EIGHT_DIAMOND, JACK_CLUB), List.of(), 2),
                Arguments.of(List.of(JACK_HEART, FIVE_HEART, THREE_SPADE, SIX_HEART, QUEEN_HEART), List.of(), 2),
                //THREE OF KIND - Positive
                Arguments.of(List.of(THREE_SPADE, THREE_HEART, THREE_DIAMOND, SIX_HEART, KING_SPADE), List.of(THREE_SPADE, THREE_HEART, THREE_DIAMOND), 3),
                Arguments.of(List.of(KING_HEART, TEN_SPADE, FOUR_DIAMOND, KING_CLUB, KING_SPADE), List.of(KING_HEART, KING_CLUB, KING_SPADE), 3),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, QUEEN_HEART, QUEEN_DIAMOND), List.of(QUEEN_SPADE, QUEEN_HEART, QUEEN_DIAMOND), 3),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, SIX_SPADE, SIX_HEART, SIX_CLUB), List.of(SIX_SPADE, SIX_HEART, SIX_CLUB), 3),
                Arguments.of(List.of(TEN_CLUB, TEN_SPADE, TEN_HEART, THREE_CLUB, QUEEN_SPADE), List.of(TEN_CLUB, TEN_SPADE, TEN_HEART), 3),
                Arguments.of(List.of(FIVE_DIAMOND, SEVEN_SPADE, THREE_SPADE, SEVEN_HEART, SEVEN_DIAMOND), List.of(SEVEN_SPADE, SEVEN_HEART, SEVEN_DIAMOND), 3),
                Arguments.of(List.of(EIGHT_DIAMOND, EIGHT_SPADE, EIGHT_HEART, SIX_HEART, THREE_HEART), List.of(EIGHT_DIAMOND, EIGHT_SPADE, EIGHT_HEART), 3),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, ACE_SPADE, ACE_DIAMOND, ACE_HEART), List.of(ACE_SPADE, ACE_DIAMOND, ACE_HEART), 3),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, NINE_SPADE, NINE_CLUB, NINE_HEART), List.of(NINE_SPADE, NINE_CLUB, NINE_HEART), 3),
                Arguments.of(List.of(FIVE_DIAMOND, FIVE_CLUB, THREE_SPADE, FIVE_SPADE, QUEEN_HEART), List.of(FIVE_DIAMOND, FIVE_CLUB, FIVE_SPADE), 3),
                //THREE OF KIND - Negative
                Arguments.of(List.of(TWO_SPADE, TWO_HEART, THREE_SPADE, SIX_HEART, KING_SPADE), List.of(), 3),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, KING_CLUB, KING_SPADE), List.of(), 3),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, QUEEN_HEART, KING_DIAMOND), List.of(), 3),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, SIX_HEART, SIX_CLUB), List.of(), 3),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, THREE_SPADE, THREE_CLUB, QUEEN_SPADE), List.of(), 3),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SEVEN_HEART, SEVEN_DIAMOND), List.of(), 3),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, THREE_SPADE, SIX_HEART, KING_SPADE), List.of(), 3),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, ACE_DIAMOND, ACE_HEART), List.of(), 3),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, JACK_DIAMOND, JACK_CLUB), List.of(), 3),
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART), List.of(), 3),
                //FOUR OF KIND - Positive
                Arguments.of(List.of(THREE_SPADE, THREE_HEART, THREE_DIAMOND, SIX_HEART, THREE_CLUB), List.of(THREE_SPADE, THREE_HEART, THREE_DIAMOND, THREE_CLUB), 4),
                Arguments.of(List.of(KING_HEART, TEN_SPADE, KING_DIAMOND, KING_CLUB, KING_SPADE), List.of(KING_HEART, KING_DIAMOND, KING_CLUB, KING_SPADE), 4),
                Arguments.of(List.of(QUEEN_CLUB, JACK_HEART, QUEEN_SPADE, QUEEN_HEART, QUEEN_DIAMOND), List.of(QUEEN_CLUB, QUEEN_SPADE, QUEEN_HEART, QUEEN_DIAMOND), 4),
                Arguments.of(List.of(SIX_DIAMOND, TWO_HEART, SIX_SPADE, SIX_HEART, SIX_CLUB), List.of(SIX_DIAMOND, SIX_SPADE, SIX_HEART, SIX_CLUB), 4),
                Arguments.of(List.of(TEN_CLUB, TEN_SPADE, TEN_HEART, TEN_DIAMOND, QUEEN_SPADE), List.of(TEN_CLUB, TEN_SPADE, TEN_HEART, TEN_DIAMOND), 4),
                Arguments.of(List.of(SEVEN_CLUB, SEVEN_SPADE, THREE_SPADE, SEVEN_HEART, SEVEN_DIAMOND), List.of(SEVEN_CLUB, SEVEN_SPADE, SEVEN_HEART, SEVEN_DIAMOND), 4),
                Arguments.of(List.of(EIGHT_DIAMOND, EIGHT_SPADE, EIGHT_HEART, SIX_HEART, EIGHT_CLUB), List.of(EIGHT_DIAMOND, EIGHT_SPADE, EIGHT_HEART, EIGHT_CLUB), 4),
                Arguments.of(List.of(ACE_CLUB, JACK_HEART, ACE_SPADE, ACE_DIAMOND, ACE_HEART), List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, ACE_HEART), 4),
                Arguments.of(List.of(NINE_DIAMOND, KING_DIAMOND, NINE_SPADE, NINE_CLUB, NINE_HEART), List.of(NINE_DIAMOND, NINE_SPADE, NINE_CLUB, NINE_HEART), 4),
                Arguments.of(List.of(FIVE_DIAMOND, FIVE_CLUB, THREE_SPADE, FIVE_SPADE, FIVE_HEART), List.of(FIVE_DIAMOND, FIVE_CLUB, FIVE_SPADE, FIVE_HEART), 4),
                //FOUR OF KIND - Negative
                Arguments.of(List.of(THREE_SPADE, THREE_HEART, THREE_DIAMOND, SIX_HEART, KING_SPADE), List.of(), 4),
                Arguments.of(List.of(KING_HEART, TEN_SPADE, FOUR_DIAMOND, KING_CLUB, KING_SPADE), List.of(), 4),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, QUEEN_HEART, QUEEN_DIAMOND), List.of(), 4),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, SIX_SPADE, SIX_HEART, SIX_CLUB), List.of(), 4),
                Arguments.of(List.of(TEN_CLUB, TEN_SPADE, TEN_HEART, THREE_CLUB, QUEEN_SPADE), List.of(), 4),
                Arguments.of(List.of(FIVE_DIAMOND, SEVEN_SPADE, THREE_SPADE, SEVEN_HEART, SEVEN_DIAMOND), List.of(), 4),
                Arguments.of(List.of(EIGHT_DIAMOND, EIGHT_SPADE, EIGHT_HEART, SIX_HEART, THREE_HEART), List.of(), 4),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, ACE_SPADE, ACE_DIAMOND, ACE_HEART), List.of(), 4),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, NINE_SPADE, NINE_CLUB, NINE_HEART), List.of(), 4),
                Arguments.of(List.of(FIVE_DIAMOND, FIVE_DIAMOND, THREE_SPADE, FIVE_SPADE, QUEEN_HEART), List.of(), 4)
        );
    }

}