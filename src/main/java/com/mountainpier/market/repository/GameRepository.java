package com.mountainpier.market.repository;

import com.mountainpier.market.domain.Game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository
	extends JpaRepository<Game, UUID> {
	
	Game getGameById(UUID gameId);
	
	void deleteGameById(UUID gameId);
	
	Page<Game> getGamesByNameContainingIgnoreCase(String name, Pageable pageable);

	Game getGameByName(String name);
	
}
