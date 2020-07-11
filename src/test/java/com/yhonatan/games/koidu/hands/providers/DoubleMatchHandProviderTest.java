package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.yhonatan.games.koidu.cards.Card.ACE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.ACE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.ACE_HEART;
import static com.yhonatan.games.koidu.cards.Card.ACE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FIVE_CLUB;
import static com.yhonatan.games.koidu.cards.Card.FIVE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.FIVE_HEART;
import static com.yhonatan.games.koidu.cards.Card.FIVE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FOUR_CLUB;
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
import static com.yhonatan.games.koidu.cards.Card.THREE_HEART;
import static com.yhonatan.games.koidu.cards.Card.THREE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TWO_CLUB;
import static com.yhonatan.games.koidu.cards.Card.TWO_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.TWO_HEART;
import static com.yhonatan.games.koidu.cards.Card.TWO_SPADE;
import static com.yhonatan.games.koidu.hands.HandCategory.STRAIGHT_FLUSH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoubleMatchHandProviderTest {

    @Mock
    private CardListExtractor firstCardListExtractor;

    @Mock
    private CardListExtractor secondCardListExtractor;

    @ParameterizedTest
    @MethodSource("getPositiveTestArguments")
    void doubleMatchHandProvidersReturnsHand(final List<Card> cardList) {
        final DoubleMatchHandProvider doubleMatchHandProvider = new DoubleMatchHandProvider(firstCardListExtractor, secondCardListExtractor, STRAIGHT_FLUSH);
        when(firstCardListExtractor.extractHand(cardList)).thenReturn(cardList);
        when(secondCardListExtractor.extractHand(cardList)).thenReturn(cardList);

        Optional<Hand> optionalHand = doubleMatchHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(true));
        assertThat(optionalHand.get().getHandCategory(), is(STRAIGHT_FLUSH));
        assertThat(optionalHand.get().getCardList(), is(cardList));

        verify(firstCardListExtractor).extractHand(cardList);
        verify(secondCardListExtractor).extractHand(cardList);
        verifyNoMoreInteractions(firstCardListExtractor, secondCardListExtractor);
    }

    @ParameterizedTest
    @MethodSource("getNegativeTestArguments")
    void doubleMatchHandProviderReturnsEmptyHand(final List<Card> cardList, final List<Card> firstCardList, final List<Card> secondCardList) {
        final DoubleMatchHandProvider doubleMatchHandProvider = new DoubleMatchHandProvider(firstCardListExtractor, secondCardListExtractor, STRAIGHT_FLUSH);
        when(firstCardListExtractor.extractHand(cardList)).thenReturn(firstCardList);
        when(secondCardListExtractor.extractHand(cardList)).thenReturn(secondCardList);

        Optional<Hand> optionalHand = doubleMatchHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(false));

        verify(firstCardListExtractor).extractHand(cardList);
        verify(secondCardListExtractor).extractHand(cardList);
        verifyNoMoreInteractions(firstCardListExtractor, secondCardListExtractor);
    }

    @ParameterizedTest
    @MethodSource("getNegativeTestArgumentsOnlyFirstExtractorCalled")
    void doubleMatchHandProviderReturnsEmptyHand(final List<Card> cardList) {
        final DoubleMatchHandProvider doubleMatchHandProvider = new DoubleMatchHandProvider(firstCardListExtractor, secondCardListExtractor, STRAIGHT_FLUSH);
        when(firstCardListExtractor.extractHand(cardList)).thenReturn(List.of());

        Optional<Hand> optionalHand = doubleMatchHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(false));
        verify(firstCardListExtractor).extractHand(cardList);
        verifyNoMoreInteractions(firstCardListExtractor, secondCardListExtractor);
    }

    private static Stream<Arguments> getPositiveTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, THREE_CLUB, FOUR_CLUB, FIVE_CLUB)),
                Arguments.of(List.of(ACE_SPADE, TWO_SPADE, THREE_SPADE, FOUR_SPADE, FIVE_SPADE)),
                Arguments.of(List.of(ACE_HEART, TWO_HEART, THREE_HEART, FOUR_HEART, FIVE_HEART)),
                Arguments.of(List.of(ACE_DIAMOND, TWO_DIAMOND, THREE_DIAMOND, FOUR_DIAMOND, FIVE_DIAMOND))
        );
    }

    private static Stream<Arguments> getNegativeTestArgumentsOnlyFirstExtractorCalled() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, TWO_HEART, THREE_SPADE, SIX_HEART, KING_SPADE)),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, NINE_SPADE, KING_SPADE)),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, TEN_SPADE, KING_DIAMOND)),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, SIX_HEART, KING_SPADE)),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, THREE_SPADE, SIX_HEART, QUEEN_SPADE)),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SIX_HEART, SEVEN_DIAMOND)),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, THREE_SPADE, SIX_HEART, KING_SPADE)),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, SIX_HEART, ACE_HEART)),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, SIX_HEART, JACK_CLUB)),
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART)),
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, THREE_CLUB, FOUR_CLUB, FIVE_CLUB)),
                Arguments.of(List.of(ACE_SPADE, TWO_SPADE, THREE_SPADE, FOUR_SPADE, FIVE_SPADE)),
                Arguments.of(List.of(ACE_HEART, TWO_HEART, THREE_HEART, FOUR_HEART, FIVE_HEART)),
                Arguments.of(List.of(ACE_DIAMOND, TWO_DIAMOND, THREE_DIAMOND, FOUR_DIAMOND, FIVE_DIAMOND))
        );
    }

    private static Stream<Arguments> getNegativeTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, THREE_CLUB, FOUR_CLUB, FIVE_CLUB), List.of(ACE_CLUB, TWO_CLUB, THREE_CLUB, FOUR_CLUB, FIVE_CLUB), List.of()),
                Arguments.of(List.of(ACE_SPADE, TWO_SPADE, THREE_SPADE, FOUR_SPADE, FIVE_SPADE), List.of(ACE_SPADE, TWO_SPADE, THREE_SPADE, FOUR_SPADE, FIVE_SPADE), List.of()),
                Arguments.of(List.of(ACE_HEART, TWO_HEART, THREE_HEART, FOUR_HEART, FIVE_HEART), List.of(ACE_HEART, TWO_HEART, THREE_HEART, FOUR_HEART, FIVE_HEART), List.of()),
                Arguments.of(List.of(ACE_DIAMOND, TWO_DIAMOND, THREE_DIAMOND, FOUR_DIAMOND, FIVE_DIAMOND), List.of(ACE_DIAMOND, TWO_DIAMOND, THREE_DIAMOND, FOUR_DIAMOND, FIVE_DIAMOND), List.of())
        );
    }
}