package com.gmail.kamilkime.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.kamilkime.KInvBackup;

public class MainCmd implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String l, String args[]) {
		if (cmd.getName().equalsIgnoreCase("kinv")) {
			if(args.length == 0){
				if(KInvBackup.SET.enableInfoCommand) new InfoCmd().info(sender, cmd, l, args);
				else new HelpCmd().pomoc(sender, cmd, l, args);
			} else {
				if(args[0].equalsIgnoreCase("restore") || args[0].equalsIgnoreCase("przywroc")){
					new RestoreCmd().restore(sender, cmd, l, args);
				}
				else if(args[0].equalsIgnoreCase("backup") || args[0].equalsIgnoreCase("zapisz")){
					new BackupCmd().backup(sender, cmd, l, args);
				}
				else if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")){
					new ReloadCmd().reload(sender, cmd, l, args);
				}
				else if(args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("pokaz")){
					new ShowCmd().show(sender, cmd, l, args);
				}
				else if(args[0].equalsIgnoreCase("help")){
					new HelpCmd().help(sender, cmd, l, args);
				}
				else if(args[0].equalsIgnoreCase("info")){
					if(KInvBackup.SET.enableInfoCommand) new InfoCmd().info(sender, cmd, l, args);
					else new HelpCmd().pomoc(sender, cmd, l, args);
				}
				else if(args[0].equalsIgnoreCase("pomoc")){
					new HelpCmd().pomoc(sender, cmd, l, args);
				} else {
					new HelpCmd().help(sender, cmd, l, args);
				}
			}
		}
		return false;
	}
}