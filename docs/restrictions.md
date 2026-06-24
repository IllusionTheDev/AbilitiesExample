# Implementing a Restriction

Restrictions are the "filters" of the system. They decide whether an ability action should execute based on the current context.

## Creating a Custom Restriction

To implement a restriction, implement the `AbilityRestriction<T>` interface, where `T` is the type of context it supports.

### Example: IsSneakingRestriction

```java
public class IsSneakingRestriction implements AbilityRestriction<AbilityPlayerContext> {

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityPlayerContext> context) {
        // Access the player from the context
        Player player = context.getContext().getBukkitPlayer();
        
        // Return true to allow, false to block
        return player.isSneaking();
    }
}
```

## Targeting Specific Contexts

You can restrict your restriction to specific contexts to gain access to more data.

### Example: DamageCauseRestriction
Only works with triggers that involve damage.

```java
public class DamageCauseRestriction implements AbilityRestriction<AbilityDamageContext> {
    private final DamageCause requiredCause;

    public DamageCauseRestriction(DamageCause requiredCause) {
        this.requiredCause = requiredCause;
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityDamageContext> context) {
        return context.getContext().getDamageCause() == requiredCause;
    }
}
```

## Built-in Restrictions

The API comes with several common restrictions, often accessible via static factory methods, such as:

*   `CooldownRestriction`: Blocks execution if a cooldown is active.
*   `AbilityRNGRestriction`: Blocks based on a random percentage chance.
*   `AbilityMetadataRestriction`: Checks for specific metadata. Use `AbilityMetadataRestriction.isActive()` for toggle states.
*   `AbilityRestrictions`: A container for multiple restrictions, allowing for complex logic.

## Usage in DSL

Use static factory methods where available for cleaner code:

```java
bind("my-action")
    .withType(AbilityTriggerContextTypes.PLAYER_DAMAGE_ENTITY)
    .withRestriction(new IsSneakingRestriction())
    .withRestriction(AbilityMetadataRestriction.isActive())
    .to(...)
```
