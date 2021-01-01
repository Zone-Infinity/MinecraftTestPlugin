package infinity.minecraft.command;

import infinity.minecraft.command.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new HelloCommand());
        addCommand(new HealCommand());
        addCommand(new WildCommand());
        addCommand(new LaunchCommand());
        addCommand(new GodBootsCommand());
        addCommand(new GodTridentCommand());
        addCommand(new AirWalkerCommand());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().toLowerCase().equals(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public boolean handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ICommand cmd = this.getCommand(label);

        if (cmd != null) {
            return cmd.handle(sender, command, label, args);
        }

        return false;
    }

}
