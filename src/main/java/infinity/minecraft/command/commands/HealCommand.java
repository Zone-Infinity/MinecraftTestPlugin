package infinity.minecraft.command.commands;

import infinity.minecraft.command.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        player.setHealth(20);
        player.setFoodLevel(20);
        sender.sendMessage(ChatColor.RED + "Healed " + sender.getName());

        return true;
    }

    @Override
    public String getName() {
        return "heal";
    }
}
