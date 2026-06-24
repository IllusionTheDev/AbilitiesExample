package me.illusion.ability.trigger.impl.generic;

import me.illusion.ability.trigger.impl.entity.AbilityEntityContext;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class AbilityProjectileHitContext implements AbilityEntityContext {

    private final Entity projectile;
    private final Entity hitEntity;
    private final Block hitBlock;

    public AbilityProjectileHitContext(Entity projectile, Entity hitEntity, Block hitBlock) {
        this.projectile = projectile;
        this.hitEntity = hitEntity;
        this.hitBlock = hitBlock;
    }

    @Override
    public Entity getTarget() {
        return projectile;
    }

    public Block getHitBlock() {
        return hitBlock;
    }

    public Entity getHitEntity() {
        return hitEntity;
    }
}
