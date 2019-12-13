package logik.roll.strategy;

import logik.roll.RollPair;

public class SpareRollStrategy implements RollStrategy {

    @Override public int calculateFrameScore(RollPair current, RollPair first, RollPair second) {
        if (first == null) {
            throw new IllegalArgumentException("Sum Ting Wong");
        }
        return current.getAllKnocked() + first.getMainKnocked();
    }
}
