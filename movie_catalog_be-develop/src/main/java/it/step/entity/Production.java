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
@Table(name = "production")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Production implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_production")
    private Integer idProduction;

    private String name;

    @ManyToMany(mappedBy = "productions")
    @JsonIgnore
    private List<Movie> movies;

}
