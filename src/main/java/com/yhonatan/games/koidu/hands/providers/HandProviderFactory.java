package com.yhonatan.games.koidu.hands.providers;

import com.yhonatan.games.koidu.hands.extractors.MaxCardListExtractor;
import com.yhonatan.games.koidu.hands.extractors.RankCardListExtractor;
import com.yhonatan.games.koidu.hands.extractors.SequenceCardListExtractor;
import com.yhonatan.games.koidu.hands.extractors.SuitCardListExtractor;

import static com.yhonatan.games.koidu.hands.HandCategory.FLUSH;
import static com.yhonatan.games.koidu.hands.HandCategory.FOUR_KIND;
import static com.yhonatan.games.koidu.hands.HandCategory.FULL_HOUSE;
import static com.yhonatan.games.koidu.hands.HandCategory.HIGH_CARD;
import static com.yhonatan.games.koidu.hands.HandCategory.PAIR;
import static com.yhonatan.games.koidu.hands.HandCategory.STRAIGHT;
import static com.yhonatan.games.koidu.hands.HandCategory.STRAIGHT_FLUSH;
import static com.yhonatan.games.koidu.hands.HandCategory.THREE_KIND;
import static com.yhonatan.games.koidu.hands.HandCategory.TWO_PAIRS;

public class HandProviderFactory {

    private HandProviderFactory() {
    }

    public static HandProvider getChainHandProvider() {
        return new ChainHandProvider(getStraightFlushHandProvider(),
                new ChainHandProvider(getFourKindHandProvider(),
                        new ChainHandProvider(getFullHouseHandProvider(),
                                new ChainHandProvider(getFlushHandProvider(),
                                        new ChainHandProvider(getStraightHandProvider(),
                                                new ChainHandProvider(getThreeKindHandProvider(),
                                                        new ChainHandProvider(getTwoPairsHandProvider(),
                                                                new ChainHandProvider(getPairHandProvider(),
                                                                        getHighCardHandProvider()))))))));
    }

    private static HandProvider getHighCardHandProvider() {
        final MaxCardListExtractor maxCardListExtractor = new MaxCardListExtractor();
        return new ExtractorDelegateHandProvider(maxCardListExtractor, HIGH_CARD);
    }

    private static HandProvider getPairHandProvider() {
        final RankCardListExtractor rankCardListExtractor = new RankCardListExtractor(2);
        return new ExtractorDelegateHandProvider(rankCardListExtractor, PAIR);
    }

    private static HandProvider getTwoPairsHandProvider() {
        final RankCardListExtractor pairRankCardListExtractor = new RankCardListExtractor(2);
        return new ComplementHandProvider(pairRankCardListExtractor, pairRankCardListExtractor, TWO_PAIRS);
    }

    private static HandProvider getThreeKindHandProvider() {
        final RankCardListExtractor rankCardListExtractor = new RankCardListExtractor(3);
        return new ExtractorDelegateHandProvider(rankCardListExtractor, THREE_KIND);
    }

    private static HandProvider getStraightHandProvider() {
        final SequenceCardListExtractor sequenceCardListExtractor = new SequenceCardListExtractor();
        return new ExtractorDelegateHandProvider(sequenceCardListExtractor, STRAIGHT);
    }

    private static HandProvider getFlushHandProvider() {
        final SuitCardListExtractor suitCardListExtractor = new SuitCardListExtractor();
        return new ExtractorDelegateHandProvider(suitCardListExtractor, FLUSH);
    }

    private static HandProvider getFullHouseHandProvider() {
        final RankCardListExtractor firstRankCardListExtractor = new RankCardListExtractor(3);
        final RankCardListExtractor secondRankCardListExtractor = new RankCardListExtractor(2);
        return new ComplementHandProvider(firstRankCardListExtractor, secondRankCardListExtractor, FULL_HOUSE);
    }

    private static HandProvider getFourKindHandProvider() {
        final RankCardListExtractor rankCardListExtractor = new RankCardListExtractor(4);
        return new ExtractorDelegateHandProvider(rankCardListExtractor, FOUR_KIND);
    }

    private static HandProvider getStraightFlushHandProvider() {
        final SequenceCardListExtractor sequenceCardListExtractor = new SequenceCardListExtractor();
        final SuitCardListExtractor suitCardListExtractor = new SuitCardListExtractor();
        return new DoubleMatchHandProvider(sequenceCardListExtractor, suitCardListExtractor, STRAIGHT_FLUSH);
    }


}
