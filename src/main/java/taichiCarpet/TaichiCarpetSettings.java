package taichiCarpet;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.*;

public class TaichiCarpetSettings
{
    private static final String TAICHI = "taichi";
    @Rule(
            categories = { OPTIMIZATION, TAICHI }
    )
    public static boolean optimizedFallDamageRaycast = false;

    @Rule(
            categories = { SURVIVAL, COMMAND, TAICHI }
    )
    public static boolean commandView = false;

    @Rule(
            categories = { SURVIVAL, COMMAND, TAICHI }
    )
    public static boolean commandSimulation = false;
}
