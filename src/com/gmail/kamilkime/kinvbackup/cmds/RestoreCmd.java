package com.gmail.kamilkime.kinvbackup.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.kamilkime.kinvbackup.Main;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class RestoreCmd {

	public void restore(CommandSender sender, Command cmd, String l, String[] args){
		if(!sender.hasPermission("kinv.restore")){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoPermission));
			return;
		}
		if(args.length !=2){
			sender.sendMessage(StringUtils.color(StringUtils.insertCommand("/kinv restore <nick>")));
			return;
		}
		if(Bukkit.getPlayer(args[1]) == null){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoSuchPlayer));
			return;
		}
		Player p = Bukkit.getPlayer(args[1]);
		if(Main.getDataManager().getPlayerFile(p) == null){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoSuchBackup));
			return;
		}
		Long time = Main.getDataManager().loadBackup(p, p.getWorld().getName());
		if(time == 0L){
			sender.sendMessage(Main.SET.msgNoBackupForThisWorld.replace("{WORLD}", p.getWorld().getName()));
			return;
		}
		sender.sendMessage(StringUtils.color(StringUtils.insertPlayerAndDate(p.getName(), time)));
	}
}