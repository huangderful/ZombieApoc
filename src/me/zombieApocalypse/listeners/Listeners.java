package me.zombieApocalypse.listeners;
 
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import me.zombieApocalypse.commands.Commands;
import me.zombieApocalypse.customZombies.BreakerZombie;
import me.zombieApocalypse.customZombies.CaptureZombie;
import me.zombieApocalypse.customZombies.CustomZombie;
import me.zombieApocalypse.customZombies.KamikazeeZombie;
import me.zombieApocalypse.customZombies.MosesZombie;
import me.zombieApocalypse.customZombies.ObsidianZombie;
import me.zombieApocalypse.customZombies.RunningJumpingZombie;
import me.zombieApocalypse.customZombies.TeleporterZombie;
import me.zombieApocalypse.customZombies.TheifZombie;
import me.zombieApocalypse.customZombies.ThrowerZombie;
import me.zombieApocalypse.customZombies.WizardZombie;
import net.minecraft.server.v1_16_R3.WorldServer;

public class Listeners implements Listener{
	
	Commands com;
	public Listeners(Commands com) {
		this.com = com;
	}
	
	@EventHandler
	public void a(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GOLD_BLOCK)) {
			
			KamikazeeZombie s = new KamikazeeZombie(event.getPlayer().getLocation(), 6);
			WorldServer w = ((CraftWorld)event.getPlayer().getWorld()).getHandle();
			w.addEntity(s);
		}
		
		
		
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity().getType().equals(EntityType.ZOMBIE) &&
				(event.getCause().equals(EntityDamageEvent.DamageCause.FALL) || event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
				|| event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
			event.setCancelled(true);
		}

	}
	@EventHandler
	public void entitySpawn(EntitySpawnEvent event) {
		WorldServer w = ((CraftWorld)event.getEntity().getWorld()).getHandle();

		if(event.getEntityType().equals(EntityType.CREEPER)) {
			event.setCancelled(true);
			KamikazeeZombie s = new KamikazeeZombie(event.getLocation(), 2);
			w.addEntity(s);


		} else if(event.getEntityType().equals(EntityType.HUSK)) {
			event.setCancelled(true);
			CaptureZombie s = new CaptureZombie(event.getLocation(), 3);
			w.addEntity(s);


		} else if(event.getEntityType().equals(EntityType.SKELETON)) {
			event.setCancelled(true);
			ThrowerZombie s = new ThrowerZombie(event.getLocation(), 2);
			w.addEntity(s);


		}
		else if(event.getEntityType().equals(EntityType.PIGLIN)) {
			event.setCancelled(true);
			BreakerZombie s = new BreakerZombie(event.getLocation(), 3);
			w.addEntity(s);


		}else if(event.getEntityType().equals(EntityType.ZOMBIFIED_PIGLIN)) {
			event.setCancelled(true);
			MosesZombie s = new MosesZombie(event.getLocation(), 1);
			w.addEntity(s);


		}else if(event.getEntityType().equals(EntityType.SPIDER)) {
			event.setCancelled(true);
			TheifZombie s = new TheifZombie(event.getLocation(), 1);
			w.addEntity(s);


		}else if(event.getEntityType().equals(EntityType.ENDERMAN)) {
			event.setCancelled(true);
			TeleporterZombie s = new TeleporterZombie(event.getLocation(), 1);
			w.addEntity(s);


		}else if(event.getEntityType().equals(EntityType.WITCH)) {
			event.setCancelled(true);
			MosesZombie s = new MosesZombie(event.getLocation(), 1);
			w.addEntity(s);


		} else if(event.getEntityType().equals(EntityType.DROWNED)) {
			((Drowned)event.getEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT));
		}
	
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDeath(EntityDeathEvent event) {
		if(((CraftEntity)event.getEntity()).getHandle() instanceof CustomZombie) {
			CustomZombie z = (CustomZombie)((CraftEntity)event.getEntity()).getHandle();
			ItemStack item = z.loot();
			if(!item.getType().equals(Material.AIR)) {
				try {
					z.getBukkitEntity().getWorld().dropItem(z.getBukkitEntity().getLocation(), item);

				} catch(Exception e) {
					System.out.println("don't worry about it, the jar compiled badly.");
				}
			}
		}

	}
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent e) {
		if(com.getWaveNum() <= 2) {
			double spawn = Math.random() * 100;
			WorldServer w = ((CraftWorld)e.getPlayer().getWorld()).getHandle();
			
			if(spawn < 0.005) {
				double magic = Math.random() * 100;
				double randomZomb = Math.random() * 100;
				double randX = Math.random() * 10 - 5;
				double randZ = Math.random() * 10 - 5;
				
				double smartSpawn = Math.random() * 100;

				Location loc = e.getPlayer().getLocation().add(30 + randX, 0, 30 + randZ);
				Block b;
				if(smartSpawn < 50) {
					 b = e.getPlayer().getWorld().getBlockAt(loc);
				} else {
					 b = e.getPlayer().getWorld().getHighestBlockAt(loc);
				}
				loc = b.getLocation().add(0, 1, 0);
				if(magic < 80) {
					
					for(int x = 0; x < (int)(Math.random() * 4) + 1; x++) {
						if(randomZomb < 14) {
							int level = (int)(Math.random() * 3) + 1;
							BreakerZombie s = new BreakerZombie(loc, level);
							w.addEntity(s);
						} else if(randomZomb < 28) {
							int level = (int)(Math.random() * 3) + 1;
							CaptureZombie s = new CaptureZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 42) {
							int level = (int)(Math.random() * 3) + 1;
							KamikazeeZombie s = new KamikazeeZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 56) {
							TeleporterZombie s = new TeleporterZombie(loc, 1);
							w.addEntity(s);
						}else if(randomZomb < 70) {
							ThrowerZombie s = new ThrowerZombie(loc, 1);
							w.addEntity(s);
						}else if(randomZomb < 84) {
							ObsidianZombie s = new ObsidianZombie(loc, 1);
							w.addEntity(s);
						}else {
							int level = (int)(Math.random() * 3) + 1;
							if(magic < 40) {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, false, level);
								w.addEntity(s);
							}
							else {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, true, level);
								w.addEntity(s);
							}
						}
					}
					
				}
				else {

					if(randomZomb < 33) {
						WizardZombie s = new WizardZombie(loc, 1);
						w.addEntity(s);
					} else if(randomZomb < 66) {
						TheifZombie s = new TheifZombie(loc, 1);
						w.addEntity(s);
					} else {
						MosesZombie s = new MosesZombie(loc, 1);
						w.addEntity(s);
					} 
				}
				
				
				
			}
		}
		if(com.getWaveNum() > 2 && com.getWaveNum() < 5) {
			double spawn = Math.random() * 100;
			WorldServer w = ((CraftWorld)e.getPlayer().getWorld()).getHandle();
			
			if(spawn < 0.1) {
				double magic = Math.random() * 100;
				double randomZomb = Math.random() * 100;
				double randX = Math.random() * 10 - 5;
				double randZ = Math.random() * 10 - 5;
				
				double smartSpawn = Math.random() * 100;

				Location loc = e.getPlayer().getLocation().add(25 + randX, 0, 25 + randZ);
				Block b;
				if(smartSpawn < 50) {
					 b = e.getPlayer().getWorld().getBlockAt(loc);
				} else {
					 b = e.getPlayer().getWorld().getHighestBlockAt(loc);
				}
				loc = b.getLocation().add(0, 1, 0);
				if(magic < 80) {
					
					for(int x = 0; x < (int)(Math.random() * 4) + 1; x++) {
						if(randomZomb < 14) {
							int level = (int)(Math.random() * 3) + 1;
							BreakerZombie s = new BreakerZombie(loc, level);
							w.addEntity(s);
						} else if(randomZomb < 28) {
							int level = (int)(Math.random() * 3) + 1;
							CaptureZombie s = new CaptureZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 42) {
							int level = (int)(Math.random() * 3) + 1;
							KamikazeeZombie s = new KamikazeeZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 56) {
							TeleporterZombie s = new TeleporterZombie(loc, 1);
							w.addEntity(s);
						}else if(randomZomb < 70) {
							ThrowerZombie s = new ThrowerZombie(loc, 1);
							w.addEntity(s);
						}else if(randomZomb < 84) {
							ObsidianZombie s = new ObsidianZombie(loc, 1);
							w.addEntity(s);
						}else {
							int level = (int)(Math.random() * 3) + 1;
							if(magic < 40) {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, false, level);
								w.addEntity(s);
							}
							else {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, true, level);
								w.addEntity(s);
							}
						}
					}
					
				}
				else {

					if(randomZomb < 33) {
						WizardZombie s = new WizardZombie(loc, 1);
						w.addEntity(s);
					} else if(randomZomb < 66) {
						TheifZombie s = new TheifZombie(loc, 1);
						w.addEntity(s);
					} else {
						MosesZombie s = new MosesZombie(loc, 1);
						w.addEntity(s);
					} 
				}
				
				
				
			}
		} else if(com.getWaveNum() < 8 && com.getWaveNum() > 4) {
			double spawn = Math.random() * 100;
			WorldServer w = ((CraftWorld)e.getPlayer().getWorld()).getHandle();
			
			if(spawn < 0.5) {
				double magic = Math.random() * 100;
				double randomZomb = Math.random() * 100;
				double randX = Math.random() * 10 - 5;
				double randZ = Math.random() * 10 - 5;
				
				double smartSpawn = Math.random() * 100;

				Location loc = e.getPlayer().getLocation().add(22 + randX, 0, 22 + randZ);
				Block b;
				if(smartSpawn < 50) {
					 b = e.getPlayer().getWorld().getBlockAt(loc);
				} else {
					 b = e.getPlayer().getWorld().getHighestBlockAt(loc);
				}
				loc = b.getLocation().add(0, 1, 0);
				if(magic < 70) {
					
					for(int x = 0; x < (int)(Math.random() * 4) + 2; x++) {
						if(randomZomb < 14) {
							int level = (int)(Math.random() * 3) + 3;
							BreakerZombie s = new BreakerZombie(loc, level);
							w.addEntity(s);
						} else if(randomZomb < 28) {
							int level = (int)(Math.random() * 3) + 3;
							CaptureZombie s = new CaptureZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 42) {
							int level = (int)(Math.random() * 3) + 3;
							KamikazeeZombie s = new KamikazeeZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 56) {
							int level = (int)(Math.random() * 2) + 2;
							TeleporterZombie s = new TeleporterZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 70) {
							int level = (int)(Math.random() * 2) + 2;
							ThrowerZombie s = new ThrowerZombie(loc, level);
							w.addEntity(s);
						}else if(randomZomb < 84) {
							int level = (int)(Math.random() * 2) + 2;
							ObsidianZombie s = new ObsidianZombie(loc, level);
							w.addEntity(s);
						}else {
							int level = (int)(Math.random() * 2) + 3;
							if(magic < 40) {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, false, level);
								w.addEntity(s);
							}
							else {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, true, level);
								w.addEntity(s);
							}
						}
					}
					
				}
				else {

					if(randomZomb < 33) {
						WizardZombie s = new WizardZombie(loc, 2);
						w.addEntity(s);
					} else if(randomZomb < 66) {
						TheifZombie s = new TheifZombie(loc, 2);
						w.addEntity(s);
					} else {
						MosesZombie s = new MosesZombie(loc, 2);
						w.addEntity(s);
					} 
				}
				
				
				
			}
		} else if(com.getWaveNum() >= 8){
			double spawn = Math.random() * 100;
			WorldServer w = ((CraftWorld)e.getPlayer().getWorld()).getHandle();
			
			if(spawn < 1) {
				double magic = Math.random() * 100;
				double randomZomb = Math.random() * 100;
				double randX = Math.random() * 10 - 5;
				double randZ = Math.random() * 10 - 5;
				
				double smartSpawn = Math.random() * 100;

				Location loc = e.getPlayer().getLocation().add(20 + randX, 0, 20 + randZ);
				Block b;
				if(smartSpawn < 50) {
					 b = e.getPlayer().getWorld().getBlockAt(loc);
				} else {
					 b = e.getPlayer().getWorld().getHighestBlockAt(loc);
				}
				loc = b.getLocation().add(0, 1, 0);
				if(magic < 70) {
					
					for(int x = 0; x < (int)(Math.random() * 4) + 2; x++) {
						if(randomZomb < 14) {
							BreakerZombie s = new BreakerZombie(loc, 5);
							w.addEntity(s);
						} else if(randomZomb < 28) {
							CaptureZombie s = new CaptureZombie(loc, 4);
							w.addEntity(s);
						}else if(randomZomb < 42) {
							KamikazeeZombie s = new KamikazeeZombie(loc, 6);
							w.addEntity(s);
						}else if(randomZomb < 56) {
							TeleporterZombie s = new TeleporterZombie(loc, 5);
							w.addEntity(s);
						}else if(randomZomb < 70) {
							ThrowerZombie s = new ThrowerZombie(loc, 4);
							w.addEntity(s);
						}else if(randomZomb < 84) {
							ObsidianZombie s = new ObsidianZombie(loc, 3);
							w.addEntity(s);
						}else {
							if(magic < 40) {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, false, 5);
								w.addEntity(s);
							}
							else {
								RunningJumpingZombie s = new RunningJumpingZombie(loc, true, 5);
								w.addEntity(s);
							}
						}
					}
					
				}
				else {
					for(int x = 0; x < (int)(Math.random() * 2) + 1; x++) {

						if(randomZomb < 33) {
							WizardZombie s = new WizardZombie(loc, 3);
							w.addEntity(s);
						} else if(randomZomb < 66) {
							TheifZombie s = new TheifZombie(loc, 3);
							w.addEntity(s);
						} else {
							MosesZombie s = new MosesZombie(loc, 3);
							w.addEntity(s);
						} 
					}
				}
				
			}
		}
			
	}
	@EventHandler
	public void projectileHit(ProjectileHitEvent e) {
		if(e.getEntityType().equals(EntityType.FIREBALL)) {
        		e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.5F);
        	
        	
        }
		if(e.getEntityType().equals(EntityType.TRIDENT)) {
    		e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 5.0F);
    	
    	
    }
	}
	public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		  
		  return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}
	


}
