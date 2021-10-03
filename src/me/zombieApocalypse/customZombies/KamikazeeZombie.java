package me.zombieApocalypse.customZombies;



import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import me.zombieApocalypse.pathfindergoals.PathfinderGoalKamikazee;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

public class KamikazeeZombie extends CustomZombie{
	long time1;
	public KamikazeeZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.RED + "TIME TO EXPLODE BITCHES"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    time1 = System.currentTimeMillis();
	    
	    this.goalSelector.a(2, new PathfinderGoalKamikazee(this, level/2.0, false, (float)level));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, level/2.0));

	    ItemStack tnt = new ItemStack(Material.TNT);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(tnt);
		this.setSlot(EnumItemSlot.HEAD, hat);
	 }
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	 protected void initPathfinder() {
		    this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
		    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		    m();
		  }
		  
	  protected void m() {
		
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
	   
	  }
	@Override
	public void movementTick() {
		super.movementTick();
		long time2 = System.currentTimeMillis();
		if(this.getGoalTarget() != null) {
			time1 = System.currentTimeMillis();
		}
		if(this.getGoalTarget() == null && time2 - time1 > 10000 && this.getHealth() > 0) {
			this.setHealth(0);
			this.dead = true;
			
	    	this.getBukkitEntity().getWorld().createExplosion(this.getBukkitEntity().getLocation(), level * 2.0F);
		}
		
	}
	@Override
	public ItemStack loot() {
		double random = Math.random() * 100;
		if(random < 75) {
			return new ItemStack(Material.TNT, level);
		}
		return super.loot();

		
		
		
	}
	@Override
	public int getPoints() {
		return level * 8;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
