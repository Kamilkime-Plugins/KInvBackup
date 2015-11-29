package com.gmail.kamilkime.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import com.gmail.kamilkime.KInvBackup;
import com.gmail.kamilkime.data.DataManager;

public class PlayerKickListener implements Listener{
	
	@EventHandler
	public void onKick(PlayerKickEvent e){
		if(KInvBackup.SET.backupOnKick) DataManager.createBackup(e.getPlayer());
	}
}