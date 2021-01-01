package infinity.minecraft.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public class AirWalkerEvent implements Listener {
    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final ItemMeta itemMeta = event.getPlayer().getInventory().getBoots().getItemMeta();
        if (itemMeta.getDisplayName().contains("Air Walker"))
            if (itemMeta.hasLore()) {

                Location from = event.getFrom();
                World world = event.getPlayer().getWorld();
                Location blockPlaced = new Location(world, from.getX(), from.getY() - 1, from.getZ());
                blockPlaced.getBlock().setType(Material.BLUE_WOOL);

            }
    }
}
