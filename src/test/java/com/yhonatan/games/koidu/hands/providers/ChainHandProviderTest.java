package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.cards.Card;
import com.yhonatan.games.koidu.hands.Hand;
import org.junit.jupiter.api.BeforeEach;
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
import static com.yhonatan.games.koidu.cards.Card.ACE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FIVE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.FOUR_SPADE;
import static com.yhonatan.games.koidu.cards.Card.JACK_CLUB;
import static com.yhonatan.games.koidu.cards.Card.JACK_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.JACK_SPADE;
import static com.yhonatan.games.koidu.cards.Card.KING_DIAMOND;
import static com.yhonatan.games.koidu.cards.Card.KING_HEART;
import static com.yhonatan.games.koidu.cards.Card.KING_SPADE;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_HEART;
import static com.yhonatan.games.koidu.cards.Card.QUEEN_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TEN_CLUB;
import static com.yhonatan.games.koidu.cards.Card.THREE_SPADE;
import static com.yhonatan.games.koidu.cards.Card.TWO_CLUB;
import static com.yhonatan.games.koidu.cards.Card.TWO_SPADE;
import static com.yhonatan.games.koidu.hands.HandCategory.FULL_HOUSE;
import static com.yhonatan.games.koidu.hands.HandCategory.TWO_PAIRS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChainHandProviderTest {

    @Mock
    private HandProvider firstHandProvider;

    @Mock
    private HandProvider secondHandProvider;

    private ChainHandProvider chainHandProvider;

    @BeforeEach
    void setup() {
        chainHandProvider = new ChainHandProvider(firstHandProvider, secondHandProvider);
    }

    @ParameterizedTest
    @MethodSource("getPositiveTestArguments")
    void chainHandProviderReturnsFirstHandProviderHand(final List<Card> cardList, final Hand firstHandProviderHand) {
        when(firstHandProvider.getHand(cardList)).thenReturn(Optional.of(firstHandProviderHand));

        final Optional<Hand> optionalHand = chainHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(true));
        assertThat(optionalHand.get().getHandCategory(), is(firstHandProviderHand.getHandCategory()));
        assertThat(optionalHand.get().getCardList(), is(firstHandProviderHand.getCardList()));

        verify(firstHandProvider).getHand(cardList);
        verifyNoMoreInteractions(firstHandProvider, secondHandProvider);
    }

    @ParameterizedTest
    @MethodSource("getPositiveTestArguments")
    void chainHandProviderReturnsSecondHandProviderHand(final List<Card> cardList, final Hand secondHandProviderHand) {
        when(firstHandProvider.getHand(cardList)).thenReturn(Optional.empty());
        when(secondHandProvider.getHand(cardList)).thenReturn(Optional.of(secondHandProviderHand));

        final Optional<Hand> optionalHand = chainHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(true));
        assertThat(optionalHand.get().getHandCategory(), is(secondHandProviderHand.getHandCategory()));
        assertThat(optionalHand.get().getCardList(), is(secondHandProviderHand.getCardList()));

        verify(firstHandProvider).getHand(cardList);
        verify(secondHandProvider).getHand(cardList);
        verifyNoMoreInteractions(firstHandProvider, secondHandProvider);
    }

    @ParameterizedTest
    @MethodSource("getNegativeTestArguments")
    void chainHandProviderReturnsEmptyHand(final List<Card> cardList) {
        when(firstHandProvider.getHand(cardList)).thenReturn(Optional.empty());
        when(secondHandProvider.getHand(cardList)).thenReturn(Optional.empty());

        final Optional<Hand> optionalHand = chainHandProvider.getHand(cardList);

        assertThat(optionalHand.isPresent(), is(false));

        verify(firstHandProvider).getHand(cardList);
        verify(secondHandProvider).getHand(cardList);
        verifyNoMoreInteractions(firstHandProvider, secondHandProvider);
    }

    private static Stream<Arguments> getPositiveTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, KING_DIAMOND),
                        new Hand(FULL_HOUSE, List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, KING_DIAMOND))),
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, KING_SPADE, KING_HEART, KING_DIAMOND),
                        new Hand(FULL_HOUSE, List.of(KING_SPADE, KING_HEART, KING_DIAMOND, ACE_CLUB, ACE_SPADE))),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, JACK_CLUB, QUEEN_CLUB, JACK_DIAMOND),
                        new Hand(FULL_HOUSE, List.of(JACK_SPADE, JACK_CLUB, JACK_DIAMOND, QUEEN_HEART, QUEEN_CLUB))),
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, FIVE_SPADE, KING_HEART, KING_DIAMOND),
                        new Hand(TWO_PAIRS, List.of(ACE_CLUB, ACE_SPADE, KING_HEART, KING_DIAMOND))),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, KING_HEART, QUEEN_SPADE),
                        new Hand(TWO_PAIRS, List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, KING_HEART))),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, FIVE_SPADE, QUEEN_CLUB, JACK_DIAMOND),
                        new Hand(TWO_PAIRS, List.of(JACK_SPADE, JACK_DIAMOND, QUEEN_HEART, QUEEN_CLUB)))
        );
    }

    private static Stream<Arguments> getNegativeTestArguments() {
        return Stream.of(
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, ACE_DIAMOND, KING_HEART, JACK_DIAMOND)),
                Arguments.of(List.of(ACE_CLUB, TWO_CLUB, KING_SPADE, KING_HEART, KING_DIAMOND)),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, JACK_CLUB, THREE_SPADE, JACK_DIAMOND)),
                Arguments.of(List.of(ACE_CLUB, ACE_SPADE, FIVE_SPADE, FOUR_SPADE, KING_DIAMOND)),
                Arguments.of(List.of(TWO_SPADE, TWO_CLUB, KING_SPADE, TEN_CLUB, QUEEN_SPADE)),
                Arguments.of(List.of(JACK_SPADE, QUEEN_HEART, FIVE_SPADE, KING_HEART, JACK_DIAMOND))
        );
    }
}