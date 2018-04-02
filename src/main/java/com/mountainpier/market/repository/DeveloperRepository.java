package com.mountainpier.market.repository;

import com.mountainpier.market.domain.Developer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DeveloperRepository
		extends JpaRepository<Developer, Integer> {
	
	Developer getDeveloperByNameIgnoreCase(String username);
	
	Developer getDeveloperByEmailIgnoreCase(String regEmail);
	
	Developer getDeveloperByUserUuid(UUID userUuid);
	
	Page<Developer> getDevelopersByNameContainingIgnoreCase(String name, Pageable pageable);
}
