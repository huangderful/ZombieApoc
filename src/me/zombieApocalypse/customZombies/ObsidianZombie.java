package me.zombieApocalypse.customZombies;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_16_R3.PathfinderGoalZombieAttack;

public class ObsidianZombie extends CustomZombie{
	int level;
	public ObsidianZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.BLACK + "bob"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    
	    this.level = level;
	    this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(level * 1.5);
	    
	    this.goalSelector.a(2, new PathfinderGoalZombieAttack(this,  (double)level/5.0 + 0.7, false));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this,  (double)level/5.0 + 0.7));
	    
	    ItemStack item = new ItemStack(Material.OBSIDIAN);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(item);
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
		if(this.getGoalTarget() != null) {
			if(distance(this.locX(), this.locY(), this.locZ(), this.getGoalTarget().locX(), this.getGoalTarget().locY(), this.getGoalTarget().locZ()) < 2) {
				if(this.getGoalTarget().getBukkitEntity().getWorld().getBlockAt(this.getGoalTarget().getBukkitEntity().getLocation().add(0, 2, 0)).getType().equals(Material.WATER)) {
					this.getGoalTarget().getBukkitEntity().getWorld().getBlockAt(this.getGoalTarget().getBukkitEntity().getLocation().add(0, 2, 0)).setType(Material.OBSIDIAN);
				} else {
					this.getBukkitEntity().getWorld().getBlockAt(this.getGoalTarget().getBukkitEntity().getLocation()
							.add(-3 * MathHelper.sin((float) (this.yaw * 3.1415926/180)), 1, 3 * MathHelper.cos((float ) (this.yaw * 3.1415926/180)))).setType(Material.OBSIDIAN);					
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
		if(random < 75) {
			return new ItemStack(Material.OBSIDIAN, level);
		}
		return super.loot();
		
	}
	@Override
	public int getPoints() {
		return level * 2;
	}

}
