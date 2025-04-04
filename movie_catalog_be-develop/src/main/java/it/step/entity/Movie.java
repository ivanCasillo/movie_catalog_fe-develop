package it.step.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1925588687415686678L;

	@Id
	@Column(name = "imdbid")
	private String imdbid;
	@NotNull
	private String title;
	@NotNull
	private String year;

	private String rated;

	private Date released;

	private String runtime;

	@NotNull
	private String plot;

	private String awards;
	@NotNull
	private String poster;

	@Column(name = "votes_number")
	private Integer votesNumber;

	private Date dvd;

	private String website;

	private Integer totalseasons;

	private String boxoffice;

	private double rating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private Type type;

	@ManyToMany
	@JoinTable(
			name = "movies_actors",
			joinColumns = @JoinColumn(name = "id_movie"),
			inverseJoinColumns = @JoinColumn(name = "id_actor")
	)
	private List<Actor> actors;

	@ManyToMany
	@JoinTable(
			name = "movies_country",
			joinColumns = @JoinColumn(name = "id_movie"),
			inverseJoinColumns = @JoinColumn(name = "id_country")
	)
	private List<Country> countries;

	@ManyToMany
	@JoinTable(
			name = "movies_director",
			joinColumns = @JoinColumn(name = "id_movie"),
			inverseJoinColumns = @JoinColumn(name = "id_director")
	)
	private List<Director> directors;

	@ManyToMany
	@JoinTable(
			name = "movies_genre",
			joinColumns = @JoinColumn(name = "id_movie"),
			inverseJoinColumns = @JoinColumn(name = "id_genre")
	)
	private List<Genre> genres;

	@ManyToMany
	@JoinTable(
			name = "movies_language",
			joinColumns = @JoinColumn(name = "id_movies"),
			inverseJoinColumns = @JoinColumn(name = "id_language")
	)
	private List<Language> languages;

	@ManyToMany
	@JoinTable(
			name = "movies_production",
			joinColumns = @JoinColumn(name = "id_movies"),
			inverseJoinColumns = @JoinColumn(name = "id_production")
	)
	private List<Production> productions;

	@ManyToMany
	@JoinTable(
			name = "movies_writer",
			joinColumns = @JoinColumn(name = "id_movies"),
			inverseJoinColumns = @JoinColumn(name = "id_writer")
	)
	private List<Writer> writers;
}
