package logik.roll;

import java.util.Arrays;

import logik.roll.strategy.RollStrategy;
import logik.roll.strategy.SimpleRollStrategy;
import logik.roll.strategy.SpareRollStrategy;
import logik.roll.strategy.StrikeRollStrategy;

public enum RollType {

    NUMBER("^\\d$", new SimpleRollStrategy()) {
        @Override
        public int getDefaultKnockValue(String identifierCharacter, String optionalOther) {
            return Integer.valueOf(identifierCharacter);
        }
    },
    MISS("-", new SimpleRollStrategy()) {
        @Override
        public int getDefaultKnockValue(String identifierCharacter, String optionalOther) {
            return 0;
        }
    },
    SPARE("/", new SpareRollStrategy()) {
        @Override
        public int getDefaultKnockValue(String identifierCharacter, String optionalOther) {
            return 10 - getByIdentifierCharacter(optionalOther).getDefaultKnockValue(optionalOther, null);
        }
    },
    STRIKE("x", new StrikeRollStrategy()) {
        @Override
        public int getDefaultKnockValue(String identifierCharacter, String optionalOther) {
            return 10;
        }
    };

    private String identifierCharacter;
    private RollStrategy rollStrategy;

    RollType(String identifierCharacter, RollStrategy rollStrategy) {
        this.identifierCharacter = identifierCharacter;
        this.rollStrategy = rollStrategy;
    }

    public static RollType getByIdentifierCharacter(String identifierCharacter) {
        if (identifierCharacter == null) {
            throw new IllegalArgumentException("Identifier character cannot be null");
        }
        return Arrays.stream(RollType.values()).filter(tryType -> identifierCharacter.matches(tryType.identifierCharacter)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public abstract int getDefaultKnockValue(String identifierCharacter, String optionalOther);

    public RollStrategy getRollStrategy() {
        return rollStrategy;
    }
}
