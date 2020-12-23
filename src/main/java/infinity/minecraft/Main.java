package infinity.minecraft;

import infinity.minecraft.command.CommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Test Plugin Enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Test Plugin Disabled");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            return new CommandManager().handle(sender, command, label, args);
        }
        return false;
    }
}
