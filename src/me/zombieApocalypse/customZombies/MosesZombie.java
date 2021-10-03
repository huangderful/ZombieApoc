package me.zombieApocalypse.customZombies;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import me.zombieApocalypse.pathfindergoals.PathfinderGoalDragonBreath;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

public class MosesZombie extends CustomZombie {
	long time1;
	public MosesZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.YELLOW + "Have fun"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    time1 = System.currentTimeMillis();
	    
	    
	    this.goalSelector.a(1, new PathfinderGoalDragonBreath(this, (double)level/1.5 + 0.5, false, level));
	    
	    ItemStack item = new ItemStack(Material.NAUTILUS_SHELL);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(item);
		this.setSlot(EnumItemSlot.HEAD, hat);

	 }
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	 protected void initPathfinder() {
			
		    this.goalSelector.a(2, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
		    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		    m();
		  }
		  
	  protected void m() {
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 0.5D));
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
	  }
	@Override
	public void movementTick() {
		super.movementTick();
		this.extinguish();
		if(this.getGoalTarget() != null) {
			long time2 = System.currentTimeMillis();
			if(distance(this.locX(), this.locY(), this.locZ(), this.getGoalTarget().locX(), this.getGoalTarget().locY(), this.getGoalTarget().locZ()) < 20
					&& time2 - time1 > 5000/level) {
				double randomX = Math.random() * 12/level;
				double randomZ = Math.random() * 12/level;
				Location loc = this.getGoalTarget().getBukkitEntity().getLocation().add(randomX - 6/level, 0, randomZ - 6/level);
				this.getBukkitEntity().getWorld().strikeLightning(loc);
				
				time1 = System.currentTimeMillis();
			}
		}
		
		for(int x = -5; x < 5; x++) {
			for(int z = -5; z < 5; z++) {
				for(int y = -2; y < 3; y++) {
					if(this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).getType().equals(Material.WATER)) {
						
						this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).setType(Material.AIR);
					}
				}
			}
		}

		
	}
	public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		  
		  return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}
	@Override
	public ItemStack loot() {
		double random = Math.random() * 100;
		if(random < 20) {
			
			return new ItemStack(Material.GOLDEN_CARROT, level + 10);
			
		} else {
			return new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, level * 3);

		}

		
		
		
	}
	@Override
	public int getPoints() {
		return level * 10;
	}

}
