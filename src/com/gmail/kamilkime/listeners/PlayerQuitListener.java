package com.gmail.kamilkime.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.data.DataManager;

public class PlayerQuitListener implements Listener{
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(KInvBackup.SET.backupOnQuit) DataManager.createBackup(e.getPlayer());
	}
}