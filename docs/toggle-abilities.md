# Writing a Toggle Ability

Toggle abilities can be turned on and off. Their behavior usually depends on a state stored in the Metadata system.

## The Toggle Logic

A toggle ability usually consists of two parts:
1.  **The Toggle Trigger**: A command or interaction that flips the state.
2.  **The Passive Effect**: Actions that only execute if the state is "ACTIVE".

### 1. The Toggle Action
This action simply flips the activation state on the player.

```java
public class ToggleActivationAction implements AbilityAction<AbilityPlayerContext> {
    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> context) {
        Player player = context.getContext().getBukkitPlayer();
        AbilityActivationState current = context.getAbility().getMetadata(player).getOrDefault(AbilityMetadataKeys.ACTIVATION_STATE, AbilityActivationState.INACTIVE);
        
        AbilityActivationState next = current == AbilityActivationState.ACTIVE ? AbilityActivationState.INACTIVE : AbilityActivationState.ACTIVE;
        
        context.getAbility().getMetadata(player).set(AbilityMetadataKeys.ACTIVATION_STATE, next);
        player.sendMessage("Ability is now: " + next);
    }
}
```

### 2. The Restricted Passive Effect
Use `AbilityMetadataRestriction.isActive()` to ensure the passive effect only runs when the toggle is active.

```java
Ability.builder("flight_mode")
    .withActions(
        // The Toggle mechanism
        bind("toggle-command")
            .withType(AbilityTriggerContextTypes.PLAYER_COMMAND)
            .withRestriction(CommandRestriction.matches("/fly"))
            .to(PlayerToggleFlyAction.of()),
            
        // The actual effect (e.g., negate fall damage while toggled on)
        bind("prevent-fall")
            .withType(AbilityTriggerContextTypes.PLAYER_TAKE_DAMAGE)
            .withRestriction(new PlayerDamageTypeRestriction(DamageCause.FALL))
            .withRestriction(AbilityMetadataRestriction.isActive())
            .to(ModifyDamageAction.of(ValueModifier.set(0.0)))
    )
    .build();
```

## Advanced: Toggles with Costs
You can combine a toggle with a repeating task (e.g., consuming mana every second). If the mana hits zero, the toggle action is executed again to turn the ability off.
