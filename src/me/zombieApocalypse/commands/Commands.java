package me.zombieApocalypse.commands;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

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
import me.zombieApocalypse.main.Main;
import net.minecraft.server.v1_16_R3.WorldServer;


public class Commands implements CommandExecutor{
	Main plugin;
	private int waveNum;

	private boolean waveStopped;
	public Commands(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("startWave").setExecutor(this);
		plugin.getCommand("waveStop").setExecutor(this);
		plugin.getCommand("zombCustom").setExecutor(this);
		plugin.getCommand("noAI").setExecutor(this);


		this.waveNum = 0;
		this.waveStopped = false;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Player player = (Player)sender; 

		switch(cmd.getName()){
			case "startWave":
				waveNum = Integer.parseInt(args[0]);
				Timer t = new Timer();
				TimerTask myTask = new TimerTask() {

					@Override
					public void run() {
						if(!waveStopped) {
							waveNum++;
							Bukkit.broadcastMessage(ChatColor.GRAY + "Waves set to " + waveNum);
						}
					}
					
				};
				
				t.schedule(myTask, 0, 300000);
				    
				
			break;
			case "waveStop":
				waveStopped = !waveStopped;
				Bukkit.broadcastMessage(ChatColor.GRAY + "isWavesStopped set to " + waveStopped);
				if(!waveStopped) {
					Bukkit.dispatchCommand(sender, "startWave " + waveNum);
				}
			break;
			case "zombCustom":
				WorldServer w = ((CraftWorld)player.getWorld()).getHandle();
				int level = Integer.parseInt(args[1]);
				switch(args[0]) {
					case "breaker":
						BreakerZombie s = new BreakerZombie(player.getLocation(), level);
						w.addEntity(s);
					break;
					case "capture":
						CaptureZombie s1 = new CaptureZombie(player.getLocation(), level);
						w.addEntity(s1);
					break;
					case "kamikazee":
						KamikazeeZombie s2 = new KamikazeeZombie(player.getLocation(), level);
						w.addEntity(s2);
						break;
					case "moses":
						MosesZombie s3 = new MosesZombie(player.getLocation(), level);
						w.addEntity(s3);
						break;
					case "runner":
						boolean jump = Boolean.parseBoolean(args[2]);
						RunningJumpingZombie s4 = new RunningJumpingZombie(player.getLocation(), jump, level);
						w.addEntity(s4);
						break;
					case "tp":
						TeleporterZombie s5 = new TeleporterZombie(player.getLocation(), level);
						w.addEntity(s5);
						break;
					case "theif":
						TheifZombie s6 = new TheifZombie(player.getLocation(), level);
						w.addEntity(s6);
						break;
					case "thrower":
						ThrowerZombie s7 = new ThrowerZombie(player.getLocation(), level);
						w.addEntity(s7);
						break;
					case "wiz":
						WizardZombie s8 = new WizardZombie(player.getLocation(), level);
						w.addEntity(s8);
					break;
					case "obsidian":
						ObsidianZombie s9 = new ObsidianZombie(player.getLocation(), level);
						w.addEntity(s9);
					break;
						
				}
			break;
			case "noAI":
				List<Entity> e = player.getWorld().getEntities();
				for(Entity entity : e) {
					if((((CraftEntity)entity).getHandle() instanceof CustomZombie)){
						((CustomZombie)entity).setAI();
					}
				}
			break;
			
		}
		return false;
	}
	public int getWaveNum() {
		return waveNum;
	}
	public boolean isWaveStopped() {
		return waveStopped;
	}
	



}
