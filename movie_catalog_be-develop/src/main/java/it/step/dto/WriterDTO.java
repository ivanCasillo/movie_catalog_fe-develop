package it.step.dto;

import it.step.entity.Movie;
import it.step.entity.Writer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriterDTO {

    private Integer idWriter;
    private String first_name;
    private String last_name;

}
