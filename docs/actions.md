# Implementing Actions

Actions contain the actual logic that executes when a trigger occurs and all restrictions pass.

## Creating a Custom Action

To implement an action, implement the `AbilityAction<T>` interface.

### Example: StrikeLightningAction

```java
public class StrikeLightningAction implements AbilityAction<AbilityPlayerContext> {

    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> context) {
        Player player = context.getContext().getBukkitPlayer();
        Location loc = player.getLocation();
        
        player.getWorld().strikeLightning(loc);
    }
}
```

## Modifying Events (Mixed Types)

Some actions are designed to modify the event that triggered them. These usually implement a more specific `AbilityAction` type.

### Example: ModifyDamageAction
This action works with `AbilityDamageContext` to change damage values.

```java
public class ModifyDamageAction implements AbilityAction<AbilityDamageContext> {
    private final ValueModifier<Double> modifier;

    public ModifyDamageAction(ValueModifier<Double> modifier) {
        this.modifier = modifier;
    }

    @Override
    public void handle(AbilityActionContext<AbilityDamageContext> context) {
        context.getContext().addDamageModifier(modifier);
    }
}
```

## Showcasing Mixed Types

You can bind multiple actions to the same trigger, as long as they are applicable to the trigger type. This allows you to combine event-modifying effects with side-effects.
The `.to()` method accepts multiple actions as varargs.

```java
Ability.builder("thunder_hit")
    .withActions(
        bind("heavy-strike")
            .withType(AbilityTriggerContextTypes.PLAYER_DAMAGE_ENTITY)
            .to(
                ModifyDamageAction.of(ValueModifier.multiply(2.0)), // Modifies damage, applies to any trigger that has damage context
                new StrikeLightningAction()                       // Side effect, applies to any trigger that has player context
            )
    )
    .build();
```

## Best Practices
*   **Single Responsibility**: An action should do one thing (e.g., Send a message OR strike lightning, not both). Combine them in the Ability builder instead.
*   **Context Safety**: Use the most generic context possible (e.g., `AbilityPlayerContext`) unless you specifically need event-modifying data (like damage).
*   **Code Consistency**: Opt for static factory methods and use Action Values when possible. Check the sample implementations when in doubt.
