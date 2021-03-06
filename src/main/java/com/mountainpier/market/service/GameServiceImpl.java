package com.mountainpier.market.service;

import com.mountainpier.market.domain.Developer;
import com.mountainpier.market.domain.Game;
import com.mountainpier.market.repository.GameRepository;
import com.mountainpier.market.web.model.GameRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
	
	private final GameRepository gameRepository;
	
	private final DeveloperService developerService;
	
	@Autowired
	public GameServiceImpl(GameRepository gameRepository,
						   DeveloperService developerService) {
		this.gameRepository = gameRepository;
		this.developerService = developerService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Game> getGames(Integer page, Integer size) {
		return this.gameRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Game> getGamesWithName(String name, Integer page, Integer size) {
		return this.gameRepository.getGamesByNameContainingIgnoreCase(name, PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Game createGame(GameRequest gameRequest) {
		Game game = new Game()
			.setName(gameRequest.getName())
			.setDescription(gameRequest.getDescription())
			.setAvatar(gameRequest.getAvatar())
			.setReleaseDate(gameRequest.getReleaseDate())
			.setPrice(gameRequest.getPrice());
		return this.gameRepository.save(game);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Game getGameById(UUID gameId) {
		return this.gameRepository.getGameById(gameId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Game getGameByName(String name) {
		return this.gameRepository.getGameByName(name);
	}
	
	@Override
	public Game updateGameById(UUID gameId, GameRequest gameRequest) {
		Game game = this.getGameById(gameId);
		game.setName(gameRequest.getName() != null ? gameRequest.getName() : gameRequest.getName());
		game.setDescription(gameRequest.getDescription() != null ? gameRequest.getDescription() : gameRequest.getDescription());
		game.setAvatar(gameRequest.getAvatar() != null ? gameRequest.getAvatar() : gameRequest.getAvatar());
		game.setReleaseDate(gameRequest.getReleaseDate() != null ? gameRequest.getReleaseDate() : gameRequest.getReleaseDate());
		game.setPrice(gameRequest.getPrice() != null ? gameRequest.getPrice() : gameRequest.getPrice());
		return this.gameRepository.save(game);
	}
	
	@Override
	@Transactional
	public void deleteGameById(UUID gameId) {
		this.gameRepository.deleteGameById(gameId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Developer> getDevelopersOfGameById(UUID gameId) {
		Game game = this.getGameById(gameId);
		return game.getDevelopers();
	}
	
	@Override
	@Transactional
	public Game setDeveloper(UUID gameId, Integer developerId) {
		Game game = this.getGameById(gameId);
		Developer developer = developerService.getDeveloperById(developerId);
		game.getDevelopers().add(developer);
		return this.gameRepository.save(game);
	}
	
}
