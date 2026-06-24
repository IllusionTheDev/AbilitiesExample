package me.illusion.ability.trigger;

import me.illusion.ability.trigger.impl.generic.AbilityPlayerKillEntityContext;
import me.illusion.ability.trigger.impl.generic.AbilityPlayerShearEntityContext;
import me.illusion.ability.trigger.impl.generic.AbilityProjectileHitContext;
import me.illusion.ability.trigger.impl.player.AbilityEntityDamagePlayerContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerDamageContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerDamageEntityContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerEntityBreedContext;

/**
 * Holder class for all the built-in context types.
 * This should not be an enum, as we allow for 3rd party context types.
 */
public final class AbilityTriggerContextTypes {

    private AbilityTriggerContextTypes() {}

    // Time based
    public static final AbilityTriggerContextType<AbilityPlayerContext> EVERY_PLAYER_TICK = AbilityTriggerContextType.create();
    public static final AbilityTriggerContextType<AbilityPlayerContext> EVERY_PLAYER_SECOND = AbilityTriggerContextType.create();

    public static final AbilityTriggerContextType<AbilityTriggerContext> EVERY_SERVER_TICK = AbilityTriggerContextType.create();
    public static final AbilityTriggerContextType<AbilityTriggerContext> EVERY_SERVER_SECOND = AbilityTriggerContextType.create();

    public static final AbilityTriggerContextType<AbilityPlayerContext> ABILITY_ACTIVATE = AbilityTriggerContextType.create(); // When a player activates an ability
    public static final AbilityTriggerContextType<AbilityPlayerContext> MANUAL_ACTIVATION = AbilityTriggerContextType.create(); // Used by the ability system to trigger other actions


    // Lifecycle based
    public static final AbilityTriggerContextType<AbilityPlayerContext> PLAYER_JOIN = AbilityTriggerContextType.create();
    public static final AbilityTriggerContextType<AbilityPlayerContext> PLAYER_QUIT = AbilityTriggerContextType.create();

    public static final AbilityTriggerContextType<AbilityProjectileHitContext> PROJECTILE_HIT = AbilityTriggerContextType.create();

    public static final AbilityTriggerContextType<AbilityPlayerShearEntityContext> PLAYER_SHEAR_ENTITY = AbilityTriggerContextType.create();

    public static final AbilityTriggerContextType<AbilityPlayerDamageEntityContext> PLAYER_DAMAGE_ENTITY = AbilityTriggerContextType.create();
    public static final AbilityTriggerContextType<AbilityEntityDamagePlayerContext> ENTITY_DAMAGE_PLAYER = AbilityTriggerContextType.create();
    public static final AbilityTriggerContextType<AbilityPlayerDamageContext> PLAYER_TAKE_DAMAGE = AbilityTriggerContextType.create();

    public static final AbilityTriggerContextType<AbilityPlayerKillEntityContext> PLAYER_KILL_ENTITY = AbilityTriggerContextType.create();


}
