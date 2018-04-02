package com.mountainpier.market.service;

import com.mountainpier.market.domain.Developer;
import com.mountainpier.market.domain.Game;
import com.mountainpier.market.web.model.DeveloperRequest;

import org.springframework.data.domain.Page;
import java.util.UUID;

public interface DeveloperService {
	Page<Developer> getDevelopers(Integer page, Integer size);
	Page<Developer> getDevelopersWithName(String name, Integer page, Integer size);
	Developer createDeveloper(DeveloperRequest developerRequest);
	Developer getDeveloperById(Integer developerId);
	Developer getDeveloperByUserUuid(UUID uuid);
	Developer getDeveloperByName(String name);
	Developer getDeveloperByEmail(String email);
	Developer updateDeveloperById(Integer developerId, DeveloperRequest developerRequest);
	void deleteDeveloperById(Integer userId);
	
	Page<Game> getGamesOfDeveloperById(Integer developerId, Integer page, Integer size);
}
