package me.zombieApocalypse.customZombies;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import me.zombieApocalypse.pathfindergoals.PathfinderGoalCaptureTarget;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;


public class CaptureZombie extends CustomZombie{
	double launchDist;
	public CaptureZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.STRIKETHROUGH + "lol fall damage."));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    
		this.goalSelector.a(1, new PathfinderGoalCaptureTarget(this, level/1.3F));
		ItemStack item = new ItemStack(Material.SADDLE);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(item);
		this.setSlot(EnumItemSlot.HEAD, hat);
		 

	 }
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	 protected void initPathfinder() {
		    this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
		    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		    m();
		  }
		  
	  protected void m() {
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1D));
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
	  }
	@Override
	public void movementTick() {
		super.movementTick();
		
	}
	@Override
	public ItemStack loot() {
		double random = Math.random() * 100;
		if(random < 50) {
			int numOfItemsDropped = (int)(Math.random() * 3) + 1;
			if(level == 1) {
				return new ItemStack(Material.OAK_LOG, numOfItemsDropped);
			} else if(level == 2) {
				return new ItemStack(Material.IRON_INGOT, numOfItemsDropped);
			} else if(level == 3) {
				return new ItemStack(Material.GOLD_INGOT, numOfItemsDropped);
			} else if(level == 4) {
				return new ItemStack(Material.DIAMOND, numOfItemsDropped);
			} 
		}
		return super.loot();

		
		
		
	}
	@Override
	public int getPoints() {
		return level;
	}
}
