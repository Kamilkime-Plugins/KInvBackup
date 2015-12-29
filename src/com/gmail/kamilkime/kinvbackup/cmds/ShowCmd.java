package com.gmail.kamilkime.kinvbackup.cmds;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.kamilkime.kinvbackup.Main;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class ShowCmd {

	public void show(CommandSender sender, Command cmd, String l, String args[]){
		if(!sender.hasPermission("kinv.show")){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoPermission));
			return;
		}
		if(args.length !=3){
			sender.sendMessage(StringUtils.color(StringUtils.insertCommand("/kinv show <nick> <worldName>")));
			return;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(StringUtils.color(Main.SET.msgPlayerOnlyCommand));
			return;
		}
		if(Main.getDataManager().getPlayerFile(args[1]) == null){
			sender.sendMessage(StringUtils.color(Main.SET.msgNoSuchBackup));
			return;
		}
		Player p = (Player) sender;
		Map<Integer, ItemStack> items = Main.getDataManager().getBackupedItems(Main.getDataManager().getPlayerFile(args[1]), args[2]);
		if(items == null){
			p.sendMessage(Main.SET.msgNoBackupForThisWorld.replace("{WORLD}", args[2]));
			return;
		}
		Inventory inv = Bukkit.createInventory(null, 45, StringUtils.color("&5&lKInvBackup backup display"));
		for(int i : items.keySet()) inv.setItem(i, items.get(i));
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		for(int i=40; i<45; i++) inv.setItem(i, is); 
		p.openInventory(inv);
	}
}