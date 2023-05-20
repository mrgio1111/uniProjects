package nl.rug.oop.rts.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum for Isengard unit names.
 */
public enum IsengardUnitNames {
    /**
     * Orc warrior unit name.
     */
    OrcWarrior,
    /**
     * Orc Pikeman unit name.
     */
    OrcPikeman,
    /**
     * Haradrim Archer unit name.
     */
    HaradrimArcher;

    private static final List<IsengardUnitNames> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static IsengardUnitNames randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
