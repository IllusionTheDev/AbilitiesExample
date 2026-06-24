# Ability API

This module contains the core abstractions for the Ability System. It is designed to be lightweight, extensible, and independent of specific ability implementations.

## Key Components
For a detailed breakdown of the architecture, see the [Documentation Guide](../docs/workflow.md).

### 1. Triggers (AbilityTriggerContext)
Triggers are the entry points of the system. They listen to events (usually Bukkit events) and wrap them into a `Context`.
- **Example**: `AbilityPlayerDamageContext` wraps an `EntityDamageEvent` and provides easy access to the damager, victim, and damage amount.

### 2. Restrictions (AbilityRestriction)
Restrictions act as filters. They inspect the `Context` and decide if the ability should proceed.
- **Built-in Examples**: Cooldowns, Metadata checks, Item requirements, Chance-based triggers (RNG).

### 3. Actions (AbilityAction)
Actions are the "payload" of the ability. They execute logic if all restrictions pass.
- **Example**: `ModifyDamageAction` can multiply or add to the damage in the current context.

### 4. Metadata System
The metadata system provides a type-safe way to store state.
- `AbilityMetadataKey<T>`: A unique key for a piece of data.
- `AbilityMetadataHolder`: Anything that can hold metadata (Players, Abilities, etc.).

## The Reactive Workflow
The system is built around a "Binding" DSL. See the [Workflow Guide](../docs/workflow.md) for more details.

```java
Ability.builder("my_ability")
    .withActions(
        bind("action_id")
            .withType(TriggerType)
            .withRestriction(new CooldownRestriction(5000))
            .to(new MyAction())
    )
    .build();
```

## Design Notes
- **Decoupling**: The API doesn't know about "Hulk" or "Iron Man". It only knows about triggers and actions.
- **Immutability**: Where possible, contexts and configurations should be treated as immutable.

For advanced exercises, check the [Homework Guide](../docs/homework.md).
