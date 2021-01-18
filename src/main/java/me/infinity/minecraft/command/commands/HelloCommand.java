package me.infinity.minecraft.command.commands;

import me.infinity.minecraft.command.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HelloCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(ChatColor.BOLD + ""
                + ChatColor.LIGHT_PURPLE + "W"
                + ChatColor.BLUE + "E"
                + ChatColor.AQUA + "L"
                + ChatColor.GREEN + "C"
                + ChatColor.YELLOW + "O"
                + ChatColor.GOLD + "M"
                + ChatColor.RED + "E"
                + ChatColor.MAGIC + sender.getName()
        );
        return true;
    }

    @Override
    public String getName() {
        return "hello";
    }
}
