package me.infinity.minecraft.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class SwitchBallEvent implements Listener {
    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntityType() != EntityType.SNOWBALL) return;
        Snowball ball = (Snowball) event.getEntity();
        final ItemMeta meta = ball.getItem().getItemMeta();

        if (meta.getDisplayName().contains("Switch Ball"))
            if (meta.hasLore()) {
                final Entity hitEntity = event.getHitEntity();
                if (hitEntity == null) return;
                
                final Location hitEntityLocation = hitEntity.getLocation();
                Player player = (Player) event.getEntity().getShooter();
                final Location location = player.getLocation();

                hitEntity.teleport(location);
                player.teleport(hitEntityLocation);
            }
    }
}
