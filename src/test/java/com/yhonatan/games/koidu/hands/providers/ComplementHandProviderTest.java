package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import com.yhonatan.games.koidu.hands.HandCategory;
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
import static com.yhonatan.games.koidu.cards.Card.JACK_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.JACK_HEART;
import static com.yhonatan.games.koidu.cards.Card.JACK_SPADE;
import static com.yhonatan.games.koidu.cards.Card.KING_CLUB;
import static com.yhonatan.games.koidu.cards.Card.KING_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.KING_HEART;
import static com.yhonatan.games.koidu.cards.Card.KING_SPADE;
import static com.yhonatan.games.koidu.cards.Card.NINE_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.NINE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_CLUB;
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
import static com.yhonatan.games.koidu.hands.HandCategory.FULL_HOUSE;
import static com.yhonatan.games.koidu.hands.HandCategory.TWO_PAIRS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComplementHandProviderTest {

    @Mock
    private CardListExtractor firstCardListExtractor;

    @Mock
    private CardListExtractor secondCardListExtractor;

    @ParameterizedTest
    @MethodSource("getPositiveTestArguments")
    void complementHandProviderReturnsComplementedHand(final List<Card> cardList, final List<Card> firstCardList, final List<Card> filteredCardList,
                                                       final List<Card> secondCardList, final List<Card> expectedHandCardList, final HandCategory handCategory) {
        final ComplementHandProvider complementHandProvider = new ComplementHandProvider(firstCardListExtractor, secondCardListExtractor, handCategory);
        when(firstCardListExtractor.extractHand(cardList)).thenReturn(firstCardList);
        when(secondCardListExtractor.extractHand(filteredCardList)).thenReturn(secondCardList);

        Optional<Hand> optionalHand = complementHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(true));
        assertThat(optionalHand.get().getHandCategory(), is(handCategory));
        assertThat(optionalHand.get().getCardList(), is(expectedHandCardList));

        verify(firstCardListExtractor).extractHand(cardList);
        verify(secondCardListExtractor).extractHand(filteredCardList);
        verifyNoMoreInteractions(firstCardListExtractor, secondCardListExtractor);
    }

    @ParameterizedTest
    @MethodSource("getNegativeTestArguments")
    void complementedHandProviderReturnsEmptyHand(final List<Card> cardList, final List<Card> firstCardList, final List<Card> filteredCardList, final HandCategory handCategory) {
        final ComplementHandProvider complementHandProvider = new ComplementHandProvider(firstCardListExtractor, secondCardListExtractor, handCategory);
        when(firstCardListExtractor.extractHand(cardList)).thenReturn(firstCardList);
        when(secondCardListExtractor.extractHand(filteredCardList)).thenReturn(List.of());

        Optional<Hand> optionalHand = complementHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(false));

        verify(firstCardListExtractor).extractHand(cardList);
        verify(secondCardListExtractor).extractHand(filteredCardList);
        verifyNoMoreInteractions(firstCardListExtractor, secondCardListExtractor);
    }

    @ParameterizedTest
    @MethodSource("getNegativeTestArgumentsOnlyFirstExtractorCalled")
    void complementedHandProviderReturnsEmptyHandOnlyFirstExtractorCalled(final List<Card> cardList, final HandCategory handCategory) {
        final ComplementHandProvider complementHandProvider = new ComplementHandProvider(firstCardListExtractor, secondCardListExtractor, handCategory);
        when(firstCardListExtractor.extractHand(cardList)).thenReturn(List.of());

        Optional<Hand> optionalHand = complementHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(false));
        verify(firstCardListExtractor).extractHand(cardList);
        verifyNoMoreInteractions(firstCardListExtractor, secondCardListExtractor);
    }

    private static Stream<Arguments> getPositiveTestArguments() {
        return Stream.of(
                //Full House
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, KING_DIAMOND), List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND),
                        List.of(KING_HEART, KING_DIAMOND), List.of(KING_HEART, KING_DIAMOND),
                        List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, KING_DIAMOND), FULL_HOUSE),
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, KING_SPADE, KING_HEART, KING_DIAMOND), List.of(KING_SPADE, KING_HEART, KING_DIAMOND),
                        List.of(ACE_CLUB, ACE_SPADE), List.of(ACE_CLUB, ACE_SPADE),
                        List.of(KING_SPADE, KING_HEART, KING_DIAMOND, ACE_CLUB, ACE_SPADE), FULL_HOUSE),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, JACK_CLUB, QUEEN_CLUB, JACK_DIAMOND), List.of(JACK_SPADE, JACK_CLUB, JACK_DIAMOND),
                        List.of(QUEEN_HEART, QUEEN_CLUB), List.of(QUEEN_HEART, QUEEN_CLUB),
                        List.of(JACK_SPADE, JACK_CLUB, JACK_DIAMOND, QUEEN_HEART, QUEEN_CLUB), FULL_HOUSE),

                //Two Pairs
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, FIVE_SPADE, KING_HEART, KING_DIAMOND), List.of(ACE_CLUB, ACE_SPADE),
                        List.of(FIVE_SPADE, KING_HEART, KING_DIAMOND), List.of(KING_HEART, KING_DIAMOND),
                        List.of(ACE_CLUB, ACE_SPADE, KING_HEART, KING_DIAMOND), TWO_PAIRS),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, KING_HEART, QUEEN_SPADE), List.of(TWO_SPADE, TWO_CLUB),
                        List.of(KING_SPADE, KING_HEART, QUEEN_SPADE), List.of(KING_SPADE, KING_HEART),
                        List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, KING_HEART), TWO_PAIRS),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, FIVE_SPADE, QUEEN_CLUB, JACK_DIAMOND), List.of(JACK_SPADE, JACK_DIAMOND),
                        List.of(QUEEN_HEART, FIVE_SPADE, QUEEN_CLUB), List.of(QUEEN_HEART, QUEEN_CLUB),
                        List.of(JACK_SPADE, JACK_DIAMOND, QUEEN_HEART, QUEEN_CLUB), TWO_PAIRS)
        );
    }

    private static Stream<Arguments> getNegativeTestArgumentsOnlyFirstExtractorCalled() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, TWO_HEART, THREE_SPADE, SIX_HEART, KING_SPADE), FULL_HOUSE),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, NINE_SPADE, KING_SPADE), FULL_HOUSE),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, TEN_SPADE, KING_DIAMOND), FULL_HOUSE),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, SIX_HEART, KING_SPADE), FULL_HOUSE),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, THREE_SPADE, SIX_HEART, QUEEN_SPADE), FULL_HOUSE),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SIX_HEART, SEVEN_DIAMOND), FULL_HOUSE),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, THREE_SPADE, SIX_HEART, KING_SPADE), FULL_HOUSE),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, SIX_HEART, ACE_HEART), TWO_PAIRS),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, SIX_HEART, JACK_CLUB), TWO_PAIRS),
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART), TWO_PAIRS),
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, THREE_CLUB, FOUR_CLUB, FIVE_CLUB), TWO_PAIRS),
                Arguments.of(List.of(ACE_SPADE, TWO_SPADE, THREE_SPADE, FOUR_SPADE, FIVE_SPADE), TWO_PAIRS),
                Arguments.of(List.of(ACE_HEART, TWO_HEART, THREE_HEART, FOUR_HEART, FIVE_HEART), TWO_PAIRS),
                Arguments.of(List.of(ACE_DIAMOND, TWO_DIAMOND, THREE_DIAMOND, FOUR_DIAMOND, FIVE_DIAMOND), TWO_PAIRS)
        );
    }

    private static Stream<Arguments> getNegativeTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, JACK_DIAMOND), List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND),
                        List.of(KING_HEART, JACK_DIAMOND), FULL_HOUSE),
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, KING_SPADE, KING_HEART, KING_DIAMOND), List.of(KING_SPADE, KING_HEART, KING_DIAMOND),
                        List.of(ACE_CLUB, TWO_CLUB), FULL_HOUSE),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, JACK_CLUB, THREE_SPADE, JACK_DIAMOND), List.of(JACK_SPADE, JACK_CLUB, JACK_DIAMOND),
                        List.of(QUEEN_HEART, THREE_SPADE), FULL_HOUSE),
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, FIVE_SPADE, FOUR_SPADE, KING_DIAMOND), List.of(ACE_CLUB, ACE_SPADE),
                        List.of(FIVE_SPADE, FOUR_SPADE, KING_DIAMOND), TWO_PAIRS),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, TEN_CLUB, QUEEN_SPADE), List.of(TWO_SPADE, TWO_CLUB),
                        List.of(KING_SPADE, TEN_CLUB, QUEEN_SPADE), TWO_PAIRS),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, FIVE_SPADE, KING_HEART, JACK_DIAMOND), List.of(JACK_SPADE, JACK_DIAMOND),
                        List.of(QUEEN_HEART, FIVE_SPADE, KING_HEART), TWO_PAIRS)
        );
    }
}