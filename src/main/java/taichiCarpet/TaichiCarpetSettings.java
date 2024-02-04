package taichiCarpet;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.*;

public class TaichiCarpetSettings
{
    private static final String TAICHI = "taichi";
    private  static final String LOGGER = "logger";
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
          categories =  {COMMAND, LOGGER, TAICHI }
    )
    public static String commandNotice = "ops";
}
