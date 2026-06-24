# Extras & Learning Exercises

To deepen your understanding of the system, try implementing the following features:

## Level 1: Basics
1.  **More Triggers**: Implement `PLAYER_MOVE`, `PROJECTILE_LAUNCH`, or `BLOCK_BREAK`.
2.  **Sound Effects**: Create a `PlaySoundAction` that takes a `Sound` and `volume/pitch` as parameters.
3.  **Permissions**: Create a `PermissionRestriction` that checks if a player has a specific Bukkit permission.

## Level 2: Intermediate
4.  **Serialization**: Implement a system to save and load `AbilityMetadata` to a database (SQL or MongoDB).
5.  **Configuration**: Implement the `getConfiguration()``method in `AbilityImpl` to fetch a configuration from a file with the ability's name.
6.  **Entity Support**: Extend the system so non-player entities (like Bosses or Custom Mobs) can hold metadata, like debuffs or cooldowns.

## Level 3: Advanced
7.  **Hulk Smash**: Add an active ability for the Hulk that launches him into the air and deals AOE damage/knockback upon landing (Hint: Use a custom `MOVE` trigger to detect landing). Ensure it doesn't break if the player stars gliding or logs off.
8.  **Iron Man Flight**: Use a `MOVE` trigger to allow flight, but consume a "Fuel" metadata value every tick.
9.  **Visual Scripting**: Design a system where players can create their own "mini-abilities" by combining triggers and actions in a GUI.
