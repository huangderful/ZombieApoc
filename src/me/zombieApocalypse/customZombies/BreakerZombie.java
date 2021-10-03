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

import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_16_R3.PathfinderGoalZombieAttack;

public class BreakerZombie extends CustomZombie{
	long time1;
	int level;
	
	public BreakerZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.BLACK + "DELETION"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    time1 = System.currentTimeMillis();
	    
	    this.level = level;
	    this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(level * 1.5);
	    this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, (double)level/5.0 + 0.7, false));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, (double)level/5.0 + 0.7));
	    
	    
	    ItemStack torch = new ItemStack(Material.TORCH);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(torch);
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
		
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
		
	  }
	@Override
	public void movementTick() {
		super.movementTick();
		long time2 = System.currentTimeMillis();
		if(this.getGoalTarget() != null) {
			time1 = System.currentTimeMillis();
		}
		for(int x = -level; x < level + 1; x++) {
			for(int z = -level; z < level + 1; z++) {
				for(int y = 0; y < level + 1; y++) {
					if(!this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).getType().equals(Material.AIR)
							&& !this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).getType().equals(Material.OBSIDIAN)) {
						
						this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).setType(Material.AIR);
					}
				}
			}
		}  
		if(this.getGoalTarget() == null && time2 - time1 > 10000 && this.getHealth() > 0) {
			for(int x = -level * 2; x < level * 2; x++) {
				for(int z = -level * 2; z < level * 2; z++) {
					for(int y = -2; y < 3; y++) {
						if(!this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).getType().equals(Material.AIR)) {
							
							this.getBukkitEntity().getWorld().getBlockAt(this.getBukkitEntity().getLocation().add(x, y, z)).setType(Material.AIR);
						}
					}
				}
			}
			this.setHealth(0);

		}

		
	}
	@Override
	public ItemStack loot() {
		double random = Math.random() * 100;
		if(random < 50) {
			if(level == 1) {
				return new ItemStack(Material.WOODEN_PICKAXE, 1);

			} else if(level == 2) {
				return new ItemStack(Material.STONE_PICKAXE, 1);
			} else if(level == 3) {
				return new ItemStack(Material.GOLDEN_PICKAXE, 1);
			} else if(level == 4) {
				return new ItemStack(Material.IRON_PICKAXE, 1);
			} else if(level == 5) {
				return new ItemStack(Material.DIAMOND_PICKAXE, 1);
			}
		}
		return super.loot();

		
		
		
	}
	@Override
	public int getPoints() {
		return level * 2;
	}



}
