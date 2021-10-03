package me.zombieApocalypse.customZombies;



import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_16_R3.PathfinderGoalZombieAttack;

public class RunningJumpingZombie extends CustomZombie{

	boolean jumper;
	public RunningJumpingZombie(Location loc, boolean jumper, int level) {
	    super(EntityTypes.ZOMBIE, (((CraftWorld)loc.getWorld()).getHandle()), level);
	    this.setCustomName(new ChatComponentText(ChatColor.GREEN + "Regular zombie"));
	    this.setCustomNameVisible(true);
	    this.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    this.jumper = jumper;
	    
	    this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(level * 1.2);
	    this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, level * 0.75 + 0.25, false));
	    this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, level * 0.75 + 0.25));
	    if(jumper) {
			this.goalSelector.a(1, new PathfinderGoalLeapAtTarget(this, level * 0.5F));
		}
	    double randomHead = Math.random() * 100;
	    if(randomHead < 50) {
	    	ItemStack item = new ItemStack(Material.SLIME_BLOCK);
			net.minecraft.server.v1_16_R3.ItemStack hat = CraftItemStack.asNMSCopy(item);
			item.addUnsafeEnchantment(Enchantment.THORNS, level);
			this.setSlot(EnumItemSlot.HEAD, hat);
			Zombie z = (Zombie)(this.getBukkitEntity());
			z.setLootTable(null);
	    }
	    

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
	  public ItemStack loot() {
		  return new ItemStack(Material.BLAZE_ROD, level);



	  }
	  @Override
		public int getPoints() {
			return level * 2;
		}
}
