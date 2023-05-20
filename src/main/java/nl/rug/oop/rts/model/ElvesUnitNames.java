package nl.rug.oop.rts.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum for Elf unit names.
 */
public enum ElvesUnitNames {
    /**
     * Lorien Warrior unit name.
     */
    LorienWarrior,
    /**
     * Mirkwood Archer unit name.
     */
    MirkwoodArcher,
    /**
     * Rivendell Lancer unit name.
     */
    RivendellLancer;

    private static final List<ElvesUnitNames> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ElvesUnitNames randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
