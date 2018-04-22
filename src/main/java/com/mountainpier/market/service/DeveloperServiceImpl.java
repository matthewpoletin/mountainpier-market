package com.mountainpier.market.service;

import com.mountainpier.market.domain.Developer;
import com.mountainpier.market.domain.Game;
import com.mountainpier.market.repository.DeveloperRepository;
import com.mountainpier.market.web.model.DeveloperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class DeveloperServiceImpl implements DeveloperService {
	
	private final DeveloperRepository developerRepository;
	
	@Autowired
	public DeveloperServiceImpl(DeveloperRepository developerRepository) {
		this.developerRepository = developerRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Developer> getDevelopers(Integer page, Integer size) {
		return developerRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Developer> getDevelopersWithName(String name, Integer page, Integer size) {
		return developerRepository.getDevelopersByNameContainingIgnoreCase(name, PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Developer createDeveloper(DeveloperRequest developerRequest) {
		Developer developer = new Developer()
				.setUserUuid(UUID.fromString(developerRequest.getUserUuid()))
				.setName(developerRequest.getName())
				.setDescription(developerRequest.getDescription())
				.setWebsite(developerRequest.getWebsite())
				.setAvatar(developerRequest.getAvatar())
				.setEmail(developerRequest.getEmail())
				.setRegDate(developerRequest.getRegDate());
		return developerRepository.save(developer);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Developer getDeveloperById(Integer developerId) {
		return developerRepository.findById(developerId)
				.orElseThrow(() -> new EntityNotFoundException("Developer '{" + developerId + "}' not found"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Developer getDeveloperByUserUuid(UUID userUuid) {
		return developerRepository.getDeveloperByUserUuid(userUuid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Developer getDeveloperByName(String name) {
		return developerRepository.getDeveloperByNameIgnoreCase(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Developer getDeveloperByEmail(String email) {
		return developerRepository.getDeveloperByEmailIgnoreCase(email);
	}
	
	@Override
	@Transactional
	public Developer updateDeveloperById(Integer developerId, DeveloperRequest developerRequest) {
		Developer developer = this.getDeveloperById(developerId);
		developer.setName(developerRequest.getName() != null ? developerRequest.getName() : developer.getName());
		developer.setDescription(developerRequest.getDescription() != null ? developerRequest.getDescription() : developer.getDescription());
		developer.setWebsite(developerRequest.getWebsite() != null ? developerRequest.getWebsite() : developer.getWebsite());
		developer.setAvatar(developerRequest.getAvatar() != null ? developerRequest.getAvatar() : developer.getAvatar());
		developer.setEmail(developerRequest.getEmail() != null ? developerRequest.getEmail() : developer.getEmail());
		developer.setRegDate(developerRequest.getRegDate() != null ? developerRequest.getRegDate() : developer.getRegDate());
		return developerRepository.save(developer);
	}
	
	@Override
	@Transactional
	public void deleteDeveloperById(Integer developerId) {
		developerRepository.deleteById(developerId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Game> getGamesOfDeveloperById(Integer developerId, Integer page, Integer size) {
		Developer developer = this.getDeveloperById(developerId);
		List<Game> games = developer.getGames();
		return new PageImpl<>(games, PageRequest.of(page, size), games.size());
	}
	
}
