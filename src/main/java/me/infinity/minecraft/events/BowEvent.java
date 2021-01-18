package me.infinity.minecraft.events;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class BowEvent implements Listener {
    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Player player = (Player) event.getEntity().getShooter();
        final ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.BOW)) {
            if (item.getItemMeta().getDisplayName().contains("Flying Bow")) {
                if (item.getItemMeta().hasLore()) {
                    Arrow arrow = (Arrow) event.getEntity();
                    arrow.addPassenger(player);
                }
            }
        }
    }
}
