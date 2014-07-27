package com.demigodsrpg.demigods.classic.command;

import com.censoredsoftware.library.util.StringUtil2;
import com.demigodsrpg.demigods.classic.DGClassic;
import com.demigodsrpg.demigods.classic.command.type.BaseCommand;
import com.demigodsrpg.demigods.classic.command.type.CommandResult;
import com.demigodsrpg.demigods.classic.deity.Deity;
import com.demigodsrpg.demigods.classic.model.PlayerModel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CheckCommand extends BaseCommand {
    @Override
    public CommandResult onCommand(CommandSender sender, Command command, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            return CommandResult.PLAYER_ONLY;
        }
        Player player = (Player) sender;
        PlayerModel model = DGClassic.PLAYER_R.fromPlayer(player);
        player.sendMessage(StringUtil2.chatTitle("Player Stats"));
        player.sendMessage("You are a " + model.getMajorDeity().getColor() + model.getMajorDeity().getNomen());
        if (!model.getContractedDeities().isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Deity deity : model.getContractedDeities()) {
                builder.append(deity.getColor()).append(deity.getDeityName()).append(ChatColor.RESET).append(", ");
            }
            String minorDeities = builder.toString();
            minorDeities = minorDeities.substring(0, minorDeities.length() - 4);
            player.sendMessage("You have also allied with: " + minorDeities);
        }
        player.sendMessage("Favor: " + model.getFavor() + " / " + model.getMaxFavor());
        player.sendMessage("Total Devotion: " + model.getTotalDevotion());
        player.sendMessage("For a list of binds, use /binds");
        return CommandResult.SUCCESS;
    }
}
