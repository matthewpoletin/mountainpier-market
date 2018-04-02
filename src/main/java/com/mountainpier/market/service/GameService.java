package com.mountainpier.market.service;

import com.mountainpier.market.domain.Developer;
import com.mountainpier.market.domain.Game;
import com.mountainpier.market.web.model.GameRequest;

import org.springframework.data.domain.Page;
import java.util.List;

public interface GameService {
	Page<Game> getGames(Integer page, Integer size);
	Page<Game> getGamesWithName(String name, Integer page, Integer size);
	Game createGame(GameRequest gameRequest);
	Game getGameById(Integer gameId);
	Game getGameByName(String name);
	Game updateGameById(Integer gameId, GameRequest gameRequest);
	void deleteGameById(Integer gameId);
	
	List<Developer> getDevelopersOfGameById(Integer gameId);
}
