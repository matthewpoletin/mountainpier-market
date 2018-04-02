package com.mountainpier.market.web.model;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class GameRequest {
	
	private String name;
	
	private String avatar;
	
	private String description;
	
	private BigDecimal price;
	
	private Date releaseDate;
	
}
