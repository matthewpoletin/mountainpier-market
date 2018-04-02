package com.mountainpier.market.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "developers", schema = "public")
public class Developer {
	
	@Id
	@Column(name = "developers_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "developers_user_uuid", unique = true)
	private UUID userUuid;
	
	// TODO: Make unique case-insensitive
	@Column(name = "developers_name", unique = true)
	private String name;
	
	@Column(name = "developers_description")
	private String description;
	
	@Column(name = "developers_website")
	private String website;
	
	@Column(name = "developers_email")
	private String email;
	
	@Column(name = "developers_avatar")
	private String avatar;
	
	@Column(name = "developers_reg_date")
	private Date regDate;
	
	@ManyToMany(mappedBy = "developers")
	private List<Game> games = new ArrayList<>();
	
}
