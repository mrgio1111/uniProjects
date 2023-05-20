package nl.rug.oop.rts.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum for Men unit names.
 */
public enum MenUnitNames {
    /**
     * Gondor Soldier unit name.
     */
    GondorSoldier,
    /**
     * Tower guard unit name.
     */
    TowerGuard,
    /**
     * Ithilien Ranger unit name.
     */
    IthilienRanger;

    private static final List<MenUnitNames> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static MenUnitNames randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
