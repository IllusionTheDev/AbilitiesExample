# Adding More Triggers

Triggers are the entry points for abilities. They translate external events (usually Bukkit events) into the system's `AbilityTriggerContext`.

## Step-by-Step: Adding a New Trigger

To add a new trigger (e.g., `PLAYER_INTERACT`), follow these steps:

### 1. Create the Context Class
Create a class that implements `AbilityTriggerContext`, along with any additional context interfaces you may want.

```java
public class AbilityPlayerInteractContext implements AbilityPlayerContext {
    private final PlayerInteractEvent event;
    private final AbilityPlayer player;

    public AbilityPlayerInteractContext(PlayerInteractEvent event) {
        this.event = event;
        this.player = AbilityPlayer.of(event.getPlayer());
    }

    public PlayerInteractEvent getEvent() {
        return event;
    }
    
    @Override
    public AbilityPlayer getPlayer() {
        return player;
    }
}
```

### 2. Register the Trigger Type
Add a new `AbilityTriggerContextType` in `AbilityTriggerContextTypes`.

```java
public static final AbilityTriggerContextType<AbilityPlayerInteractContext> PLAYER_INTERACT = AbilityTriggerContextType.create();
```

### 3. Implement the Listener
Create your event handler and register it with the system.

```java
@EventHandler
public void onInteract(PlayerInteractEvent event) {
    AbilityPlayerInteractContext context = new AbilityPlayerInteractContext(
        event.getPlayer(), 
        event
    );
    
    AbilitySystem.handle(AbilityTriggerContextTypes.PLAYER_INTERACT, context);
}
```

## Best Practices
*   **Keep it Lightweight**: Contexts should be simple wrappers. Avoid heavy logic inside context constructors.
*   **Generics**: Ensure your `AbilityTriggerContextType` matches the generic type of your context class.
*   **Global Access**: Always register new types in `AbilityTriggerContextTypes` so they can be discovered by other modules.
