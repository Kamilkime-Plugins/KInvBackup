package com.gmail.kamilkime;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.kamilkime.cmds.MainCmd;
import com.gmail.kamilkime.data.DataManager;
import com.gmail.kamilkime.data.Settings;
import com.gmail.kamilkime.listeners.InventoryClickListener;
import com.gmail.kamilkime.listeners.PlayerJoinListener;
import com.gmail.kamilkime.listeners.PlayerKickListener;
import com.gmail.kamilkime.listeners.PlayerQuitListener;
import com.gmail.kamilkime.utils.StringUtils;

public class KInvBackup extends JavaPlugin{

	private static KInvBackup inst;
	public static final Settings SET = Settings.getInst();
	public final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss zz");
	
	public KInvBackup(){
		inst = this;
	}
	
	@Override
	public void onEnable(){
		getCommand("kinv").setExecutor(new MainCmd());
		DataManager.load();
		if(SET.backupOnServerEnabled){
			for(Player p : Bukkit.getOnlinePlayers()) DataManager.createBackup(p);
		}
		if(SET.autoBackupEnabled) startBackup();
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerKickListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
	}
	
	@Override
	public void onDisable(){
		if(SET.backupOnServerDisabled){
			for(Player p : Bukkit.getOnlinePlayers()) DataManager.createBackup(p);
		}
	}
	
	public static KInvBackup getInst(){
		return inst;
	}
	
	private void startBackup(){
		Bukkit.getScheduler().runTaskTimer(this, new Runnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					DataManager.createBackup(p);
				}
				Bukkit.getConsoleSender().sendMessage(StringUtils.color("&7&l[KInvBackup] &6Inventories backuped!"));
			}
		}, SET.autoBackupInterval*20, SET.autoBackupInterval*20);
	}
}