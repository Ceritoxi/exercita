package logik.sequence;

import logik.Frame;
import logik.Score;

public class SequenceAnalyzer {
    public Score analyzeSequence(RollSequence rollSequence) {
        if (rollSequence == null) {
            throw new IllegalArgumentException("Roll sequence cannot be null");
        }
        int finalScore = 0;
        Frame currentFrame = rollSequence.getStartFrame();
        int counter = 10;
        while (currentFrame != null && counter > 0) {
            finalScore += currentFrame.getRollPair().getFinalRollType().getRollStrategy().calculateFrameScore(currentFrame.getRollPair(), currentFrame.getNextTryPair(), currentFrame.getNexterestesterestTryPair());
            currentFrame = currentFrame.getNextFrame();
            counter--;
        }
        return Score.of(finalScore);
    }
}
