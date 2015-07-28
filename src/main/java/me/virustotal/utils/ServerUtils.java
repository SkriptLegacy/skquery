package me.virustotal.utils;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerUtils {
	
	@SuppressWarnings("unchecked")
	public static Player[] getOnlinePlayers()
	{
		try {
			Object object = Bukkit.class.getMethod("getOnlinePlayers").invoke(null);
			if(object instanceof Player[])
			{
				return (Player[])object;
			}
			else if(object instanceof Collection)
			{
				Collection<Player> pl = ((Collection<Player>)object);
				return pl.toArray(new Player[pl.size()]);
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
