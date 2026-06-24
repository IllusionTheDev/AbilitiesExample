# How Context Values Work

Context values and the Metadata system provide a type-safe way to share and persist data within the ability system.

## Metadata System

The metadata system allows you to attach data to various holders (`AbilityMetadataHolder`), such as Players or even the Ability itself.

### 1. Defining a Key
Keys are unique identifiers for a piece of data, linked to a specific type.

```java
public static final AbilityMetadataKey<Integer> KILL_COUNT = new AbilityMetadataKey<>("kill_count");
```

### 2. Reading and Writing
Any `AbilityMetadataHolder` can store these values.

```java
// Setting a value
ability.getMetadata(player).set(KILL_COUNT, 10);

// Reading a value (with optional default)
int kills = ability.getMetadata(player).getOrDefault(KILL_COUNT, 0);
```

## Ability Action Values (AbilityActionValue)

`AbilityActionValue` is a wrapper used to provide values to Actions. These can be:
*   **Static**: A fixed value (e.g., `10.0`).
*   **Dynamic**: Values pulled from the context, metadata, or configuration.

This allows the same Action class to behave differently based on how it is bound.

## The Context Object

The `AbilityActionContext` is passed to every restriction and action. It contains:
1.  **The Trigger Context**: The raw data from the event (e.g., who was hit, how much damage).
2.  **The Ability Instance**: Access to the ability's configuration and metadata.

Most implementations of `AbilityActionContext` implement `AbilityPlayerContext`, which also provides access to the `AbilityPlayer` and `Player` objects.

### Accessing Data in an Action:
```java
private final AbilityActionValue<AbilityPlayerContext, Integer> value = (action) -> action.getAbility().getMetadata(action.getContext().getBukkitPlayer()).getOrDefault(LEVEL_KEY, 1);

@Override
public void handle(AbilityActionContext<AbilityPlayerContext> context) {
    // Get the player
    Player player = context.getContext().getBukkitPlayer();
    
    // Get metadata from the player
    int level = value.getValue(context); 
}
```

## Why use Metadata instead of Fields?
1.  **Type Safety**: `AbilityMetadataKey` ensures you don't cast to the wrong type.
2.  **Persistence**: The metadata system can be hooked into a database to save values across sessions.
3.  **Decoupling**: Different plugins can share data via keys without knowing about each other's implementation.
