package com.gmail.kamilkime.kinvbackup.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class InventoryClickListener implements Listener {

	private static final String INV_TITLE = StringUtils.color("&5&lKInvBackup backup display");

	@EventHandler
	public void onClick(InventoryClickEvent e){
		if(e.getInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType().equals(Material.AIR)) return;
		if(e.getInventory().getTitle() == null) return;
		if(e.getInventory().getTitle().equals(INV_TITLE)){
			e.setCancelled(true);
			e.getWhoClicked().openInventory(e.getInventory());
		}
	}
}