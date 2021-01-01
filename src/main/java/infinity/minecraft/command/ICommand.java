package infinity.minecraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface ICommand {
    boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

    String getName();
}
