package logik.roll.strategy;

import logik.roll.RollPair;

public class SimpleRollStrategy implements RollStrategy{

    @Override public int calculateFrameScore(RollPair current, RollPair first, RollPair second) {
        return current.getAllKnocked();
    }
}
