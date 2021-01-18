package me.infinity.minecraft.command.commands;

import me.infinity.minecraft.command.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GodTridentCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (player.getInventory().firstEmpty() == -1) {
            Location loc = player.getLocation();
            World world = player.getWorld();

            world.dropItem(loc, getGodTrident());
            player.sendMessage(ChatColor.GOLD + "The Minecraft Gods dropped a gift near you");

            return true;
        }

        player.getInventory().addItem(getGodTrident());
        player.sendMessage(ChatColor.GOLD + "The Minecraft Gods gave you a gift");

        return true;
    }

    @SuppressWarnings("ConstantConditions")
    private ItemStack getGodTrident() {
        ItemStack item = new ItemStack(Material.TRIDENT);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Ancient Trident");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Right Click) &a&oSpawn minions"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Left Click) &a&oShoot explosives"));
        meta.setLore(lore);

        meta.addEnchant(Enchantment.LOYALTY, 10, true);
        meta.addEnchant(Enchantment.CHANNELING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }

    @Override
    public String getName() {
        return "GodTrident";
    }
}
