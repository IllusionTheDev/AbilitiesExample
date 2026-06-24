package me.illusion.heroes.ability;

import static me.illusion.ability.builder.bind.BindableAbilityAction.bind;

import me.illusion.ability.Ability;
import me.illusion.ability.action.impl.generic.ModifyDamageAction;
import me.illusion.ability.modifier.ValueModifier;
import me.illusion.ability.registry.BuiltinAbilities;
import me.illusion.ability.restriction.impl.PlayerDamageTypeRestriction;
import me.illusion.ability.trigger.AbilityTriggerContextTypes;
import me.illusion.heroes.ability.restriction.IsHeroRestriction;
import me.illusion.heroes.data.HeroType;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class IronManAbilities {

    public static final Ability DAMAGE_RESISTANCE = Ability.builder("iron_man_resistance")
        .withActions(
            bind("take-damage")
                .withType(AbilityTriggerContextTypes.PLAYER_TAKE_DAMAGE)
                .withRestriction(IsHeroRestriction.create(HeroType.IRON_MAN))
                .withRestriction(PlayerDamageTypeRestriction.blacklist(DamageCause.VOID, DamageCause.DROWNING, DamageCause.POISON))
                .to(ModifyDamageAction.of(ValueModifier.multiply(0.5)))
        )
        .build();



    public static void register() {
        BuiltinAbilities.register(DAMAGE_RESISTANCE);
    }
}
