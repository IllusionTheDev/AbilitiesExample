package me.illusion.ability.restriction.impl;


import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.value.AbilityActionValue;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;

public class PlayerValueRestriction implements AbilityRestriction<AbilityPlayerContext> {

    private final AbilityActionValue<AbilityPlayerContext, Double> valueParser;
    private final AbilityActionValue<AbilityPlayerContext, Double> currentValueParser;
    private final AbilityActionValue<AbilityPlayerContext, Double> maxValueParser;
    private final AbilityActionValue<AbilityPlayerContext, Mode> modeParser;

    private PlayerValueRestriction(
        AbilityActionValue<AbilityPlayerContext, Double> valueParser,
        AbilityActionValue<AbilityPlayerContext, Double> currentValueParser,
        AbilityActionValue<AbilityPlayerContext, Double> maxValueParser,
        AbilityActionValue<AbilityPlayerContext, Mode> modeParser
    ) {
        this.valueParser = valueParser;
        this.currentValueParser = currentValueParser;
        this.maxValueParser = maxValueParser;
        this.modeParser = modeParser;
    }

    public static PlayerValueRestriction health() {
        return new PlayerValueRestriction(
            AbilityActionValue.configurable(config -> config.getDouble("health-amount")),
            (actionContext) -> actionContext.getContext().getBukkitPlayer().getHealth(),
            (actionContext) -> actionContext.getContext().getBukkitPlayer().getAttribute(Attribute.MAX_HEALTH).getValue(),
            AbilityActionValue.configurable(config -> Mode.valueOf(config.getString("mode")))
        );
    }

    public static PlayerValueRestriction maxHealth() { // Above 99.95% health
        return new PlayerValueRestriction(
            AbilityActionValue.of(0.9995),
            (actionContext) -> actionContext.getContext().getBukkitPlayer().getHealth(),
            (actionContext) -> actionContext.getContext().getBukkitPlayer().getAttribute(Attribute.MAX_HEALTH).getValue(),
            AbilityActionValue.of(Mode.MORE_THAN_PERCENT)
        );
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityPlayerContext> actionContext) {
        double current = currentValueParser.getValue(actionContext);
        double maxValue = maxValueParser.getValue(actionContext);

        Mode mode = modeParser.getValue(actionContext);
        double value = valueParser.getValue(actionContext);

        return mode.isSatisfied(current, maxValue, value);
    }

    private interface ValueRestriction {
        boolean isSatisfied(double current, double maxValue, double amount);
    }

    public enum Mode implements ValueRestriction {
        LESS_THAN((current, maxValue, amount) -> current <= amount),
        MORE_THAN((current, maxValue, amount) -> current >= amount),

        LESS_THAN_PERCENT((current, maxValue, amount) -> amount >= current / maxValue),
        MORE_THAN_PERCENT((current, maxValue, amount) -> amount <= current / maxValue),
        ;

        private final ValueRestriction restriction;

        Mode(ValueRestriction restriction) {
            this.restriction = restriction;
        }

        public boolean isSatisfied(double current, double maxValue, double amount) {
            return restriction.isSatisfied(current, maxValue, amount);
        }
    }
}
