package me.zombieApocalypse.customZombies;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.zombieApocalypse.pathfindergoals.PathfinderGoalThrow;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

public class ThrowerZombie extends CustomZombie{
	public ThrowerZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.AQUA + "YUH YEET"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    
	    this.goalSelector.a(2, new PathfinderGoalThrow(this, (double)level/3.0 + 0.5, false, level * 0.2 + 0.3));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this,  (double)level/3.0 + 0.7));
	    
	    ItemStack item = new ItemStack(Material.FISHING_ROD);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(item);
		this.setSlot(EnumItemSlot.HEAD, hat);
		
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(0);
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
		if(random < 10) {
			if(level == 1) {
				ItemStack item = new ItemStack(Material.WOODEN_SWORD);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				item.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
				return item;
			} else if(level == 2) {
				ItemStack item = new ItemStack(Material.STONE_SWORD);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				item.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
				return item;

			} else if(level == 3) {
				ItemStack item = new ItemStack(Material.IRON_SWORD);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				item.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
				return item;

			}else if(level == 3) {
				ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				item.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
				return item;

			}
		}
		return super.loot();	
	}
	@Override
	public int getPoints() {
		return level * 5;
	}

}
