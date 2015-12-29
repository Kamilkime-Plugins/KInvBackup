package com.gmail.kamilkime.kinvbackup.utils;

import java.io.File;
import java.util.Calendar;

import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.kamilkime.kinvbackup.Main;

public class ConfigUtils {

	private final static int version = 2;
	private static File mainDir = Main.getInst().getDataFolder();
	private static File cfgFile = new File(mainDir, "config.yml");
	
	public static void check(){
		File f = new File(mainDir, "configOld@" + createTimestamp() + ".yml");
		if(Main.getInst().getConfig().get("configVersion") == null || Main.getInst().getConfig().getInt("configVersion") !=version){
			cfgFile.renameTo(f);
			Main.getInst().saveResource("config.yml", true);
			return;
		}
		File temp = new File(mainDir, "tempCfg.yml");
		cfgFile.renameTo(temp);
		Main.getInst().saveResource("config.yml", true);
		YamlConfiguration cYml = YamlConfiguration.loadConfiguration(cfgFile);
		YamlConfiguration tYml = YamlConfiguration.loadConfiguration(temp);
		for(String s : cYml.getKeys(false)){
			if(tYml.get(s) == null){
				temp.renameTo(f);
				return;
			}
		}
		cfgFile.delete();
		temp.renameTo(cfgFile);
	}
	
	private static String createTimestamp(){
		Calendar c = Calendar.getInstance();
		String time = "" + c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1) + "" + c.get(Calendar.DAY_OF_MONTH) +
				"" + c.get(Calendar.HOUR_OF_DAY) + "" + c.get(Calendar.MINUTE) + "" + c.get(Calendar.SECOND);
		return time;
	}
}