package com.gmail.kamilkime.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.data.DataManager;

public class PlayerJoinListener implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(KInvBackup.SET.backupOnJoin) DataManager.createBackup(e.getPlayer());
	}
}