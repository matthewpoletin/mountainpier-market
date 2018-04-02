package com.mountainpier.market.web;

import com.mountainpier.market.domain.Developer;
import com.mountainpier.market.service.DeveloperServiceImpl;
import com.mountainpier.market.web.model.DeveloperRequest;
import com.mountainpier.market.web.model.DeveloperResponse;
import com.mountainpier.market.web.model.GameResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequestMapping(DeveloperController.developerBaseURI)
public class DeveloperController {

	static final String developerBaseURI = "/api/market";

	private final DeveloperServiceImpl developerService;
	
	@Autowired
	DeveloperController(DeveloperServiceImpl developerService) {
		this.developerService = developerService;
	}
	
	@RequestMapping(value = "/developers", method = RequestMethod.GET)
	public Page<DeveloperResponse> getDevelopers(@RequestParam(value = "page", required = false) Integer page,
												 @RequestParam(value = "size", required = false) Integer size,
												 @RequestParam(value = "name", required = false) String name) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		Page<Developer> developerPage;
		if (name != null) developerPage = developerService.getDevelopersWithName(name, page, size);
		else developerPage = developerService.getDevelopers(page, size);
		return developerPage
			.map(DeveloperResponse::new);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/developers", method = RequestMethod.POST)
	public DeveloperResponse createUDeveloper(@RequestBody @Valid DeveloperRequest developerRequest,
											  HttpServletResponse response) {
		Developer developer = developerService.createDeveloper(developerRequest);
		response.addHeader(HttpHeaders.LOCATION, developerBaseURI + "/developers/" + developer.getId());
		return new DeveloperResponse(developer);
	}

	@RequestMapping(value = "/developers/{developerId}", method = RequestMethod.GET)
	public DeveloperResponse getDeveloperById(@PathVariable("developerId") final Integer developerId) {
		return new DeveloperResponse(developerService.getDeveloperById(developerId));
	}

	@RequestMapping(value = "/developers/by", method = RequestMethod.GET)
	public DeveloperResponse getDeveloperBy(@RequestParam(value = "userUuid", required = false) @Size(min = 36, max = 36) final String userUuid,
											@RequestParam(name = "name", required = false) final String name,
											@RequestParam(name = "email", required = false) final String email,
									   		HttpServletResponse response) {
		Developer developer;
		if (userUuid != null && userUuid .length() == 36) {
			developer = developerService.getDeveloperByUserUuid(UUID.fromString(userUuid));
		}
		else if (name != null && name.length() > 0) {
			developer = developerService.getDeveloperByName(name);
		} else if (email != null && email.length() > 0) {
			developer = developerService.getDeveloperByEmail(email);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		if (developer != null) {
			return new DeveloperResponse(developer);
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
	}
	
	@RequestMapping(value = "/developers/{developerId}", method = RequestMethod.PATCH)
	public DeveloperResponse updateDeveloper(@PathVariable("developerId") final Integer developerId,
											 @RequestBody @Valid DeveloperRequest developerRequest) {
		return new DeveloperResponse(developerService.updateDeveloperById(developerId, developerRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/developers/{developerId}", method = RequestMethod.DELETE)
	public void deleteDeveloper(@PathVariable("developerId") final Integer developerId) {
		developerService.deleteDeveloperById(developerId);
	}
	
	@RequestMapping(value = "/developers/{developerId}/games", method = RequestMethod.GET)
	public Page<GameResponse> GetGamesOfDeveloperById(@PathVariable("developerId") final Integer developerId,
													  @RequestParam(value = "page", required = false) Integer page,
													  @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		return developerService.getGamesOfDeveloperById(developerId, page, size)
			.map(GameResponse::new);
	}
	
}
