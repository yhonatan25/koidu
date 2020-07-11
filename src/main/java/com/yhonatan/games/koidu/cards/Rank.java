package com.yhonatan.games.koidu.cards;

public enum Rank {
    ACE("A", 14),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("k", 13);

    private final String symbol;
    private final int rank;

    Rank(final String symbol, final int rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getRank() {
        return rank;
    }
}
