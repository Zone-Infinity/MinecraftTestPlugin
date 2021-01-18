package me.infinity.minecraft.command.commands;

import me.infinity.minecraft.command.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LaunchCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooom!");
            player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));

            return true;

        }

        try {
            int mul = Integer.parseInt(args[0]);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i <= Math.min(mul, 20); i++) {
                s.append("o");
            }
            player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Z" + s + "m!");
            player.setVelocity(player.getLocation().getDirection().multiply(mul).setY(2));

        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Usage: /launch <number>");
        }

        return true;

    }

    @Override
    public String getName() {
        return "launch";
    }

}
