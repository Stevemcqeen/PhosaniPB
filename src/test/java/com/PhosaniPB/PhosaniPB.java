package com.PhosaniPB;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PhosaniPB
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PhosaniPBPlugin.class);
		RuneLite.main(args);
	}
}