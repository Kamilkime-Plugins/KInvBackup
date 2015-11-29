package com.gmail.kamilkime.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gmail.kamilkime.utils.StringUtils;

public class InfoCmd {

	public void info(CommandSender sender, Command cmd, String l, String args[]){
		sender.sendMessage(StringUtils.color("                       &8&l--------------------"));
		sender.sendMessage(StringUtils.color("                   &7&lKInvBackup v1.0 &6&lby Kamilkime"));
		sender.sendMessage(StringUtils.color("                        &a&lhttps://goo.gl/BLqvns"));
		sender.sendMessage(StringUtils.color("                 &6&lFor plugin help type &7&l/kinv help"));
		sender.sendMessage(StringUtils.color("                       &8&l--------------------"));
	}
}