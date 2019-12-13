package logik;

import java.util.Objects;

import logik.roll.RollPair;

public class Frame {

    private Frame nextFrame;
    private RollPair rollPair;

    public Frame(Frame nextFrame, RollPair rollPair) {
        this.nextFrame = nextFrame;
        this.rollPair = rollPair;
    }

    public Frame(RollPair rollPair) {
        this.rollPair = rollPair;
    }

    public RollPair getRollPair() {
        return rollPair;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    public RollPair getNextTryPair() {
        return nextFrame != null ? nextFrame.getRollPair() : null;
    }

    public RollPair getNexterestesterestTryPair() {
        return nextFrame != null ? nextFrame.getNextTryPair() : null;
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frame frame = (Frame) o;
        return Objects.equals(nextFrame, frame.nextFrame) &&
            Objects.equals(rollPair, frame.rollPair);
    }

    @Override public int hashCode() {
        return Objects.hash(nextFrame, rollPair);
    }
}
