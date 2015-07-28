package com.w00tmast3r.skquery.util.packet.particle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
 
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParticleLibrary {
 
   private static Constructor<?> constructor;
    private static Field  playerConnection;
    private static Method  getHandle;
    private static Method  sendPacket;
 
    static {
        try {
            Class<?> clazz = getMCClass("PacketPlayOutWorldParticles");
            constructor = clazz.getConstructor(String .class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
            getHandle = getCraftClass("entity.CraftPlayer").getMethod("getHandle");
            playerConnection = getMCClass("EntityPlayer").getDeclaredField("playerConnection");
            sendPacket = getMCClass("PlayerConnection").getMethod("sendPacket", getMCClass("Packet"));
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
 
    public void sendParticleToLocation(Location loc, ParticleType particle, float xOffset, float yOffset, float zOffset, float speed, int amount, int data) {
        try {
            Object  packet = null;
            	if(Bukkit.getVersion().toLowerCase().contains("1_7"))
            		packet = constructor.newInstance(particle.getName(), (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
            	else if(Bukkit.getVersion().toLowerCase().contains("1_8"))
            	{
            		Class enumParticle = Class.forName("EnumParticle");
            		Constructor constructor = enumParticle.getConstructor(String.class, int.class, boolean.class);
            		Object obj = constructor.newInstance(particle.getName(), data, true, amount);
            		packet = constructor.newInstance(obj, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
            	}
            		
            	
            	for (Player p : Bukkit.getOnlinePlayers()) {
                if (loc.getWorld().equals(p.getWorld()) && p.getLocation().distance(loc) <= 50) {
                    Object  entityPlayer = getHandle.invoke(p);
                    Object  connection = playerConnection.get(entityPlayer);
                    sendPacket.invoke(connection, packet);
                }
            }
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
 
    public void sendPartileToPlayer(Player p, ParticleType particle, Location loc, float xOffset, float yOffset, float zOffset, float speed, int amount, int data) {
        try {
            Object  packet = null;
            if(Bukkit.getVersion().toLowerCase().contains("1_7"))
        		packet = constructor.newInstance(particle.getName(), (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
        	else if(Bukkit.getVersion().toLowerCase().contains("1_8"))
        	{
        		Class enumParticle = Class.forName("EnumParticle");
        		Constructor constructor = enumParticle.getConstructor(String.class, int.class, boolean.class);
        		Object obj = constructor.newInstance(particle.getName(), data, true, amount);
        		packet = constructor.newInstance(obj, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
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
 
    public enum ParticleType {
 
    	
        DRIP_WATER("dripWater"), 
        VOID_FOG("depthsuspend"),  
        SNOW_SHOVEL("snowshovel"), 
        MOB_SPELL("mobSpell"), 
        BUBBLE("bubble"), 
        SUSPEND("suspend"), 
        DEPTH_SUSPEND("depthSuspend"), 
        SMALL_SMOKE("townaura"), 
        CRIT("crit"), 
        SLIME("slime"), 
        EXPLOSION("explode"),
        EXPLOSION_HUGE("hugeexplosion"), 
        EXPLOSION_LARGE("largeexplode"), 
        FIREWORKS_SPARK("fireworksSpark"),
        FLYING_GLYPH("enchantmenttable"),
        HEART("heart"), 
        POTION_SWIRL("mobSpell"),
        POTION_SWIRL_TRANSPARENT("mobSpellAmbient"),
        VILLAGER_THUNDERCLOUD("angryVillager"), 
        MAGIC_CRIT("magicCrit"), 
        INSTANT_SPELL("instantSpell"), 
        WITCH_MAGIC("witchMagic"), 
        HAPPY_VILLAGER("happerVillager"), 
        NOTE("note"), 
        PORTAL("portal"), 
        ENCHANTMENT_TABLE("enchantmenttable"), 
        EXPLODE("explode"), 
        FLAME("flame"), 
        LAVADRIP("dripLava"),
        WATERDRIP("dripWater"),
        LAVA_POP("lava"), 
        PARTICLE_SMOKE("smoke"),
        LARGE_SMOKE("largesmoke"), 
        CLOUD("cloud"), 
        COLOURED_DUST("reddust"), 
        SNOWBALL_BREAK("snowballpoof"), 
        MOB_SPELL_AMBIENT("mobSpellAmbient"), 
        SPELL("spell"), 
        FOOTSTEP("footstep"), 
        SPLASH("splash"),
        ITEM_BREAK("iconcrack"),
        TILE_BREAK("blockcrack"),
        TILE_DUST("blockdust");

        
        private String  name;
        private ParticleType(String  name) 
        {
            this.name = name; 
        }
        public String  getName() 
        {
            return name;
        }
    }
}