package me.virustotal.utils;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerUtils {
	
	/*
	 * 
	 * Thanks to massivecraft and the mcore team for the cross server version compatibility <3
	 * https://github.com/MassiveCraft/MassiveCore/blob/master/src/com/massivecraft/massivecore/util/MUtil.java
	 *
	 */
	
	/*private static Method methodGetOnlinePlayers;
	//gone until I figure out why it wasn't working
	
	static
	{
		methodGetOnlinePlayers = getMethodGetOnlinePlayers();
	}
	
	private static Method getMethodGetOnlinePlayers()
	{
		Method ret = null;
		
		try
		{
			for (Method method : Bukkit.class.getDeclaredMethods())
			{
				// The method name must be getOnlinePlayers ...
				if ( ! method.getName().equals("getOnlinePlayers")) continue;
				
				// ... if we find such a method it's better than nothing ...
				if (ret == null) ret = method;
				
				// ... but if the method additionally returns a collection ...
				if ( ! method.getReturnType().isAssignableFrom(Collection.class)) continue;
				
				// ... that is preferable ...
				ret = method;
				
				// ... and we need not look any further.
				break;
			}
			
			ret.setAccessible(true);
		}
		catch (Exception e)
		{
			// If we fail we do so silently.
			// This method is probably almost never going to be used anyways.
		}
		
		return ret;
	}
	
	public static Collection<? extends Player> getOnlinePlayers()
	{
		// Fetch some kind of playersObject.
		Object playersObject = null;
		try
		{
			playersObject = Bukkit.getOnlinePlayers();
		}
		catch (Throwable t)
		{
			// That didn't work!
			// We probably just caught a NoSuchMethodError.
			// So let's try with reflection instead.
			try
			{
				playersObject = methodGetOnlinePlayers.invoke(null);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		// Now return the playersObject.
		if (playersObject instanceof Collection<?>)
		{
			@SuppressWarnings("unchecked")
			Collection<? extends Player> playersCollection = (Collection<? extends Player>)playersObject;
			return playersCollection;
		}
		else if (playersObject instanceof Player[])
		{
			Player[] playersArray = (Player[])playersObject;
			return Arrays.asList(playersArray);
		}
		else
		{
			throw new RuntimeException("Failed retrieving online players.");
		}
	}*/
	
}
