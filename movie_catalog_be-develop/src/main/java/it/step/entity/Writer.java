package it.step.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "writer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Writer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_writer")
    private Integer idWriter;

    private String first_name;

    private String last_name;

    @ManyToMany(mappedBy = "writers")
    @JsonIgnore
    private List<Movie> movies;

}
