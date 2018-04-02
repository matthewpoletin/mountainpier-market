package com.mountainpier.market.web;

import com.mountainpier.market.domain.Game;
import com.mountainpier.market.service.GameServiceImpl;
import com.mountainpier.market.web.model.DeveloperResponse;
import com.mountainpier.market.web.model.GameRequest;
import com.mountainpier.market.web.model.GameResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(GameController.gameBaseURI)
public class GameController {

	static final String gameBaseURI = "/api/market";

	private final GameServiceImpl gameService;

	@Autowired
	GameController(GameServiceImpl gameService) {
		this.gameService = gameService;
	}

	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public Page<GameResponse> getUsers(@RequestParam(value = "page", required = false) Integer page,
									   @RequestParam(value = "size", required = false) Integer size,
									   @RequestParam(value = "name", required = false) String name) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		Page<Game> gamePage;
		if (name != null) gamePage = gameService.getGamesWithName(name, page, size);
		else gamePage = gameService.getGames(page, size);
		return gamePage
			.map(GameResponse::new);
	}
	
	@RequestMapping(value = "/games", method = RequestMethod.POST)
	public GameResponse createGame(@RequestBody @Valid final GameRequest gameRequest,
								   HttpServletResponse response) {
		Game game = this.gameService.createGame(gameRequest);
		response.addHeader(HttpHeaders.LOCATION, gameBaseURI + "/games/" + game.getId());
		return new GameResponse(game);
	}
	
	@RequestMapping(value = "/games/{gameId}", method = RequestMethod.GET)
	public GameResponse getGameById(@PathVariable(name = "gameId") final Integer gameId) {
		return new GameResponse(this.gameService.getGameById(gameId));
	}
	
	@RequestMapping(value = "/games/{gameId}", method = RequestMethod.PATCH)
	public GameResponse updateGameById(@PathVariable(name = "gameId") final Integer gameId,
									   @RequestBody @Valid GameRequest gameRequest) {
		return new GameResponse(this.gameService.updateGameById(gameId, gameRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
	public void deleteGameById(@PathVariable(name = "gameId") final Integer gameId) {
		this.gameService.deleteGameById(gameId);
	}
	
	@RequestMapping(value = "/games/{gameId}/developers", method = RequestMethod.GET)
	public List<DeveloperResponse> getDevelopersOfGame(@PathVariable(name = "gameId") final Integer gameId) {
		return this.gameService.getDevelopersOfGameById(gameId)
			.stream()
			.map(DeveloperResponse::new)
			.collect(Collectors.toList());
	}
	
}
