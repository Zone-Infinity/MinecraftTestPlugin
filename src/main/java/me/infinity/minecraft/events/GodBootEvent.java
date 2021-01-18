package me.infinity.minecraft.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

@SuppressWarnings("unused")
public class GodBootEvent implements Listener {
    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getBoots() != null)
            if (Objects.requireNonNull(player.getInventory().getBoots().getItemMeta()).getDisplayName().contains("Boots of Leaping"))
                if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                    final Location location = player.getLocation();
                    if (event.getFrom().getY() < Objects.requireNonNull(event.getTo()).getY() &&
                            location.subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                        if (player.getInventory().getItemInMainHand().getType() == Material.TRIDENT)
                            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Ancient Trident"))
                                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                                    Objects.requireNonNull(location.getWorld()).strikeLightning(location);
                        player.setVelocity(location.getDirection().multiply(2).setY(2));
                    }
                }

    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getCause() == EntityDamageEvent.DamageCause.FALL)
                if (player.getInventory().getBoots() != null)
                    if (Objects.requireNonNull(player.getInventory().getBoots().getItemMeta()).getDisplayName().contains("Boots of Leaping"))
                        if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                            event.setCancelled(true);
                        }

        }
    }
}
