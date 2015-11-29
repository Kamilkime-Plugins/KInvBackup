package com.gmail.kamilkime.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BackupUtils {

	public static List<String> enchantsToString(Map<Enchantment, Integer> enchs){
		List<String> toReturn = new ArrayList<String>();
		if(enchs == null || enchs.isEmpty()) return toReturn;
		for(Enchantment e : enchs.keySet()){
			toReturn.add(e.getName() + "@" + enchs.get(e));
		}
		return toReturn;
	}
	
	public static Map<Enchantment, Integer> stringsToEnchants(List<String> str){
		Map<Enchantment, Integer> toReturn = new HashMap<Enchantment, Integer>();
		if(str == null || str.isEmpty()) return toReturn;
		for(String s : str){
			String[] ss = s.split("@");
			toReturn.put(Enchantment.getByName(ss[0]), Integer.parseInt(ss[1]));
		}
		return toReturn;
	}
	
	public static List<String> effectsToString(List<PotionEffect> effs){
		List<String> toReturn = new ArrayList<String>();
		if(effs == null || effs.isEmpty()) return toReturn;
		for(PotionEffect e : effs){
			toReturn.add(e.getType().toString() + "@" + e.getDuration() + "@" + e.getAmplifier() + "@" + e.isAmbient() + "@" + e.hasParticles());
		}
		return toReturn;
	}
	
	public static List<PotionEffect> stringsToEffects(List<String> str){
		List<PotionEffect> toReturn = new ArrayList<PotionEffect>();
		if(str == null || str.isEmpty()) return toReturn;
		for(String s : str){
			String[] ss = s.split("@");
			PotionEffect e = new PotionEffect(PotionEffectType.getByName(ss[0]), Integer.parseInt(ss[1]),
					Integer.parseInt(ss[2]), Boolean.parseBoolean(ss[3]), Boolean.parseBoolean(ss[4]));
			toReturn.add(e);
		}
		return toReturn;
	}
	
	public static List<Integer> colorsToString(List<Color> cols){
		List<Integer> toReturn = new ArrayList<Integer>();
		if(cols == null || cols.isEmpty()) return toReturn;
		for(Color c : cols){
			toReturn.add(c.asRGB());
		}
		return toReturn;
	}
	
	public static List<Color> stringsToColors(List<String> cols){
		List<Color> toReturn = new ArrayList<Color>();
		if(cols == null || cols.isEmpty()) return toReturn;
		for(String c : cols){
			toReturn.add(Color.fromRGB(Integer.parseInt(c)));
		}
		return toReturn;
	}
}