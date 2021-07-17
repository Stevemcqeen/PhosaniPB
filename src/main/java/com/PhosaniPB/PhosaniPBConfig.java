package com.PhosaniPB;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface PhosaniPBConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "PhosaniPBGreet",
		description = "Phosani PB Plugin active!"
	)
	default String greeting()
	{
		return "Hello";
	}
}
