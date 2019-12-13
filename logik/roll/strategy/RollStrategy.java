package logik.roll.strategy;

import logik.roll.RollPair;

public interface RollStrategy {

    int calculateFrameScore(RollPair current, RollPair first, RollPair second);
}
