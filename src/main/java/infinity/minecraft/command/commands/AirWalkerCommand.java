package infinity.minecraft.command.commands;

import infinity.minecraft.command.ICommand;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AirWalkerCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.getInventory().firstEmpty() == -1) {
            Location loc = player.getLocation();
            World world = player.getWorld();

            world.dropItem(loc, getAirWalkers());
            player.sendMessage(ChatColor.AQUA + "Dropped a AirWalker near you");

            return true;
        }

        player.getInventory().addItem(getAirWalkers());
        player.sendMessage(ChatColor.AQUA + "Gave a AirWalker to you");

        return true;
    }

    @SuppressWarnings("ConstantConditions")
    private ItemStack getAirWalkers() {
        ItemStack airWalkers = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta meta = (LeatherArmorMeta) airWalkers.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Air Walker");
        meta.setColor(Color.AQUA);

        meta.addEnchant(Enchantment.SOUL_SPEED, 2, true);
        meta.addEnchant(Enchantment.DEPTH_STRIDER, 2, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Walk in the air !!");
        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Jump First !!");

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        airWalkers.setItemMeta(meta);

        return airWalkers;
    }

    @Override
    public String getName() {
        return "airWalker";
    }
}
