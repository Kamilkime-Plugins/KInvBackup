package com.gmail.kamilkime.kinvbackup.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import com.gmail.kamilkime.kinvbackup.Main;
import com.gmail.kamilkime.kinvbackup.utils.BackupUtils;
import com.gmail.kamilkime.kinvbackup.utils.StringUtils;

public class PerWorldDataManager extends DataManager{
	
	public void createBackup(Player p, String worldName){
		File file = getOrCreatePlayerFile(p);
		Map<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		Inventory inv = p.getInventory();
		for(int i=0; i<=39; i++){
			ItemStack is = inv.getItem(i);
			if(is == null) continue;
			items.put(i, is);
		}
		createBackup(file, items, worldName);
	}
	
	public void createBackup(File file, Map<Integer, ItemStack> items, String worldName){
		Settings.getInst().checkFiles();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		yml.set(worldName, null);
		for(int i=0; i<=39; i++){
			ItemStack is = items.get(i);
			if(is == null) continue;
			yml.set(worldName + "." + i + ".type", is.getType().toString());
			yml.set(worldName + "." + i + ".data", is.getDurability());
			yml.set(worldName + "." + i + ".amount", is.getAmount());
			yml.set(worldName + "." + i + ".enchants", BackupUtils.enchantsToString(is.getEnchantments()));
			if(is.hasItemMeta()){
				ItemMeta im = is.getItemMeta();
				if(im.hasDisplayName()) yml.set(worldName + "." + i + ".name", StringUtils.ymlaze(im.getDisplayName()));
				if(im.hasLore()) yml.set(worldName + "." + i + ".lore", StringUtils.ymlaze(im.getLore()));
				if(im instanceof LeatherArmorMeta){
					LeatherArmorMeta lam = (LeatherArmorMeta) im;
					yml.set(worldName + "." + i + ".armorMeta.color", lam.getColor().asRGB());
				}
				if(im instanceof PotionMeta){
					PotionMeta pm = (PotionMeta) im;
					yml.set(worldName + "." + i + ".potionMeta.effects", BackupUtils.effectsToString(pm.getCustomEffects()));
				}
				if(im instanceof SkullMeta){
					SkullMeta sm = (SkullMeta) im;
					if(sm.hasOwner()) yml.set(worldName + "." + i + ".skullMeta.owner", sm.getOwner());
				}
				if(im instanceof FireworkMeta){
					FireworkMeta fm = (FireworkMeta) im;
					yml.set(worldName + "." + i + ".fireworkMeta.power", fm.getPower());
					if(fm.hasEffects()){
						for(int f=0; f<fm.getEffects().size(); f++){
							FireworkEffect fe = fm.getEffects().get(f);
							yml.set(worldName + "." + i + ".fireworkMeta." + f + ".type", fe.getType().toString());
							yml.set(worldName + "." + i + ".fireworkMeta." + f + ".colors", BackupUtils.colorsToString(fe.getColors()));
							yml.set(worldName + "." + i + ".fireworkMeta." + f + ".fadeColors", BackupUtils.colorsToString(fe.getFadeColors()));
							yml.set(worldName + "." + i + ".fireworkMeta." + f + ".flicker", fe.hasFlicker());
							yml.set(worldName + "." + i + ".fireworkMeta." + f + ".trail", fe.hasTrail());
						}
					}
				}
				if(im instanceof BookMeta){
					BookMeta bm = (BookMeta) im;
					if(bm.hasAuthor()) yml.set(worldName + "." + i + ".bookMeta.author", StringUtils.ymlaze(bm.getAuthor()));
					if(bm.hasTitle()) yml.set(worldName + "." + i + ".bookMeta.title", StringUtils.ymlaze(bm.getTitle()));
					if(bm.hasPages()) yml.set(worldName + "." + i + ".bookMeta.pages", StringUtils.ymlaze(bm.getPages()));
				}
			}
		}
		try {
			yml.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long loadBackup(Player p, String worldName){
		Inventory inv = p.getInventory();
		File file = getPlayerFile(p);
		long toReturn = file.lastModified();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		Map<Integer, ItemStack> items = getBackupedItems(file, worldName);
		if(items == null) return 0L;
		inv.clear();
		p.updateInventory();
		for(int slot : items.keySet()){
			inv.setItem(slot, items.get(slot));
		}
		p.updateInventory();
		if(Main.SET.clearBackupAfterUse){
			yml.set(worldName, null);
			try {
				yml.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toReturn;
	}
	
	public Map<Integer, ItemStack> getBackupedItems(File file, String worldName){
		if(needsConversion(file)) convert(file);
		Map<Integer, ItemStack> toReturn = new HashMap<Integer, ItemStack>();
		Settings.getInst().checkFiles();
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		if(yml.getConfigurationSection(worldName) == null) return null;
		if(yml.getConfigurationSection(worldName).getKeys(false) == null) return null;
		if(yml.getConfigurationSection(worldName).getKeys(false).isEmpty()) return null;
		ConfigurationSection cs = yml.getConfigurationSection(worldName);
		for(String s : cs.getKeys(false)){
			int slot = Integer.parseInt(s);
			ItemStack is = new ItemStack(Material.getMaterial(cs.getString(s + ".type")), Integer.parseInt(cs.getString(s + ".amount")),
					Short.parseShort(cs.getString(s + ".data")));
			is.addUnsafeEnchantments(BackupUtils.stringsToEnchants(cs.getStringList(s + ".enchants")));
			ItemMeta im = is.getItemMeta();
			if(yml.get(s + ".name") !=null) im.setDisplayName(StringUtils.unymlaze(cs.getString(s + ".name")));
			if(yml.get(s + ".lore") !=null) im.setLore(StringUtils.unymlaze(cs.getStringList(s + ".lore")));
			if(yml.get(s + ".armorMeta") !=null) ((LeatherArmorMeta)im).setColor(Color.fromRGB(cs.getInt(s + ".armorMeta.color")));
			if(yml.get(s + ".potionMeta") !=null){
				for(PotionEffect pe : BackupUtils.stringsToEffects(cs.getStringList(s + ".potionMeta.effects"))){
					((PotionMeta)im).addCustomEffect(pe, true);
				}
			}
			if(yml.get(s + ".skullMeta") !=null) ((SkullMeta)im).setOwner(cs.getString(s + ".skullMeta.owner"));
			if(yml.get(s + ".fireworkMeta") !=null){
				FireworkMeta fm = (FireworkMeta) im;
				ConfigurationSection cs1 = cs.getConfigurationSection(s + ".fireworkMeta");
				fm.setPower(yml.getInt(s + ".fireworkMeta.power"));
				for(String fe : cs1.getKeys(false)){
					if(fe.equalsIgnoreCase("power")) continue;
					FireworkEffect f = FireworkEffect.builder().with(FireworkEffect.Type.valueOf(cs1.getString(fe + ".type")))
					.withColor(BackupUtils.stringsToColors(cs1.getStringList(fe + ".colors")))
					.withFade(BackupUtils.stringsToColors(cs1.getStringList(fe + ".fadeColors")))
					.trail(cs1.getBoolean(fe + ".trail")).flicker(cs1.getBoolean(fe + ".trail"))
					.build();
					fm.addEffect(f);
				}
			}
			if(yml.get(s + ".bookMeta") !=null){
				BookMeta bm = (BookMeta) im;
				bm.setAuthor(StringUtils.unymlaze(cs.getString(s + ".bookMeta.author")));
				bm.setTitle(StringUtils.unymlaze(cs.getString(s + ".bookMeta.title")));
				bm.setPages(StringUtils.unymlaze(cs.getStringList(s + ".bookMeta.pages")));
			}
			is.setItemMeta(im);
			toReturn.put(slot, is);
		}
		return toReturn;
	}
	
	public void convert(File file){
		OnlyOneDataManager dm = new OnlyOneDataManager();
		String worldName = Main.SET.convertToWorld;
		Map<Integer, ItemStack> items = dm.getBackupedItems(file, worldName);
		try {
			new PrintWriter(file.getAbsolutePath()).close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		createBackup(file, items, worldName);
	}
	
	public boolean needsConversion(File file){
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		for(String s : yml.getKeys(false)){
			try{
				Integer.parseInt(s);
				return true;
			} catch(Exception e){
				continue;
			}
		}
		return false;
	}
	
	public boolean containsWorld(File file, String worldName){
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		for(String s : yml.getKeys(false)){
			if(s.equalsIgnoreCase(worldName)) return true;
		}
		return false;
	}
	
	public String getFirstWorld(File file){
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		return (String) yml.getKeys(false).toArray()[0];
	}
}