package me.zombieApocalypse.pathfindergoals;

import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.PathfinderGoalZombieAttack;

public class PathfinderGoalKamikazee extends PathfinderGoalZombieAttack{
	private final EntityZombie b;
	  
	  private int c;
	  private float strength;
	  public PathfinderGoalKamikazee(EntityZombie var0, double var1, boolean var3, float strength) {
	    super(var0, var1, var3);
	    this.b = var0;
	    
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
	    if(distance(this.a.locX(), this.a.locY(), this.a.locZ(), this.a.getGoalTarget().locX(), this.a.getGoalTarget().locY(), this.a.getGoalTarget().locZ()) < 1) {
	    	this.a.getBukkitEntity().getWorld().createExplosion(this.a.getBukkitEntity().getLocation(), strength);
	    	this.a.setHealth(0F);
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
