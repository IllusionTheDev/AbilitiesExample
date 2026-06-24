package me.illusion.ability.action.impl.generic;

import me.illusion.ability.action.AbilityAction;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.value.AbilityActionValue;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import net.kyori.adventure.text.Component;

/**
 * Example action that sends a message to the player.
 */
public class SendMessageAbilityAction implements AbilityAction<AbilityPlayerContext> {

    private final AbilityActionValue<AbilityPlayerContext, Component> message;

    private SendMessageAbilityAction(AbilityActionValue<AbilityPlayerContext, Component> message) {
        this.message = message;
    }

    public static SendMessageAbilityAction of(AbilityActionValue<AbilityPlayerContext, Component> message) {
        return new SendMessageAbilityAction(message);
    }

    public static SendMessageAbilityAction of(Component message) {
        return SendMessageAbilityAction.of(AbilityActionValue.of(message));
    }

    public static SendMessageAbilityAction literal(String message) {
        return SendMessageAbilityAction.of(Component.text(message));
    }

    public static SendMessageAbilityAction translatable(String message) {
        return SendMessageAbilityAction.of(Component.translatable(message));
    }

    public static SendMessageAbilityAction configured(String path) {
        return SendMessageAbilityAction.of(AbilityActionValue.configurable(config -> config.getRichMessage(path)));
    }

    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> actionContext) {
        actionContext.getContext().getBukkitPlayer().sendMessage(message.getValue(actionContext));
    }
}
