package com.gmail.kamilkime.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.data.DataManager;
import com.gmail.kamilkime.utils.StringUtils;

public class RestoreCmd {

	public void restore(CommandSender sender, Command cmd, String l, String args[]){
		if(!sender.hasPermission("kinv.restore")){
			sender.sendMessage(StringUtils.color(KInvBackup.SET.msgNoPermission));
			return;
		}
		if(args.length !=2){
			sender.sendMessage(StringUtils.color(StringUtils.insertCommand("/kinv restore <nick>")));
			return;
		}
		if(Bukkit.getPlayer(args[1]) == null){
			sender.sendMessage(StringUtils.color(KInvBackup.SET.msgNoSuchPlayer));
			return;
		}
		Player p = Bukkit.getPlayer(args[1]);
		if(DataManager.getPlayerFile(p) == null){
			sender.sendMessage(StringUtils.color(KInvBackup.SET.msgNoSuchBackup));
			return;
		}
		sender.sendMessage(StringUtils.color(StringUtils.insertPlayerAndDate(p.getName(), DataManager.loadBackup(p))));
	}
}