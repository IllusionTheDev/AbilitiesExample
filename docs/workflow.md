# The Ability Workflow

The system follows a reactive, decoupled architecture based on three main stages: **Trigger** -> **Restriction** -> **Action**.

## Lifecycle of an Ability Execution

1.  **Trigger Phase**:
    *   An event occurs (e.g., a player takes damage).
    *   The `AbilityTriggerListener` catches the Bukkit event.
    *   The event is wrapped into an `AbilityTriggerContext` (e.g., `AbilityPlayerDamageContext`).
    *   The system searches for all `Ability` instances bound to this `TriggerType`.

2.  **Restriction Phase**:
    *   For each bound action, the system runs a list of `AbilityRestriction`s.
    *   Restrictions inspect the `Context` and return `true` or `false`.
    *   If **any** restriction returns `false`, the action is aborted for that specific player/event.

3.  **Action Phase**:
    *   If all restrictions pass, the `AbilityAction` is executed.
    *   Actions can modify the event (e.g., changing damage) or perform side effects (e.g., sending a message, striking lightning).

## The Binding DSL

Abilities are defined by binding triggers to actions with specific restrictions:

```java
Ability.builder("my_ability")
    .withActions(
        bind("action-id")
            .withType(AbilityTriggerContextTypes.PLAYER_DAMAGE_ENTITY) // Trigger
            .withRestriction(new CooldownRestriction(5000))           // Restriction
            .to(ModifyDamageAction.of(ValueModifier.multiply(2.0)))    // Action
    )
    .build();
```

## Architecture Benefits

*   **Decoupling**: The Action doesn't know why it was triggered (e.g., a damage boost could be triggered by a kill, an item, or a command).
*   **Reusability**: Restrictions like `CooldownRestriction` or `IsHeroRestriction` can be reused across any trigger/action combination.
*   **Chainability**: Multiple actions can be bound to the same trigger within a single ability.
