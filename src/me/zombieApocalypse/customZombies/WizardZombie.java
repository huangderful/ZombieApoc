package me.zombieApocalypse.customZombies;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.zombieApocalypse.pathfindergoals.PathfinderGoalDragonBreath;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;
public class WizardZombie extends CustomZombie {
	long time1;
	public WizardZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.DARK_PURPLE + "Magic Hands"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    time1 = System.currentTimeMillis();
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this,(double)level/1.5 + 0.5));
	    this.goalSelector.a(1, new PathfinderGoalDragonBreath(this, (double)level/1.5 + 0.5, false, level));
	    
	    ItemStack item = new ItemStack(Material.STICK);
		net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(item);
		item.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
		this.setSlot(EnumItemSlot.HEAD, hat);
	 }
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	 protected void initPathfinder() {
			
		    this.goalSelector.a(2, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
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
			long time2 = System.currentTimeMillis();
			if(distance(this.locX(), this.locY(), this.locZ(), this.getGoalTarget().locX(), this.getGoalTarget().locY(), this.getGoalTarget().locZ()) < 20
					&& time2 - time1 > 5000/level) {
				Fireball fireball = (Fireball) this.getBukkitEntity().getWorld().spawnEntity(this.getBukkitEntity().getLocation(), EntityType.FIREBALL);
				Vector velocity = this.getGoalTarget().getBukkitEntity().getLocation().toVector().subtract(fireball.getLocation().toVector()).normalize();
				fireball.setVelocity(fireball.getVelocity().add(velocity));
				time1 = System.currentTimeMillis();
				
			}
		}
		
		
	}
	public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		  
		  return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}
	 @Override
	  public ItemStack loot() {
		  double random = Math.random() * 100;
		  if(random < 50) {
			  ItemStack item = new ItemStack(Material.BOW);
			  item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
			  item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, level + 3);

			  return item;
		  }
		  else {
			  return super.loot();
		  }


	  } 
	 @Override
		public int getPoints() {
			return level * 9;
		}

}
