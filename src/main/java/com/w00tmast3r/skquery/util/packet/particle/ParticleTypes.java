package com.w00tmast3r.skquery.util.packet.particle;

public enum ParticleTypes {
	 
	
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
    private ParticleTypes(String  name) 
    {
        this.name = name; 
    }
    public String getName() 
    {
        return name;
    }
}