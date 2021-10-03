package me.zombieApocalypse.customZombies;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.World;

public abstract class CustomZombie extends EntityZombie{
	int level;
	public CustomZombie(EntityTypes<? extends EntityZombie> entitytypes, World world, int level) {
		super(entitytypes, world);
		this.level = level;
		// TODO Auto-generated constructor stub
	}
	
	public ItemStack loot() {
		return new ItemStack(Material.ROTTEN_FLESH, 1);
	}

	public int getLevel() {
		return level;
	}
	public int getPoints() {
		return 1;
	}
	public void setAI() {
		this.setNoAI(true);
	}

}
