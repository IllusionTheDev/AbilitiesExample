# Example Plugin: Heroes

This module demonstrates a concrete implementation of the Ability API. It implements a "Hero" system where players can choose a hero type and receive specific passive or active abilities.

## Current Heroes

### Iron Man
- **Ability**: `DAMAGE_RESISTANCE`
- **Effect**: 50% damage reduction.
- **Restrictions**: Does not protect against VOID, DROWNING, or POISON damage.
- **Code**: [IronManAbilities.java](src/main/java/me/illusion/heroes/ability/IronManAbilities.java)

### Hulk
- **Ability**: `DAMAGE_BOOST`
- **Effect**: Deals 1.5x damage to enemies.
- **Code**: [HulkAbilities.java](src/main/java/me/illusion/heroes/ability/HulkAbilities.java)

## Commands
- `/hero <type>`: Switch to a specific hero (e.g., `/hero hulk`).
- `/hero none`: Remove your current hero.
- *Includes full tab-completion.*

## Implementation Details

### Custom Restrictions
The plugin implements `IsHeroRestriction`, which checks if a player has a specific hero assigned before allowing an ability to trigger. This shows how to extend the API with plugin-specific logic.

### Shading
This module uses the **Gradle Shadow Plugin** to shade the `api` module into the final JAR. This ensures that the plugin is self-contained and doesn't require a separate API plugin to be installed.

## Learning More
For more design patterns and guides, check the [Global Documentation](../docs/workflow.md).