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
@Table(name = "actors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
    public class Actor {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_a")
        private Integer idActor;

        private String first_name;

        private String last_name;

        @JsonIgnore
        @ManyToMany(mappedBy = "actors")
        private List<Movie> movies;

    }
