package infinity.minecraft.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

@SuppressWarnings("unused")
public class GodBootEvent implements Listener {
    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getBoots() != null)
            if (Objects.requireNonNull(player.getInventory().getBoots().getItemMeta()).getDisplayName().contains("Boots of Leaping"))
                if (player.getInventory().getBoots().getItemMeta().hasLore())
                    if (event.getFrom().getY() < Objects.requireNonNull(event.getTo()).getY() &&
                            player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                        player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
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
