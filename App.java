import logik.sequence.RollSequence;
import logik.sequence.SequenceAnalyzer;

public class App {

    public static void main(String[] args) {
        SequenceAnalyzer sequenceAnalyzer = new SequenceAnalyzer();
        String joined = String.join(" ", args);
        System.out.println(sequenceAnalyzer.analyzeSequence(RollSequence.parseRawSequence(joined)));
    }
}
