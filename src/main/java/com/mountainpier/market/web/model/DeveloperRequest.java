package com.mountainpier.market.web.model;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

@Data
@Accessors(chain = true)
public class DeveloperRequest {
	
	private String userId;
	
	private String name;
	
	private String description;
	
	private String website;
	
	private String email;
	
	private String avatar;
	
	private Date regDate;
	
}
