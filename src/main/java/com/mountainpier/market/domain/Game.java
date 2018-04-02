package com.mountainpier.market.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "games", schema = "public")
public class Game {
	
	@Id
	@Column(name = "games_id")
	@GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
	@GenericGenerator(
		name = "uuid2",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID id;
	
	@Column(name = "games_name")
	private String name;
	
	@Column(name = "games_avatar")
	private String avatar;
	
	@Column(name = "games_description", length = 1024)
	private String description;
	
	@Column(name = "games_price")
	private BigDecimal price;
	
	@Column(name = "games_release_date")
	private Date releaseDate;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Developer> developers = new ArrayList<>();

}
