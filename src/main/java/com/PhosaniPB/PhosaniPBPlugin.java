package com.PhosaniPB;

import com.google.inject.Provides;
import javax.inject.Inject;
import java.lang.String;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.NpcChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "PhosaniPB"
)
public class PhosaniPBPlugin extends Plugin
{
	static final int phase1Start = 9417;
	static final int phase2Start = 9418;
	static final int phase3Start = 11153;
	static final int phase4Start = 9416;
	static final int phase5Start = 11154;

	String gameMessage;
	long startTime;
	long phase1Duration;
	long phase2Duration;
	long phase3Duration;
	long phase4Duration;
	long phase5Duration;
	long phaseTime;
	int searchID;
	int currentPhase = 0;

	int[] phaseIDs = new int[]{9416,9417,9418,11153,11154};
	boolean found = false;

	@Inject
	private Client client;

	@Inject
	private PhosaniPBConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says :) " + config.greeting(), null);
		}
	}

	@Subscribe
	public void onNpcChanged(NpcChanged npcChanged)
	{
		gameMessage = "onNpcChanged debug: ID is " + npcChanged.getNpc().getId();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
		searchID = npcChanged.getNpc().getId();

		for(int x : phaseIDs){
			if( x == searchID){
				found = true;
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "ID Found: " + searchID, null);
				break;
			}
		}

		if(found){
			currentPhase++;
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "current phase is " + currentPhase, null);
			if(currentPhase == 1){
				startTime = System.currentTimeMillis();
				gameMessage = "Phosani's Nightmare: Phase 1 Started ";
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
			}

			if(currentPhase == 2){
				phase1Duration = System.currentTimeMillis();
				phaseTime = (phase1Duration - startTime ) / 1000;
				gameMessage = "Phase 1 Duration: " + phaseTime + " seconds";
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
			}

			if(currentPhase == 3){
				phase2Duration = System.currentTimeMillis();
				phaseTime = (phase2Duration - phase1Duration ) / 1000;
				gameMessage = "Phase 2 Duration: " + phaseTime + " seconds";
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
			}

			if(currentPhase == 4){
				phase3Duration = System.currentTimeMillis();
				phaseTime = (phase3Duration - phase2Duration ) / 1000;
				gameMessage = "Phase 3 Duration: " + phaseTime + " seconds";
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
			}

			if(currentPhase == 5){
				phase4Duration = System.currentTimeMillis();
				phaseTime = (phase4Duration - phase3Duration ) / 1000;
				gameMessage = "Phase 4 Duration: " + phaseTime + " seconds";
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
			}

			if(currentPhase == 6){
				phase5Duration = System.currentTimeMillis();
				phaseTime = (phase5Duration - phase4Duration ) / 1000;
				gameMessage = "Phase 5 Duration: " + phaseTime + " seconds";
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", gameMessage, null);
				currentPhase = 0;
			}
		}
		found = false;
	}

	@Provides
	PhosaniPBConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PhosaniPBConfig.class);
	}
}
