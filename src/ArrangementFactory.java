import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class ArrangementFactory extends AbstractCandidateFactory<String> {
    private final char[] alphabet;
    private final int stringLength;

    public ArrangementFactory(char[] alphabet, int stringLength) {
        this.alphabet = (char[])alphabet.clone();
        this.stringLength = stringLength;
    }

    public String generateRandomCandidate(Random rng) {
        char[] chars = new char[this.stringLength];

        for(int i = 0; i < this.stringLength; ++i) {
            chars[i] = this.alphabet[rng.nextInt(this.alphabet.length)];
        }

        return new String(chars);
    }
}