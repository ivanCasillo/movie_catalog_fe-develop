package it.step.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Integer idGenre;

    private String genre;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<Movie> movies;

}
