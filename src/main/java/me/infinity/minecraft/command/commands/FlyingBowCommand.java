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

public class FlyingBowCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.getInventory().firstEmpty() == -1) {
            Location loc = player.getLocation();
            World world = player.getWorld();

            world.dropItem(loc, getBow());
            player.sendMessage(ChatColor.GOLD + "The Minecraft Gods dropped a gift near you");

            return true;
        }

        player.getInventory().addItem(getBow());
        player.sendMessage(ChatColor.GOLD + "The Minecraft Gods gave you a gift");

        return true;
    }

    @SuppressWarnings("ConstantConditions")
    public ItemStack getBow() {
        ItemStack bow = new ItemStack(Material.BOW);

        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Flying Bow");
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Shoot the arrow to fly");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bow.setItemMeta(meta);

        return bow;
    }

    @Override
    public String getName() {
        return "flyingBow";
    }
}
