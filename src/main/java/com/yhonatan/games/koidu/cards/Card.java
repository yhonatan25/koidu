package com.yhonatan.games.koidu.cards;

import static com.yhonatan.games.koidu.cards.Rank.ACE;
import static com.yhonatan.games.koidu.cards.Rank.EIGHT;
import static com.yhonatan.games.koidu.cards.Rank.FIVE;
import static com.yhonatan.games.koidu.cards.Rank.FOUR;
import static com.yhonatan.games.koidu.cards.Rank.JACK;
import static com.yhonatan.games.koidu.cards.Rank.KING;
import static com.yhonatan.games.koidu.cards.Rank.NINE;
import static com.yhonatan.games.koidu.cards.Rank.QUEEN;
import static com.yhonatan.games.koidu.cards.Rank.SEVEN;
import static com.yhonatan.games.koidu.cards.Rank.SIX;
import static com.yhonatan.games.koidu.cards.Rank.TEN;
import static com.yhonatan.games.koidu.cards.Rank.THREE;
import static com.yhonatan.games.koidu.cards.Rank.TWO;
import static com.yhonatan.games.koidu.cards.Suit.CLUB;
import static com.yhonatan.games.koidu.cards.Suit.DIAMOND;
import static com.yhonatan.games.koidu.cards.Suit.HEART;
import static com.yhonatan.games.koidu.cards.Suit.SPADE;

public enum Card {

    ACE_CLUB(ACE, CLUB),
    TWO_CLUB(TWO, CLUB),
    THREE_CLUB(THREE, CLUB),
    FOUR_CLUB(FOUR, CLUB),
    FIVE_CLUB(FIVE, CLUB),
    SIX_CLUB(SIX, CLUB),
    SEVEN_CLUB(SEVEN, CLUB),
    EIGHT_CLUB(EIGHT, CLUB),
    NINE_CLUB(NINE, CLUB),
    TEN_CLUB(TEN, CLUB),
    JACK_CLUB(JACK, CLUB),
    QUEEN_CLUB(QUEEN, CLUB),
    KING_CLUB(KING, CLUB),

    ACE_DIAMOND(ACE, DIAMOND),
    TWO_DIAMOND(TWO, DIAMOND),
    THREE_DIAMOND(THREE, DIAMOND),
    FOUR_DIAMOND(FOUR, DIAMOND),
    FIVE_DIAMOND(FIVE, DIAMOND),
    SIX_DIAMOND(SIX, DIAMOND),
    SEVEN_DIAMOND(SEVEN, DIAMOND),
    EIGHT_DIAMOND(EIGHT, DIAMOND),
    NINE_DIAMOND(NINE, DIAMOND),
    TEN_DIAMOND(TEN, DIAMOND),
    JACK_DIAMOND(JACK, DIAMOND),
    QUEEN_DIAMOND(QUEEN, DIAMOND),
    KING_DIAMOND(KING, DIAMOND),

    ACE_HEART(ACE, HEART),
    TWO_HEART(TWO, HEART),
    THREE_HEART(THREE, HEART),
    FOUR_HEART(FOUR, HEART),
    FIVE_HEART(FIVE, HEART),
    SIX_HEART(SIX, HEART),
    SEVEN_HEART(SEVEN, HEART),
    EIGHT_HEART(EIGHT, HEART),
    NINE_HEART(NINE, HEART),
    TEN_HEART(TEN, HEART),
    JACK_HEART(JACK, HEART),
    QUEEN_HEART(QUEEN, HEART),
    KING_HEART(KING, HEART),

    ACE_SPADE(ACE, SPADE),
    TWO_SPADE(TWO, SPADE),
    THREE_SPADE(THREE, SPADE),
    FOUR_SPADE(FOUR, SPADE),
    FIVE_SPADE(FIVE, SPADE),
    SIX_SPADE(SIX, SPADE),
    SEVEN_SPADE(SEVEN, SPADE),
    EIGHT_SPADE(EIGHT, SPADE),
    NINE_SPADE(NINE, SPADE),
    TEN_SPADE(TEN, SPADE),
    JACK_SPADE(JACK, SPADE),
    QUEEN_SPADE(QUEEN, SPADE),
    KING_SPADE(KING, SPADE);

    private final Rank rank;
    private final Suit suit;

    Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
