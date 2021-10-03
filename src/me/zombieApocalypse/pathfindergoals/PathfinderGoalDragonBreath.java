package me.zombieApocalypse.pathfindergoals;


import org.bukkit.Color;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.PathfinderGoalZombieAttack;

public class PathfinderGoalDragonBreath extends PathfinderGoalZombieAttack{
	private final EntityZombie b;
		AreaEffectCloud cloud;
	  private int c;
	  long time1;
	  int strength;
	  public PathfinderGoalDragonBreath(EntityZombie var0, double var1, boolean var3, int strength) {
	    super(var0, var1, var3);
	    this.b = var0;
	    time1 = System.currentTimeMillis();
	    this.strength = strength;
	  }
	  
	  public void c() {
	    super.c();
	    this.c = 0;
	  }
	  
	  public void d() {
	    super.d();
	    this.b.setAggressive(false);
	  }
	  
	  public void e() {
	    super.e();
	    this.c++;
	    long time2 = System.currentTimeMillis();
	    
	    if(distance(this.a.locX(), this.a.locY(), this.a.locZ(), this.a.getGoalTarget().locX(), this.a.getGoalTarget().locY(), this.a.getGoalTarget().locZ()) < 2
	    		&& time2 - time1 > 5000) {
	    	cloud = (AreaEffectCloud) this.a.getBukkitEntity().getWorld().spawnEntity(this.a.getBukkitEntity().getLocation(), EntityType.AREA_EFFECT_CLOUD);
	    	cloud.setDuration(100);
	    	cloud.setRadiusPerTick(0);
	    	cloud.setColor(Color.WHITE);
	    	cloud.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 100, strength), true);
	    	cloud.setWaitTime(0);
	    	time1 = System.currentTimeMillis();
		
	    }
	    if (this.c >= 5 && j() < k() / 2) {
	      this.b.setAggressive(true);
	    } else {
	      this.b.setAggressive(false);
	    }  
	  }
	  public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		  
		  return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	  }
}
