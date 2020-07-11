package com.yhonatan.games.koidu;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.HandCategory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.yhonatan.games.koidu.cards.Card.ACE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.ACE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.ACE_HEART;
import static com.yhonatan.games.koidu.cards.Card.ACE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FIVE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FIVE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FOUR_CLUB;
import static com.yhonatan.games.koidu.cards.Card.FOUR_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FOUR_SPADE;
import static com.yhonatan.games.koidu.cards.Card.JACK_CLUB;
import static com.yhonatan.games.koidu.cards.Card.JACK_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.JACK_SPADE;
import static com.yhonatan.games.koidu.cards.Card.KING_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.KING_HEART;
import static com.yhonatan.games.koidu.cards.Card.KING_SPADE;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.THREE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.THREE_HEART;
import static com.yhonatan.games.koidu.cards.Card.THREE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TWO_CLUB;
import static com.yhonatan.games.koidu.cards.Card.TWO_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.TWO_HEART;
import static com.yhonatan.games.koidu.cards.Card.TWO_SPADE;
import static com.yhonatan.games.koidu.hands.HandCategory.FLUSH;
import static com.yhonatan.games.koidu.hands.HandCategory.FOUR_KIND;
import static com.yhonatan.games.koidu.hands.HandCategory.FULL_HOUSE;
import static com.yhonatan.games.koidu.hands.HandCategory.HIGH_CARD;
import static com.yhonatan.games.koidu.hands.HandCategory.PAIR;
import static com.yhonatan.games.koidu.hands.HandCategory.STRAIGHT;
import static com.yhonatan.games.koidu.hands.HandCategory.STRAIGHT_FLUSH;
import static com.yhonatan.games.koidu.hands.HandCategory.THREE_KIND;
import static com.yhonatan.games.koidu.hands.HandCategory.TWO_PAIRS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class KoiduIntegrationTest {

    private static final Koidu KOIDU = Koidu.getInstance();

    @ParameterizedTest
    @MethodSource("getTestArguments")
    void testGetHand(final List<Card> cardList, final List<Card> handCardList, final HandCategory handCategory) {
        final Hand hand = KOIDU.getHand(cardList);

        assertThat(hand.getHandCategory(), is(handCategory));
        assertThat(hand.getCardList(), is(handCardList));
    }

    private static Stream<Arguments> getTestArguments() {
        return Stream.of(
                //Straight Flush
                Arguments.of(List.of(TEN_SPADE, JACK_SPADE, QUEEN_SPADE, KING_SPADE, ACE_SPADE),
                        List.of(TEN_SPADE, JACK_SPADE, QUEEN_SPADE, KING_SPADE, ACE_SPADE), STRAIGHT_FLUSH),
                Arguments.of(List.of(TWO_DIAMOND, THREE_DIAMOND, FIVE_DIAMOND, FOUR_DIAMOND, ACE_DIAMOND),
                        List.of(TWO_DIAMOND, THREE_DIAMOND, FIVE_DIAMOND, FOUR_DIAMOND, ACE_DIAMOND), STRAIGHT_FLUSH),
                //Four of a Kind
                Arguments.of(List.of(ACE_HEART, ACE_DIAMOND, QUEEN_SPADE, ACE_CLUB, ACE_SPADE),
                        List.of(ACE_HEART, ACE_DIAMOND, ACE_CLUB, ACE_SPADE), FOUR_KIND),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, TWO_DIAMOND, TWO_HEART, ACE_SPADE),
                        List.of(TWO_SPADE, TWO_CLUB, TWO_DIAMOND, TWO_HEART), FOUR_KIND),
                //Full House
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, KING_DIAMOND),
                        List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, KING_DIAMOND), FULL_HOUSE),
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, KING_SPADE, KING_HEART, KING_DIAMOND),
                        List.of(KING_SPADE, KING_HEART, KING_DIAMOND, ACE_CLUB, ACE_SPADE), FULL_HOUSE),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, JACK_CLUB, QUEEN_CLUB, JACK_DIAMOND),
                        List.of(JACK_SPADE, JACK_CLUB, JACK_DIAMOND, QUEEN_HEART, QUEEN_CLUB), FULL_HOUSE),
                //Flush
                Arguments.of(List.of(TEN_SPADE, JACK_SPADE, QUEEN_SPADE, TWO_SPADE, ACE_SPADE),
                        List.of(TEN_SPADE, JACK_SPADE, QUEEN_SPADE, TWO_SPADE, ACE_SPADE), FLUSH),
                Arguments.of(List.of(TWO_DIAMOND, THREE_DIAMOND, FIVE_DIAMOND, KING_DIAMOND, ACE_DIAMOND),
                        List.of(TWO_DIAMOND, THREE_DIAMOND, FIVE_DIAMOND, KING_DIAMOND, ACE_DIAMOND), FLUSH),
                //Straight
                Arguments.of(List.of(TEN_SPADE, JACK_CLUB, QUEEN_DIAMOND, KING_DIAMOND, ACE_SPADE),
                        List.of(TEN_SPADE, JACK_CLUB, QUEEN_DIAMOND, KING_DIAMOND, ACE_SPADE), STRAIGHT),
                Arguments.of(List.of(TWO_HEART, THREE_DIAMOND, FIVE_SPADE, FOUR_SPADE, ACE_DIAMOND),
                        List.of(TWO_HEART, THREE_DIAMOND, FIVE_SPADE, FOUR_SPADE, ACE_DIAMOND), STRAIGHT),
                //Three of a Kind
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, QUEEN_CLUB),
                        List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND), THREE_KIND),
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, KING_SPADE, KING_HEART, KING_DIAMOND),
                        List.of(KING_SPADE, KING_HEART, KING_DIAMOND), THREE_KIND),
                Arguments.of(List.of(JACK_SPADE, KING_SPADE, JACK_CLUB, QUEEN_CLUB, JACK_DIAMOND),
                        List.of(JACK_SPADE, JACK_CLUB, JACK_DIAMOND), THREE_KIND),
                //Two Pairs
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, FIVE_SPADE, KING_HEART, KING_DIAMOND),
                        List.of(ACE_CLUB, ACE_SPADE, KING_HEART, KING_DIAMOND), TWO_PAIRS),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, KING_HEART, QUEEN_SPADE),
                        List.of(KING_SPADE, KING_HEART, TWO_SPADE, TWO_CLUB), TWO_PAIRS),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, FIVE_SPADE, QUEEN_CLUB, JACK_DIAMOND),
                        List.of(QUEEN_HEART, QUEEN_CLUB, JACK_SPADE, JACK_DIAMOND), TWO_PAIRS),
                //Pair
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, FIVE_SPADE, FOUR_CLUB, KING_DIAMOND),
                        List.of(ACE_CLUB, ACE_SPADE), PAIR),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, ACE_SPADE, KING_HEART, QUEEN_SPADE),
                        List.of(TWO_SPADE, TWO_CLUB), PAIR),
                Arguments.of(List.of(JACK_SPADE, ACE_DIAMOND, FIVE_SPADE, QUEEN_CLUB, JACK_DIAMOND),
                        List.of(JACK_SPADE, JACK_DIAMOND), PAIR),
                //High Card
                Arguments.of(List.of(ACE_CLUB, QUEEN_SPADE, FIVE_SPADE, FOUR_CLUB, KING_DIAMOND),
                        List.of(ACE_CLUB), HIGH_CARD),
                Arguments.of(List.of(TWO_SPADE, THREE_HEART, JACK_SPADE, KING_HEART, QUEEN_SPADE),
                        List.of(KING_HEART), HIGH_CARD),
                Arguments.of(List.of(JACK_SPADE, THREE_SPADE, FIVE_SPADE, QUEEN_CLUB, FOUR_SPADE),
                        List.of(QUEEN_CLUB), HIGH_CARD)
        );
    }
}
