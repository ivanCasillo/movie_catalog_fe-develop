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
@Table(name = "language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLanguage;

    private String language;

    @ManyToMany(mappedBy = "languages")
    @JsonIgnore
    private List<Movie> movies;
}
