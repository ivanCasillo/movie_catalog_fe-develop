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
@Table(name = "director")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Director implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDirector;

    private String first_name;

    private String last_name;

    @ManyToMany(mappedBy = "directors")
    @JsonIgnore
    private List<Movie> movies;

}
