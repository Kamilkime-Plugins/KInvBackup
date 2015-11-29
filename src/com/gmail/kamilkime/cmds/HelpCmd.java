package com.gmail.kamilkime.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.utils.StringUtils;

public class HelpCmd {
	
	public void help(CommandSender sender, Command cmd, String l, String args[]){
		sender.sendMessage(StringUtils.color("                    &6&l--------------------"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv pomoc &8- &7Pokazuje te informacje po polsku"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv backup <nick> &8- &7Creates player's inventory backup"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv restore <nick> &8- &7Loads player's inventory backup"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv show <nick> &8- &7Shows player's backuped items"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv reload &8- &7Reloads config file"));
		if(KInvBackup.SET.enableInfoCommand) sender.sendMessage(StringUtils.color(" &8&l» &a/kinv info &8- &7Little info about the plugin :)"));
		sender.sendMessage(StringUtils.color("                    &6&l--------------------"));
	}
	
	public void pomoc(CommandSender sender, Command cmd, String l, String args[]){
		sender.sendMessage(StringUtils.color("                    &6&l--------------------"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv help &8- &7Shows this information in English"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv zapisz <nick> &8- &7Tworzy kopie zapasowa eq gracza"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv przywroc <nick> &8- &7Wczytuje kopie eq gracza"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv pokaz <nick> &8- &7Pokazuje zapisane itemy gracza"));
		sender.sendMessage(StringUtils.color(" &8&l» &a/kinv reload &8- &7Przeladowuje config"));
		if(KInvBackup.SET.enableInfoCommand) sender.sendMessage(StringUtils.color(" &8&l» &a/kinv info &8- &7Male info o pluginie :)"));
		sender.sendMessage(StringUtils.color("                    &6&l--------------------"));
	}
}