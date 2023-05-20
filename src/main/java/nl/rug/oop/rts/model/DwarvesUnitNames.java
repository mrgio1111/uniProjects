package nl.rug.oop.rts.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum for Dwarf unit names.
 */
public enum DwarvesUnitNames {
    /**
     * Guardian unit name.
     */
    Guardian,
    /**
     * Phalanx unit name.
     */
    Phalanx,
    /**
     * Axe Thrower unit name.
     */
    AxeThrower;

    private static final List<DwarvesUnitNames> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static DwarvesUnitNames randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
