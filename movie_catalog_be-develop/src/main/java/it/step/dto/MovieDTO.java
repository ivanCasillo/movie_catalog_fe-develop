package it.step.dto;

import it.step.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String imdbid;
    private String title;
    private String year;
    private String rated;
    private Date released;
    private String runtime;
    private String plot;
    private String awards;
    private String poster;
    private Integer votesNumber;
    private Date dvd;
    private String website;
    private Integer totalseasons;
    private String boxoffice;
    private Type type;
    private double rating;


}
