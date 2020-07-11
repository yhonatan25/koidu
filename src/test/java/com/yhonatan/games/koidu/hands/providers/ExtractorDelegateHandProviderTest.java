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
import static com.yhonatan.games.koidu.hands.HandCategory.FLUSH;
import static com.yhonatan.games.koidu.hands.HandCategory.FOUR_KIND;
import static com.yhonatan.games.koidu.hands.HandCategory.HIGH_CARD;
import static com.yhonatan.games.koidu.hands.HandCategory.PAIR;
import static com.yhonatan.games.koidu.hands.HandCategory.STRAIGHT;
import static com.yhonatan.games.koidu.hands.HandCategory.THREE_KIND;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExtractorDelegateHandProviderTest {

    @Mock
    private CardListExtractor cardListExtractor;

    @ParameterizedTest
    @MethodSource("getPositiveTestArguments")
    void extractorDelegateHandProviderReturnsDelegatedHand(final List<Card> cardList, final HandCategory handCategory) {
        final ExtractorDelegateHandProvider extractorDelegateHandProvider = new ExtractorDelegateHandProvider(cardListExtractor, handCategory);
        final List<Card> expectedHandCardList = List.of(ACE_SPADE);
        when(cardListExtractor.extractHand(cardList)).thenReturn(expectedHandCardList);

        Optional<Hand> optionalHand = extractorDelegateHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(true));
        assertThat(optionalHand.get().getHandCategory(), is(handCategory));
        assertThat(optionalHand.get().getCardList(), is(expectedHandCardList));

        verify(cardListExtractor).extractHand(cardList);
        verifyNoMoreInteractions(cardListExtractor);
    }

    @ParameterizedTest
    @MethodSource("getNegativeTestArguments")
    void extractorDelegateHandProviderReturnsEmptyHand(final List<Card> cardList) {
        final ExtractorDelegateHandProvider extractorDelegateHandProvider = new ExtractorDelegateHandProvider(cardListExtractor, FOUR_KIND);
        when(cardListExtractor.extractHand(cardList)).thenReturn(List.of());

        Optional<Hand> optionalHand = extractorDelegateHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(false));

        verify(cardListExtractor).extractHand(cardList);
        verifyNoMoreInteractions(cardListExtractor);
    }

    private static Stream<Arguments> getPositiveTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, TWO_HEART, THREE_SPADE, SIX_HEART, KING_SPADE), HIGH_CARD),
                Arguments.of(List.of(QUEEN_HEART, TEN_SPADE, FOUR_DIAMOND, NINE_SPADE, KING_SPADE), PAIR),
                Arguments.of(List.of(JACK_CLUB, JACK_HEART, QUEEN_SPADE, TEN_SPADE, KING_DIAMOND), THREE_KIND),
                Arguments.of(List.of(FIVE_CLUB, KING_DIAMOND, THREE_SPADE, SIX_HEART, JACK_CLUB), FOUR_KIND),
                Arguments.of(List.of(FOUR_HEART, TWO_HEART, ACE_DIAMOND, SIX_HEART, KING_SPADE), FLUSH),
                Arguments.of(List.of(KING_CLUB, JACK_SPADE, THREE_SPADE, SIX_HEART, QUEEN_SPADE), STRAIGHT),
                Arguments.of(List.of(FIVE_DIAMOND, TEN_CLUB, THREE_SPADE, SIX_HEART, SEVEN_DIAMOND), HIGH_CARD),
                Arguments.of(List.of(THREE_DIAMOND, NINE_DIAMOND, THREE_SPADE, SIX_HEART, KING_SPADE), PAIR),
                Arguments.of(List.of(FOUR_SPADE, JACK_HEART, THREE_SPADE, SIX_HEART, ACE_HEART), THREE_KIND),
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART), FOUR_KIND)
        );
    }

    private static Stream<Arguments> getNegativeTestArguments() {
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
                Arguments.of(List.of(JACK_HEART, THREE_CLUB, THREE_SPADE, SIX_HEART, QUEEN_HEART))
        );
    }
}