package com.gmail.kamilkime.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.data.Settings;
import com.gmail.kamilkime.utils.StringUtils;

public class ReloadCmd {

	public void reload(CommandSender sender, Command cmd, String l, String args[]){
		if(!sender.hasPermission("kinv.reload")){
			sender.sendMessage(StringUtils.color(KInvBackup.SET.msgNoPermission));
			return;
		}
		if(args.length !=1){
			sender.sendMessage(StringUtils.color(StringUtils.insertCommand("/kinv reload")));
			return;
		}
		Settings.getInst().reload();
		sender.sendMessage(StringUtils.color(KInvBackup.SET.msgConfigReloaded));
	}
}