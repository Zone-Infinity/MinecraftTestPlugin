package me.infinity.minecraft.command.commands;

import me.infinity.minecraft.command.ICommand;
import me.infinity.minecraft.enchantments.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TelePickaxeCommand implements ICommand {
    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        item.addUnsafeEnchantment(CustomEnchants.TELEPATHY, 1);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);


        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Telepathy I");
        if (meta.hasLore())
            lore.addAll(meta.getLore());

        meta.setLore(lore);

        Player player = (Player) sender;
        player.getInventory().addItem(item);

        return true;
    }

    @Override
    public String getName() {
        return "telepathy";
    }
}
