package com.gmail.kamilkime.kinvbackup.cmds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.gmail.kamilkime.kinvbackup.Main;

public class MainTabCompleter implements TabCompleter{

	String[] arguments = new String[]{"przywroc", "pokaz", "pomoc", "backup", "rl", "reload", "restore", "show", "help", "zapisz", "info"};
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("kinv")){
			if(args.length == 1) return getFirstArgs(args[0].toLowerCase());
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("backup") || args[0].equalsIgnoreCase("restore") || args[0].equalsIgnoreCase("show")
						|| args[0].equalsIgnoreCase("zapisz") || args[0].equalsIgnoreCase("przywroc") || args[0].equalsIgnoreCase("pokaz")){
					List<String> tab = new ArrayList<String>();
					for(Player p : Bukkit.getOnlinePlayers()) tab.add(p.getName());
					Collections.sort(tab);
					return tab;
				}
			}
			if(args.length == 3){
				if(args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("pokaz")){
					List<String> tab = new ArrayList<String>();
					for(World w : Bukkit.getWorlds()) tab.add(w.getName());
					Collections.sort(tab);
					return tab;
				}
			}
		}
		return null;
	}
	
	private List<String> getFirstArgs(String arg0){
		List<String> tab = new ArrayList<String>();
		for(String s : arguments){
			if(s.isEmpty()){
				tab.add(s);
				continue;
			}
			if(s.equalsIgnoreCase("info") && !Main.SET.enableInfoCommand) continue;
			if(s.startsWith(arg0)) tab.add(s);
		}
		Collections.sort(tab);
		return tab;
	}
}