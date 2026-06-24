# Writing a Cast Ability

A "Cast" ability is an active ability triggered by a specific player action, such as right-clicking an item or pressing a key (interacting).

## The Interaction Trigger

Most cast abilities are bound to the `PLAYER_INTERACT` trigger (or a custom "CAST" trigger).

### Example: "Fireball" Ability

1.  **Define the Action**:
```java
public class ShootFireballAction implements AbilityAction<AbilityPlayerContext> {
    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> context) {
        Player player = context.getContext().getBukkitPlayer();
        player.launchProjectile(Fireball.class);
    }
}
```

2.  **Bind the Ability**:
```java
Ability.builder("fireball")
    .withActions(
        bind("shoot")
            .withType(AbilityTriggerContextTypes.PLAYER_INTERACT)
            .withRestriction(new AbilityItemRestriction(Material.BLAZE_ROD)) // Must hold a Blaze Rod
            .withRestriction(CooldownRestriction.isOffCooldown())               // 5s cooldown
            .to(
                new ShootFireballAction(),
                SetTemporaryMetadataAction.createCooldown(Duration.ofSeconds(5))
            )
    )
    .build();
```

## Pro Tip: Custom Cast Events
For more advanced logic, you can use the `MANUAL_TRIGGER` trigger that can only be called via the API. This allows you to create custom events that can be triggered by any action, including other abilities.
