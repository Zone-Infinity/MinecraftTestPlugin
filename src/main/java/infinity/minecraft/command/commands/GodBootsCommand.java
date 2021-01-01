package infinity.minecraft.command.commands;

import infinity.minecraft.command.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GodBootsCommand implements ICommand, CommandExecutor {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.getInventory().firstEmpty() == -1) {
            Location loc = player.getLocation();
            World world = player.getWorld();

            world.dropItem(loc, getGodBoots());
            player.sendMessage(ChatColor.GOLD + "The Minecraft Gods dropped a gift near you");

            return true;
        }

        player.getInventory().addItem(getGodBoots());
        player.sendMessage(ChatColor.GOLD + "The Minecraft Gods gave you a gift");

        return true;
    }

    @SuppressWarnings("ConstantConditions")
    private ItemStack getGodBoots() {
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta meta = boots.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Boots of Leaping");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Boots made for the Minecraft Gods");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        meta.addEnchant(Enchantment.FROST_WALKER, 3, true);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 3, true);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setUnbreakable(true);

        boots.setItemMeta(meta);

        return boots;
    }

    @Override
    public String getName() {
        return "godBoots";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return true;
    }

}
