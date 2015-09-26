package com.w00tmast3r.skquery.util.packet.particle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
 



import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.w00tmast3r.skquery.util.Reflection;

public class ParticleLibrary {
 
   private static Constructor<?> constructor;
    private static Field  playerConnection;
    private static Method  getHandle;
    private static Method  sendPacket;
 
    static {
        try {
            Class<?> clazz = getMCClass("PacketPlayOutWorldParticles");
            constructor = null;
            if(Bukkit.getVersion().contains("1.7"))
            	constructor = clazz.getConstructor(String.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
            else if(Bukkit.getVersion().contains("1.8"))
            {
            	String particleString = "net.minecraft.server." + Reflection.getServerVersion() + "." + "EnumParticle";
        		Class enumParticle = Class.forName(particleString);
            	constructor = clazz.getConstructor(enumParticle, boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int[].class);
            }   
            getHandle = getCraftClass("entity.CraftPlayer").getMethod("getHandle");
            playerConnection = getMCClass("EntityPlayer").getDeclaredField("playerConnection");
            sendPacket = getMCClass("PlayerConnection").getMethod("sendPacket", getMCClass("Packet"));
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
 
    public void sendPartileToPlayer(Player p, ParticleTypes particle, Location loc, float xOffset, float yOffset, float zOffset, float speed, int amount, int data) {
        try {
            Object  packet = null;
            if(Bukkit.getVersion().toLowerCase().contains("1.7"))
            {
            	packet = constructor.newInstance(particle.getName(), (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
            }
        	else if(Bukkit.getVersion().toLowerCase().contains("1.8"))
        	{
        		String particleString = "net.minecraft.server." + Reflection.getServerVersion() + "." + "EnumParticle";
        		Class<Enum> enumParticle = (Class<Enum>) Class.forName(particleString);
        		Method meth = enumParticle.getDeclaredMethod("valueOf", String.class);
        		String format = particle.getName().toUpperCase();
        		if(format.contains("_"))
        			format = format.replace(" ", "_");
        		Object obj = meth.invoke(null, format);
        		
        		packet = constructor.newInstance(obj, true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount, new int[0]);
        	}
            Object  entityPlayer = getHandle.invoke(p);
            Object  connection = playerConnection.get(entityPlayer);
            sendPacket.invoke(connection, packet);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
 
    private static Class<?> getMCClass(String  name) {
        String  version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String  className = "net.minecraft.server." + version + name;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }
        return clazz;
    }
 
    private static Class<?> getCraftClass(String  name) {
        String  version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String  className = "org.bukkit.craftbukkit." + version + name;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }
        return clazz;
    }
}