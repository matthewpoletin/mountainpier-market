package com.mountainpier.market.web.model;

import com.mountainpier.market.domain.Developer;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class DeveloperResponse {
	private Integer id;
	private String userId;
	private String name;
	private String description;
	private String website;
	private String email;
	private String avatar;
	private Date regDate;

	public DeveloperResponse(Developer developer) {
		this.id = developer.getId();
		this.userId = developer.getUserId().toString();
		this.name = developer.getName();
		this.description = developer.getDescription();
		this.website = developer.getWebsite();
		this.email = developer.getEmail();
		this.avatar = developer.getAvatar();
		this.regDate = developer.getRegDate();
	}
	
}
