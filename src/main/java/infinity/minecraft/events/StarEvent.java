package infinity.minecraft.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class StarEvent implements Listener {
    private int tridentClicks = 0;
    public List<String> list = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        final ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        if (itemInMainHand.getType().equals(Material.TRIDENT))
            if (Objects.requireNonNull(itemInMainHand.getItemMeta()).hasLore()) {
                Player player = event.getPlayer();

                if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (!list.contains(player.getName()))
                        list.add(player.getName());
                    return;
                }

                if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    final Class fireball;
                    // DragonFireball.class;
                    // LargeFireball.class;
                    // SmallFireball.class;

                    switch (tridentClicks % 4) {
                        case 0:
                        case 1:
                        case 2:
                            fireball = LargeFireball.class;
                            break;
                        case 3:
                            fireball = DragonFireball.class;
                            break;
                        default:
                            fireball = Fireball.class;
                    }

                    tridentClicks++;
                    player.launchProjectile(fireball);
                }
            }

        list.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void onLand(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.TRIDENT) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();

                if (list.contains(player.getName())) {
                    Location loc = event.getEntity().getLocation();
                    loc.setY(loc.getY() + 1);

                    for (int i = 1; i < 4; i++) {
                        Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.DROWNED);
                        loc.setX(loc.getX() + i);
                    }
                }

            }
        }
    }
}
