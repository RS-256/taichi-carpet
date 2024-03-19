package taichiCarpet;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.*;

public class TaichiCarpetSettings
{
    private static final String TAICHI = "taichi";
    private  static final String LOGGER = "logger";
    private static final String PROTOCOL = "protocol";

    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean optimizedFallDamageRaycast = false;

    @Rule(
            categories = { SURVIVAL, COMMAND, TAICHI }
    )
    public static String commandView = "ops";

    @Rule(
            categories = { SURVIVAL, COMMAND, TAICHI }
    )
    public static String commandSimulation = "ops";

    @Rule(
            categories = { SURVIVAL, COMMAND, TAICHI }
    )
    public static String commandHat = "ops";

    @Rule(
            categories = { SURVIVAL, COMMAND, TAICHI }
    )
    public static String commandSit = "ops";

    @Rule(
            categories = { COMMAND, LOGGER, TAICHI }
    )
    public static String commandNotice = "ops";

    @Rule(
            categories = { TAICHI }
    )
    public static boolean loginNotifier = false;

    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean optimizedDragonRespawn = false;

    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean disableWanderingOnCart = false;

    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean disableNetherPortalCollisionCheck = false;

    @Rule(
            categories = { SURVIVAL, PROTOCOL, TAICHI }
    )
    public static boolean blockInventorySyncing = false;

    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean optimizedHypot = false;

    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean optimizedRounding = false;
}
