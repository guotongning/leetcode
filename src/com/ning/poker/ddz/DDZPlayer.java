package com.ning.poker.ddz;

import com.ning.poker.base.BasePlayer;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.utils.PokerUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class DDZPlayer extends BasePlayer {

    private final Poker[] pokers = new Poker[17];
    private final AtomicInteger handsCount = new AtomicInteger(0);

    public DDZPlayer(String name) {
        super(name);
    }

    @Override
    public void addHand(Poker poker) {
        pokers[handsCount.getAndIncrement()] = poker;
    }

    @Override
    public PokerGroup play(Poker[] selectedPokers) {
        PokerUtils.sort(selectedPokers, true);
        int i = 0, j = 0;
        while (i < pokers.length && j < selectedPokers.length) {
            Poker hand = pokers[i];
            Poker selectedHand = selectedPokers[j];
            if (hand.equals(selectedHand)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        if (j < selectedPokers.length - 1) {
            return null;
        }
        PokerGroup pokerGroup = DDZPokerGroupFactory.getInstance().create(selectedPokers);
        int m = 0, n = 0;
        if (pokerGroup != null) {
            while (m < pokers.length && n < selectedPokers.length) {
                Poker hand = pokers[m];
                Poker selectedHand = selectedPokers[n];
                if (hand.equals(selectedHand)) {
                    pokers[m++] = null;
                    n++;
                } else {
                    m++;
                }
            }
            handsCount.set(handsCount.get() - selectedPokers.length);
        }
        return pokerGroup;
    }

}
