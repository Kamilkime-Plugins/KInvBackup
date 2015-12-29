package com.gmail.kamilkime.kinvbackup.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gmail.kamilkime.kinvbackup.Main;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class InfoCmd {

	public void info(CommandSender sender, Command cmd, String l, String args[]){
		sender.sendMessage(StringUtils.color("                       &8&l--------------------"));
		sender.sendMessage(StringUtils.color("                   &7&lKInvBackup v" + Main.getInst().getDescription().getVersion() + " &6&lby Kamilkime"));
		sender.sendMessage(StringUtils.color("                        &a&lhttps://goo.gl/BLqvns"));
		sender.sendMessage(StringUtils.color("                 &6&lFor plugin help type &7&l/kinv help"));
		sender.sendMessage(StringUtils.color("                       &8&l--------------------"));
	}
}