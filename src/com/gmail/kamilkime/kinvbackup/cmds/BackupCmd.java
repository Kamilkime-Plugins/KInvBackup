package com.gmail.kamilkime.kinvbackup.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.kamilkime.kinvbackup.Main;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class BackupCmd {

	public void backup(CommandSender sender, Command cmd, String l, String args[]){
		if(!sender.hasPermission("kinv.backup")){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoPermission));
			return;
		}
		if(args.length !=2){
			sender.sendMessage(StringUtils.color(StringUtils.insertCommand("/kinv backup <nick>")));
			return;
		}
		if(Bukkit.getPlayer(args[1]) == null){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoSuchPlayer));
			return;
		}
		Player p = Bukkit.getPlayer(args[1]);
		Main.getDataManager().createBackup(p, p.getWorld().getName());
		sender.sendMessage(StringUtils.color(StringUtils.insertPlayer(p.getName())));
	}
}