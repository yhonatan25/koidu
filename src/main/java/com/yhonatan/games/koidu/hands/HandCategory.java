package com.yhonatan.games.koidu.hands;

public enum HandCategory {
    STRAIGHT_FLUSH("Straight Flush"),
    FOUR_KIND("Four of a Kind"),
    FULL_HOUSE("Full House"),
    FLUSH("Flush"),
    STRAIGHT("Straight"),
    THREE_KIND("Three of a Kind"),
    TWO_PAIRS("Two Pairs"),
    PAIR("Pair"),
    HIGH_CARD("High Card");

    private final String name;

    HandCategory(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
