package me.illusion.heroes.ability;

import static me.illusion.ability.builder.bind.BindableAbilityAction.bind;

import me.illusion.ability.Ability;
import me.illusion.ability.action.impl.generic.ModifyDamageAction;
import me.illusion.ability.modifier.ValueModifier;
import me.illusion.ability.registry.BuiltinAbilities;
import me.illusion.ability.trigger.AbilityTriggerContextTypes;
import me.illusion.heroes.ability.restriction.IsHeroRestriction;
import me.illusion.heroes.data.HeroType;

public class HulkAbilities {

    public static final Ability DAMAGE_BOOST = Ability.builder("hulk_damage_boost")
        .withActions(
            bind("deal-damage")
                .withType(AbilityTriggerContextTypes.PLAYER_DAMAGE_ENTITY)
                .withRestriction(IsHeroRestriction.create(HeroType.HULK))
                .to(ModifyDamageAction.of(ValueModifier.multiply(1.5)))
        )
        .build();


    public static void register() {
        BuiltinAbilities.register(DAMAGE_BOOST);
    }
}
