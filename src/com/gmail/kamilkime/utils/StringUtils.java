package com.gmail.kamilkime.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.gmail.kamilkime.KInvBackup;

public class StringUtils {
	
	public static String color(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static List<String> ymlaze(List<String> toRepair){
		List<String> toReturn = new ArrayList<String>();
		for(String s : toRepair) toReturn.add(s.replace("§", "&"));
		return toReturn;
	}
	
	public static String ymlaze(String toRepair){
		return toRepair.replace("§", "&");
	}
	
	public static List<String> unymlaze(List<String> toRepair){
		List<String> toReturn = new ArrayList<String>();
		for(String s : toRepair) toReturn.add(s.replace("&", "§"));
		return toReturn;
	}
	
	public static String unymlaze(String toRepair){
		return toRepair.replace("&", "§");
	}
	
	public static String insertCommand(String command){
		String toReturn = KInvBackup.SET.msgCorrectUsage;
		return toReturn.replace("{COMMAND}", command);
	}
	
	public static String insertPlayer(String name){
		String toReturn = KInvBackup.SET.msgBackupCreated;
		return toReturn.replace("{PLAYER}", name);
	}
	
	public static String insertPlayerAndDate(String name, Object date){
		String toReturn = KInvBackup.SET.msgInventoryRestored;
		return toReturn.replace("{PLAYER}", name).replace("{DATE}", KInvBackup.SDF.format(date));
	}
}