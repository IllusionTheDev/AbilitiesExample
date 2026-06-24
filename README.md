# AbilityAbstraction

An architectural guide and sample implementation for a highly versatile, reactive Ability System for Minecraft (Paper/Spigot).

## Purpose
This project serves as a **design guide** rather than a feature-complete plugin. It demonstrates a robust way to decouple ability logic from the Bukkit API using a reactive workflow.

The core philosophy is to minimize "hard-coding" of ability logic and instead provide a flexible framework where abilities can be composed of reusable components.

## Documentation Guide
This project is designed as a learning resource. Check out the detailed guides in the `docs/` folder:

*   **[Core Workflow](docs/workflow.md)**: How the Trigger -> Restriction -> Action pipeline works.
*   **[Adding Triggers](docs/triggers.md)**: How to hook into new events.
*   **[Implementing Restrictions](docs/restrictions.md)**: How to create filters (cooldowns, permissions, etc.).
*   **[Actions & Mixed Types](docs/actions.md)**: Executing logic and modifying events.
*   **[Context & Metadata](docs/context-values.md)**: Type-safe data sharing and persistence.
*   **[Ability Types](docs/cast-abilities.md)**: Guides for [Cast Abilities](docs/cast-abilities.md) and [Toggle Abilities](docs/toggle-abilities.md).
*   **[Homework](docs/homework.md)**: Exercises to improve your implementation.

## Project Structure
- `api/`: The core framework. Contains the abstractions for triggers, restrictions, actions, and metadata management.
- `example-plugin/`: A reference implementation. Shows how to use the API to create a "Hero" system with specific character abilities (e.g., Hulk, Iron Man).

## The Core Architecture
The system follows a three-step workflow for every ability:

1.  **Trigger**: An event occurs (e.g., player takes damage, moves, or manually activates an ability). This builds the **Context**.
2.  **Restrictions**: Rules are applied to the context (e.g., "Is the player on cooldown?", "Does the player have enough mana?", "Is the damage self-inflicted?").
3.  **Actions**: If all restrictions pass, the logic is executed (e.g., modify damage, send a message, play a sound).

### Typed Metadata
A flexible metadata system allows storing information tied to either a player or an ability. This is used for tracking state like:
- Cooldown timestamps
- Active/Inactive states (for toggles)
- "Stacks" of a specific buff
- Custom player data
