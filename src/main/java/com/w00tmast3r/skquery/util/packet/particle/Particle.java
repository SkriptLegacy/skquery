package com.w00tmast3r.skquery.util.packet.particle;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Particle {

    private float xO = 0;
    private float yO = 0;
    private float zO = 0;
    private int data = 0;
    private int amount = 1;
    private final String particle;

    public Particle() {
        particle = "";
    }

    public Particle(String particle) {
        this.particle = particle;
    }

    public Particle(ParticleType particle) {
        this.particle = particle.getId();
    }

    public Particle(ParticleTypes particle) {
        this.particle = particle.getId();
    }

    public void setXOffset(float xO) {
        this.xO = xO;
    }

    public void setYOffset(float yO) {
    	this.yO = yO;
    }

    public void setZOffset(float zO) {
    	this.zO = zO;
    }

    public void setData(int data) {
    	this.data = data;
    }

    public void setAmount(int amount) {
    	this.amount = amount;
    }

    public void play(Location loc, Player... players) {

    	for(Player p : players)
    	{
    		p.spigot().playEffect(loc, Effect.getByName(this.particle), this.data, 0, this.xO, this.yO,this.zO, 0.0F, this.amount, 2);
    	}
    	
    	

    }
}
