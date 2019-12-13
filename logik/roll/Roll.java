package logik.roll;

import java.util.Objects;

public class Roll {

    private int pinsKnocked;
    private RollType rollType;

    public Roll(int pinsKnocked, RollType rollType) {
        this.pinsKnocked = pinsKnocked;
        this.rollType = rollType;
    }

    int getPinsKnocked() {
        return pinsKnocked;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Roll aRoll = (Roll) o;
        return pinsKnocked == aRoll.pinsKnocked &&
            rollType == aRoll.rollType;
    }

    @Override public int hashCode() {
        return Objects.hash(pinsKnocked, rollType);
    }

    @Override public String toString() {
        return "Roll{" +
            "pinsKnocked=" + pinsKnocked +
            ", rollType=" + rollType +
            '}';
    }
}
