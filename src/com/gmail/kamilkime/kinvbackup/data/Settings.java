package com.gmail.kamilkime.kinvbackup.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.gmail.kamilkime.kinvbackup.Main;
import com.gmail.kamilkime.kinvbackup.utils.ConfigUtils;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class Settings {
	
	private static Settings inst;
	public boolean enableInfoCommand;
	public boolean backupOnWorldChange;
	public boolean backupOnJoin;
	public boolean backupOnQuit;
	public boolean backupOnKick;
	public boolean backupOnServerEnabled;
	public boolean backupOnServerDisabled;
	public boolean autoBackupEnabled;
	public boolean clearBackupAfterUse;
	public boolean enablePerWorldBackup;
	public String convertToWorld;
	public String msgNoPermission;
	public String msgCorrectUsage;
	public String msgPlayerOnlyCommand;
	public String msgNoSuchPlayer;
	public String msgNoSuchBackup;
	public String msgNoBackupForThisWorld;
	public String msgInventoryRestored;
	public String msgConfigReloaded;
	public String msgBackupCreated;
	public List<String> conversionWorldPriority = new ArrayList<String>();
	public int autoBackupInterval;
	
	private static File mainDir = Main.getInst().getDataFolder();
	private static File cfgFile = new File(mainDir, "config.yml");
	private static File users = new File(mainDir, "users");

	private Settings(){
		inst = this;
	}
	
	public void load(){
		checkFiles();
		ConfigUtils.check();
		enableInfoCommand = Main.getInst().getConfig().getBoolean("enableInfoCommand");
		backupOnWorldChange = Main.getInst().getConfig().getBoolean("backupOnWorldChange");
		backupOnJoin = Main.getInst().getConfig().getBoolean("backupOnJoin");
		backupOnQuit = Main.getInst().getConfig().getBoolean("backupOnQuit");
		backupOnKick = Main.getInst().getConfig().getBoolean("backupOnKick");
		backupOnServerEnabled = Main.getInst().getConfig().getBoolean("backupOnServerEnabled");
		backupOnServerDisabled = Main.getInst().getConfig().getBoolean("backupOnServerDisabled");
		autoBackupEnabled = Main.getInst().getConfig().getBoolean("autoBackupEnabled");
		clearBackupAfterUse = Main.getInst().getConfig().getBoolean("clearBackupAfterUse");
		enablePerWorldBackup = Main.getInst().getConfig().getBoolean("enablePerWorldBackup");
		autoBackupInterval = Main.getInst().getConfig().getInt("autoBackupInterval");
		convertToWorld = Main.getInst().getConfig().getString("convertToWorld");
		msgNoPermission = StringUtils.color(Main.getInst().getConfig().getString("msgNoPermission"));
		msgCorrectUsage = StringUtils.color(Main.getInst().getConfig().getString("msgCorrectUsage"));
		msgPlayerOnlyCommand = StringUtils.color(Main.getInst().getConfig().getString("msgPlayerOnlyCommand"));
		msgNoSuchPlayer = StringUtils.color(Main.getInst().getConfig().getString("msgNoSuchPlayer"));
		msgNoSuchBackup = StringUtils.color(Main.getInst().getConfig().getString("msgNoSuchBackup"));
		msgInventoryRestored = StringUtils.color(Main.getInst().getConfig().getString("msgInventoryRestored"));
		msgConfigReloaded = StringUtils.color(Main.getInst().getConfig().getString("msgConfigReloaded"));
		msgBackupCreated = StringUtils.color(Main.getInst().getConfig().getString("msgBackupCreated"));
		msgNoBackupForThisWorld = StringUtils.color(Main.getInst().getConfig().getString("msgNoBackupForThisWorld"));
		conversionWorldPriority = Main.getInst().getConfig().getStringList("conversionWorldPriority");
		if(enablePerWorldBackup) Main.setDataManager(new PerWorldDataManager());
		else Main.setDataManager(new OnlyOneDataManager());
	}
	
	public void reload(){
		Main.getInst().reloadConfig();
		load();
	}
	
	public static Settings getInst(){
		if(inst == null) inst = new Settings();
		return inst;
	}
	
	public void checkFiles(){
		if(!mainDir.exists()) mainDir.mkdir();
		if(!cfgFile.exists()) Main.getInst().saveDefaultConfig();
		if(!users.exists()) users.mkdir();
	}
}