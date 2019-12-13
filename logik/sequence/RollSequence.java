package logik.sequence;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import logik.Frame;
import logik.roll.RollPair;

public class RollSequence {

    private static final int SEQUENCE_MIN_SIZE = 10;

    private Frame startFrame;

    RollSequence() {
    }

    private RollSequence(Frame startFrames) {
        this.startFrame = startFrames;
    }

    public static RollSequence parseRawSequence(String rawSequence) {
        validateAgainstNull(rawSequence);
        List<Frame> frames = parseFrames(rawSequence);
        for (int i = 0; i < frames.size() - 1; i++) {
            frames.get(i).setNextFrame(frames.get(i + 1));
        }
        return new RollSequence(frames.get(0));
    }

    private static List<Frame> parseFrames(String rawSequence) {
        String[] splitRawFrames = rawSequence.split(" ");
        List<Frame> frames = Arrays.stream(splitRawFrames).map(rawPair -> new Frame(RollPair.parseRawPair(rawPair))).collect(Collectors.toList());
        if (frames.size() < 10) {
            throw new IllegalArgumentException("This '" + rawSequence + "' ain't a bowling game mon!");
        }
        return frames;
    }

    private static void validateAgainstNull(String rawSequence) {
        if (rawSequence == null) {
            throw new IllegalArgumentException("Given sequence cannot be null!");
        }
    }

    Frame getStartFrame() {
        return startFrame;
    }

    void setStartFrame(Frame startFrames) {
        this.startFrame = startFrames;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RollSequence that = (RollSequence) o;
        return Objects.equals(startFrame, that.startFrame);
    }

    @Override public int hashCode() {
        return Objects.hash(startFrame);
    }
}
