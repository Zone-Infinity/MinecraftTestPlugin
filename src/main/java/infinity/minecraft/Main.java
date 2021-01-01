package infinity.minecraft;

import infinity.minecraft.command.CommandManager;
import infinity.minecraft.events.AirWalkerEvent;
import infinity.minecraft.events.GodBootEvent;
import infinity.minecraft.events.StarEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new StarEvent(), this);
        this.getServer().getPluginManager().registerEvents(new GodBootEvent(), this);
        this.getServer().getPluginManager().registerEvents(new AirWalkerEvent(), this);
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
}
