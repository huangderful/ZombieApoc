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

public class TeleporterZombie extends CustomZombie{
	long time1;
	int level;
	public TeleporterZombie(Location loc, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.LIGHT_PURPLE + "VWOOMP"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    time1 = System.currentTimeMillis();
	    
	    this.level = level;
	    
	    this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(level * 1.2);
	    
	    ItemStack item = new ItemStack(Material.ENDER_PEARL);
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
		
	    this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1D, false));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1D));
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
	   
	  }
	@Override
	public void movementTick() {
		super.movementTick();
		if(this.getGoalTarget() != null) {
			long time2 = System.currentTimeMillis();
			if(distance(this.locX(), this.locY(), this.locZ(), this.getGoalTarget().locX(), this.getGoalTarget().locY(), this.getGoalTarget().locZ()) > 30.0/level
					&& time2 - time1 > 10000/level) {
				this.getBukkitEntity().teleport(this.getGoalTarget().getBukkitEntity().getLocation()
						.add(-3 * MathHelper.sin((float) (this.yaw * 3.1415926/180)), 0, 3 * MathHelper.cos((float) (this.yaw * 3.1415926/180))));
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
		if(random < 75) {
			return new ItemStack(Material.ENDER_PEARL, level *2);
		}
		return super.loot();	
	}
	@Override
	public int getPoints() {
		return level * 5;
	}

}
