package me.zombieApocalypse.pathfindergoals;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.PathfinderGoalZombieAttack;

public class PathfinderGoalThief extends PathfinderGoalZombieAttack{
	private final EntityZombie b;
	ItemStack air = new ItemStack(Material.AIR);
	 ItemMeta m = air.getItemMeta();
	    net.minecraft.server.v1_16_R3.ItemStack airHand = CraftItemStack.asNMSCopy(air);
	  private int c;
	  int level;
	  public PathfinderGoalThief(EntityZombie var0, double var1, boolean var3, int level) {
	    super(var0, var1, var3);
	    this.b = var0;
	    this.level = level;
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
		  if(distance(this.a.locX(), this.a.locY(), this.a.locZ(), this.a.getGoalTarget().locX(), this.a.getGoalTarget().locY(), this.a.getGoalTarget().locZ()) < 2) {
			  
			  if(level == 1 && Math.random() < 0.5) {
				  net.minecraft.server.v1_16_R3.ItemStack originalOffHand = this.a.getGoalTarget().getItemInOffHand();
				  ItemStack offHandItem = CraftItemStack.asBukkitCopy(originalOffHand);
				  if(!offHandItem.getType().equals(Material.AIR)) {
					  this.a.getBukkitEntity().getWorld().dropItem(this.a.getBukkitEntity().getLocation(), offHandItem);
					  this.a.getGoalTarget().setSlot(EnumItemSlot.OFFHAND, airHand);
				  }
				  
			  }
			  if(!this.a.getGoalTarget().getItemInMainHand().equals(airHand) && level > 2) {
				  net.minecraft.server.v1_16_R3.ItemStack originalMainHand = this.a.getGoalTarget().getItemInMainHand();		 	    
				  ItemStack mainHandItem = CraftItemStack.asBukkitCopy(originalMainHand);
				  if(!mainHandItem.getType().equals(Material.AIR)) {
					  this.a.getBukkitEntity().getWorld().dropItem(this.a.getBukkitEntity().getLocation(), mainHandItem);
					  this.a.getGoalTarget().setSlot(EnumItemSlot.OFFHAND, airHand);
				  }
			  }
			  
			  if(!this.a.getGoalTarget().getItemInOffHand().equals(airHand) && level > 1) {
				  net.minecraft.server.v1_16_R3.ItemStack originalOffHand = this.a.getGoalTarget().getItemInOffHand();
				  ItemStack offHandItem = CraftItemStack.asBukkitCopy(originalOffHand);
				  if(!offHandItem.getType().equals(Material.AIR)) {
					  this.a.getBukkitEntity().getWorld().dropItem(this.a.getBukkitEntity().getLocation(), offHandItem);
					  this.a.getGoalTarget().setSlot(EnumItemSlot.OFFHAND, airHand);
				  }
			  }
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
