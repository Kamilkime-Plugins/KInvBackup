package com.gmail.kamilkime.kinvbackup;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.kamilkime.kinvbackup.cmds.MainCmd;
import com.gmail.kamilkime.kinvbackup.cmds.MainTabCompleter;
import com.gmail.kamilkime.kinvbackup.data.DataManager;
import com.gmail.kamilkime.kinvbackup.data.Settings;
import com.gmail.kamilkime.kinvbackup.listeners.InventoryClickListener;
import com.gmail.kamilkime.kinvbackup.listeners.PlayerListener;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class Main extends JavaPlugin{

	private static Main inst;
	private static DataManager dm;
	public static Settings SET;
	public final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss zz");
	
	public Main(){
		inst = this;
	}
	
	@Override
	public void onEnable(){
		SET = Settings.getInst();
		SET.load();
		if(SET.backupOnServerEnabled){
			for(Player p : Bukkit.getOnlinePlayers()) dm.createBackup(p, p.getWorld().getName());
		}
		if(SET.autoBackupEnabled) startBackup();
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		getCommand("kinv").setExecutor(new MainCmd());
		getCommand("kinv").setTabCompleter(new MainTabCompleter());
	}
	
	@Override
	public void onDisable(){
		if(SET.backupOnServerDisabled){
			for(Player p : Bukkit.getOnlinePlayers()) dm.createBackup(p, p.getWorld().getName());
		}
	}
	
	public static Main getInst(){
		return inst;
	}
	
	public static DataManager getDataManager(){
		return dm;
	}
	
	public static void setDataManager(DataManager dataManager){
		dm = dataManager;
	}
	
	private void startBackup(){
		Bukkit.getScheduler().runTaskTimer(this, new Runnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					dm.createBackup(p, p.getWorld().getName());
				}
				Bukkit.getConsoleSender().sendMessage(StringUtils.color("&7&l[KInvBackup] &6Inventories backuped!"));
			}
		}, SET.autoBackupInterval*20, SET.autoBackupInterval*20);
	}
}