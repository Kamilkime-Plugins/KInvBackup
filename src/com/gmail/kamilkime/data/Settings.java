package com.gmail.kamilkime.data;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.utils.ConfigUtils;
import com.gmail.kamilkime.utils.StringUtils;

public class Settings {
	
	private static Settings inst;
	public boolean enableInfoCommand;
	public boolean backupOnJoin;
	public boolean backupOnQuit;
	public boolean backupOnKick;
	public boolean backupOnServerEnabled;
	public boolean backupOnServerDisabled;
	public boolean autoBackupEnabled;
	public boolean clearBackupAfterUse;
	public String msgNoPermission;
	public String msgCorrectUsage;
	public String msgPlayerOnlyCommand;
	public String msgNoSuchPlayer;
	public String msgNoSuchBackup;
	public String msgInventoryRestored;
	public String msgConfigReloaded;
	public String msgBackupCreated;
	public int autoBackupInterval;

	private Settings(){
		inst = this;
	}
	
	public void load(){
		ConfigUtils.check();
		enableInfoCommand = KInvBackup.getInst().getConfig().getBoolean("enableInfoCommand");
		backupOnJoin = KInvBackup.getInst().getConfig().getBoolean("backupOnJoin");
		backupOnQuit = KInvBackup.getInst().getConfig().getBoolean("backupOnQuit");
		backupOnKick = KInvBackup.getInst().getConfig().getBoolean("backupOnKick");
		backupOnServerEnabled = KInvBackup.getInst().getConfig().getBoolean("backupOnServerEnabled");
		backupOnServerDisabled = KInvBackup.getInst().getConfig().getBoolean("backupOnServerDisabled");
		autoBackupEnabled = KInvBackup.getInst().getConfig().getBoolean("autoBackupEnabled");
		clearBackupAfterUse = KInvBackup.getInst().getConfig().getBoolean("clearBackupAfterUse");
		autoBackupInterval = KInvBackup.getInst().getConfig().getInt("autoBackupInterval");
		msgNoPermission = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgNoPermission"));
		msgCorrectUsage = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgCorrectUsage"));
		msgPlayerOnlyCommand = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgPlayerOnlyCommand"));
		msgNoSuchPlayer = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgNoSuchPlayer"));
		msgNoSuchBackup = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgNoSuchBackup"));
		msgInventoryRestored = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgInventoryRestored"));
		msgConfigReloaded = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgConfigReloaded"));
		msgBackupCreated = StringUtils.color(KInvBackup.getInst().getConfig().getString("msgBackupCreated"));
	}
	
	public void reload(){
		KInvBackup.getInst().reloadConfig();
		load();
	}
	
	public static Settings getInst(){
		if(inst == null) inst = new Settings();
		return inst;
	}
}