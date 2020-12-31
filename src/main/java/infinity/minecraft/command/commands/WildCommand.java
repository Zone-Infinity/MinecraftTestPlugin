package infinity.minecraft.command.commands;

import infinity.minecraft.command.ICommand;
import infinity.minecraft.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WildCommand implements ICommand {
    @Override
    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final Player player = (Player) sender;

        Random random = new Random();

        int newX = Utils.randInt(random, -2000, 2000);
        int newY = player.getLocation().getBlockY();
        int newZ = Utils.randInt(random, -2000, 2000);

        Location location = new Location(player.getWorld(), newX, newY, newZ);

        player.teleport(location);

        return true;
    }

    @Override
    public String getName() {
        return "wild";
    }
}
