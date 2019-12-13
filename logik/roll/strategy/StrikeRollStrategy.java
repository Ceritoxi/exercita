package logik.roll.strategy;

import logik.roll.RollPair;

public class StrikeRollStrategy implements RollStrategy {

    @Override public int calculateFrameScore(RollPair current, RollPair first, RollPair second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Sum Ting Wong");
        }
        return current.getAllKnocked() + (first.isOptionalThere() ? first.getAllKnocked() : first.getMainKnocked() + second.getMainKnocked());
    }
}
