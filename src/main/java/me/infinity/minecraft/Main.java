package me.infinity.minecraft;

import me.infinity.minecraft.command.CommandManager;
import me.infinity.minecraft.enchantments.CustomEnchants;
import me.infinity.minecraft.events.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        CustomEnchants.register();

        Bukkit.addRecipe(getNetherStarRecipe());
        Bukkit.addRecipe(getEmeraldPickaxeRecipe());
        this.getServer().getPluginManager().registerEvents(new StarEvent(), this);
        this.getServer().getPluginManager().registerEvents(new GodBootEvent(), this);
        this.getServer().getPluginManager().registerEvents(new AirWalkerEvent(), this);
        this.getServer().getPluginManager().registerEvents(new AdamAppleEatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new BowEvent(), this);
        this.getServer().getPluginManager().registerEvents(new SwitchBallEvent(), this);
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Test Plugin Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Test Plugin Disabled");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            return new CommandManager().handle(sender, command, label, args);
        }

        return false;
    }

    public ShapedRecipe getNetherStarRecipe() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        NamespacedKey key = new NamespacedKey(this, "nether_star");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" T ", "TET", " T ");
        recipe.setIngredient('T', Material.GHAST_TEAR);
        recipe.setIngredient('E', Material.EMERALD_BLOCK);

        return recipe;
    }

    @SuppressWarnings("ConstantConditions")
    public ShapedRecipe getEmeraldPickaxeRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Emerald Pickaxe");
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, true);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "emerald_pickaxe");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("EEE", " S ", " S ");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('E', Material.EMERALD_BLOCK);

        return recipe;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        final ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        if (!itemInMainHand.hasItemMeta())
            return;
        if (!Objects.requireNonNull(itemInMainHand.getItemMeta()).hasEnchant(CustomEnchants.TELEPATHY))
            return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        if (event.getPlayer().getInventory().firstEmpty() == -1)
            return;
        if (event.getBlock().getState() instanceof Container)
            return;

        event.setDropItems(false);
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
        if (drops.isEmpty())
            return;

        player.getInventory().addItem(drops.iterator().next());
    }
}
