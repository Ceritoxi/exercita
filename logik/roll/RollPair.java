package logik.roll;

import java.util.Objects;

public class RollPair {
    private Roll main;
    private Roll optional;
    private RollType finalRollType;

    public RollPair(Roll main, Roll optional, RollType finalRollType) {
        this.main = main;
        this.optional = optional;
        this.finalRollType = finalRollType;
    }

    public RollPair(Roll main, RollType finalRollType) {
        this.main = main;
        this.finalRollType = finalRollType;
    }

    public static RollPair parseRawPair(String rawPair) {
        if (rawPair == null) {
            return null;
        }
        validatePair(rawPair);
        String[] split = rawPair.split("");
        RollPair result;
        if (split.length == 1) {
            RollType first = RollType.getByIdentifierCharacter(split[0]);
            result = new RollPair(new Roll(first.getDefaultKnockValue(split[0], null), first), first);
        } else {
            RollType first = RollType.getByIdentifierCharacter(split[0]);
            RollType second = RollType.getByIdentifierCharacter(split[1]);
            result = new RollPair(new Roll(first.getDefaultKnockValue(split[0], null), first),
                new Roll(second.getDefaultKnockValue(split[1], split[0]), second), second);
            if (isNumberPairHigherThanTen(result, second)) {
                throw new IllegalArgumentException(rawPair + " is gae!");
            }
        }
        return result;
    }

    private static boolean isNumberPairHigherThanTen(RollPair result, RollType second) {
        return (second.equals(RollType.NUMBER) || second.equals(RollType.MISS)) && result.getMainKnocked() + result.getOptionalKnocked() >= 10;
    }

    private static void validatePair(String rawPair) {
        if (rawPair.length() == 0) {
            throw new IllegalArgumentException("Raw pair cannot or empty");
        }
        if (rawPair.length() > 2 ||
            rawPair.matches("^10$") ||
            !rawPair.matches("^[\\d-]/$|^x$|^[\\d-][\\d-]$")) {
                throw new IllegalArgumentException(rawPair + " is not a pair");
            }
    }

    public boolean isOptionalThere() {
        return optional != null;
    }

    public int getMainKnocked() {
        return main.getPinsKnocked();
    }

    private int getOptionalKnocked() {
        return optional.getPinsKnocked();
    }

    public int getAllKnocked() {
        return optional != null ? main.getPinsKnocked() + optional.getPinsKnocked() : main.getPinsKnocked() ;
    }

    public RollType getFinalRollType() {
        return finalRollType;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RollPair rollPair = (RollPair) o;
        return main.equals(rollPair.main) &&
            Objects.equals(optional, rollPair.optional);
    }

    @Override public int hashCode() {
        return Objects.hash(main, optional);
    }

    @Override public String toString() {
        return "RollPair{" +
            "main=" + main +
            ", optional=" + optional +
            ", finalRollType=" + finalRollType +
            '}';
    }
}
