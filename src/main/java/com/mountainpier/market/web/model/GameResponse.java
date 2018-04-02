package com.mountainpier.market.web.model;

import com.mountainpier.market.domain.Game;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class GameResponse {
	
	private String id;
	private String name;
	private String description;
	private String avatar;
	private Date releaseDate;
	private BigDecimal price;
	
	public GameResponse(Game game) {
		this.id = game.getId().toString();
		this.name = game.getName();
		this.description = game.getDescription();
		this.avatar = game.getAvatar();
		this.releaseDate = game.getReleaseDate();
		this.price = game.getPrice();
	}
	
}
