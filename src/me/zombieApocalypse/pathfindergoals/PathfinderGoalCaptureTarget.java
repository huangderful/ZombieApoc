package me.zombieApocalypse.pathfindergoals;

import java.util.EnumSet;

import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.PathfinderGoal;
import net.minecraft.server.v1_16_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R3.Vec3D;

public class PathfinderGoalCaptureTarget extends PathfinderGoalLeapAtTarget {
	 private final EntityInsentient a;
	  
	  private EntityLiving b;
	  
	  private final float c;
	  
	  public PathfinderGoalCaptureTarget(EntityInsentient var0, float var1) {
		super(var0, var1);
	    this.a = var0;
	    this.c = var1;
	    a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
	  }
	  
	  @Override
	  public boolean a() {
	    this.b = this.a.getGoalTarget();
	    if (this.b == null)
	      return false; 
	    if (!this.a.isOnGround())
	      return false; 
	
	    return true;
	  }
	  
	  public boolean b() {
	    return !this.a.isOnGround();
	  }
	  
	  public void c() {
		if(this.a.getPassengers().size() < 1) {
		    Vec3D var0 = this.a.getMot();
		    Vec3D var1 = new Vec3D(this.b.locX() - this.a.locX(), 0.0D, this.b.locZ() - this.a.locZ());
		    if (var1.g() > 1.0E-7D)
		      var1 = var1.d().a(0.4D).e(var0.a(0.2D)); 
		    this.a.setMot(var1.x, this.c, var1.z);
		    
		    if(distance(this.a.locX(), this.a.locY(), this.a.locZ(), this.a.getGoalTarget().locX(), this.a.getGoalTarget().locY(), this.a.getGoalTarget().locZ()) < 2) {
		    	this.a.getBukkitEntity().addPassenger(this.a.getGoalTarget().getBukkitEntity());
		    }
		}
		else {
			  Vec3D var0 = this.a.getMot();
			    Vec3D var1 = new Vec3D(this.b.locX() - this.a.locX(), 0.0D, this.b.locZ() - this.a.locZ());
			    if (var1.g() > 1.0E-7D)
			      var1 = var1.d().a(0.4D).e(var0.a(0.2D)); 
			    this.a.setMot(var1.x, this.c * 4, var1.z);
			  
		}
	  
	    
	  }
	  public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		  
		  return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	  }
	  
	  
}
