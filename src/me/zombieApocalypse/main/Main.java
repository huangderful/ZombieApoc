package me.zombieApocalypse.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.zombieApocalypse.commands.Commands;
import me.zombieApocalypse.listeners.Listeners;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable(){
		Commands c = new Commands(this);
		getServer().getPluginManager().registerEvents(new Listeners(c), this);

	}
}
