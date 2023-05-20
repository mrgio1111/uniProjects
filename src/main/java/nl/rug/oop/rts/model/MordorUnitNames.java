package nl.rug.oop.rts.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum for Mordor unit names.
 */
public enum MordorUnitNames {
    /**
     * Uruk hai unit name.
     */
    UrukHai,
    /**
     * Uruk Crossbowman unit name.
     */
    UrukCrossbowman,
    /**
     * Warg Rider unit name.
     */
    WargRider;

    private static final List<MordorUnitNames> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Random names.
     * @return returns random values of the names above.
     */
    public static MordorUnitNames randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
