package com.gmail.kamilkime.kinvbackup.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.kamilkime.kinvbackup.Main;

public class PlayerListener implements Listener{

	@EventHandler
	public void onChange(PlayerChangedWorldEvent e){
		if(Main.SET.backupOnWorldChange) Main.getDataManager().createBackup(e.getPlayer(), e.getFrom().getName());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(Main.SET.backupOnJoin) Main.getDataManager().createBackup(e.getPlayer(), e.getPlayer().getWorld().getName());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e){
		if(Main.SET.backupOnKick) Main.getDataManager().createBackup(e.getPlayer(), e.getPlayer().getWorld().getName());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(Main.SET.backupOnQuit) Main.getDataManager().createBackup(e.getPlayer(), e.getPlayer().getWorld().getName());
	}
}