package me.zombieApocalypse.customZombies;



import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.zombieApocalypse.pathfindergoals.PathfinderGoalThief;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

public class TheifZombie extends CustomZombie{
	public TheifZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.WHITE + "lol nice shield."));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    
	    this.goalSelector.a(2, new PathfinderGoalThief(this, level * 0.4 + 0.8F, false, level));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, level * 0.4 + 0.8F));
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
		
		
	}
	@Override
	public ItemStack loot() {
		double random = Math.random() * 100;
		if(random < 90) {
			double randomPiece = Math.random() * 100;
			if(level == 1) {
				if(randomPiece < 25) {
					ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
					return item;

				} else if(randomPiece < 50 ) {
					ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;

				} else if(randomPiece < 75 ) {
					ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else {
					ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;				
					
				}

			} else if(level == 2) {
				if(randomPiece < 25) {
					ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else if(randomPiece < 50 ) {
					ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else if(randomPiece < 75 ) {
					ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else {
					ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;				}

			} else if(level == 3) {
				if(randomPiece < 25) {
					ItemStack item = new ItemStack(Material.NETHERITE_HELMET, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else if(randomPiece < 50 ) {
					ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else if(randomPiece < 75 ) {
					ItemStack item = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;
				} else {
					ItemStack item = new ItemStack(Material.NETHERITE_BOOTS, 1);
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

					return item;				}

			}
		}
		return super.loot();	
	}
	@Override
	public int getPoints() {
		return level * 7;
	}

}
