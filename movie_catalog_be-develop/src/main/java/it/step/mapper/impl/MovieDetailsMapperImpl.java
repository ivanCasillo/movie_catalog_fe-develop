package it.step.mapper.impl;

import it.step.dto.*;
import it.step.entity.*;
import it.step.mapper.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsMapperImpl {

    public static MovieDetailsDTO movieToMovieDetailsDTO(Movie movie){

        MovieDetailsDTO dto = new MovieDetailsDTO(); //dto di appoggio in cui carico tutto
        dto.setMovieDto(Mappers.getMapper(MovieMapper.class).movieToMovieDto(movie));	//setto le informazioni base del movie dopo averlo mappato
        
        List<ActorDTO> actorDTOs = new ArrayList<>();	//mi creo una lista di appoggio per la list
        movie.getActors().forEach(actor -> {
            actorDTOs.add(Mappers.getMapper(ActorMapper.class).actorToActorDto(actor));	//per ogni attore effettuo il mapping
        });
        dto.setActorDTOs(actorDTOs);	//e lo aggiungo alla lista del dto

        List<CountryDTO> countryDTOs  = new ArrayList<>();
        movie.getCountries().forEach(country -> countryDTOs.add(Mappers.getMapper(CountryMapper.class).countryToCountryDto(country)));
        dto.setCountryDTOs(countryDTOs);

        List<DirectrorDTO> directrorDTOs  = new ArrayList<>();
        movie.getDirectors().forEach(director -> directrorDTOs.add(Mappers.getMapper(DirectorMapper.class).directorToDirectorDto(director)));
        dto.setDirectrorDTOs(directrorDTOs);

        List<GenreDTO> genreDTOs  = new ArrayList<>();
        movie.getGenres().forEach(genre -> genreDTOs.add(Mappers.getMapper(GenreMapper.class).genreToGenreDto(genre)));
        dto.setGenreDTOs(genreDTOs);

        List<LanguageDTO> languageDTOs  = new ArrayList<>();
        movie.getLanguages().forEach(language -> languageDTOs.add(Mappers.getMapper(LanguageMapper.class).languageToLanguageDto(language)));
        dto.setLanguageDTOs(languageDTOs);

        List<ProductionDTO> productionDTOs  = new ArrayList<>();
        movie.getProductions().forEach(production -> productionDTOs.add(Mappers.getMapper(ProductionMapper.class).productionToProductionDto(production)));
        dto.setProductionDTOs(productionDTOs);

        List<WriterDTO> writerDTOs  = new ArrayList<>();
        movie.getWriters().forEach(writer -> writerDTOs.add(Mappers.getMapper(WriterMapper.class).writerToWriterDto(writer)));
        dto.setWriterDTOs(writerDTOs);

        return dto;
    }

    public static List<MovieDetailsDTO> movieLstToMovieDetailsDTOLst (List<Movie> movieLst){

        List<MovieDetailsDTO> dtoLst = new ArrayList<>();
        movieLst.forEach(movie -> dtoLst.add(MovieDetailsMapperImpl.movieToMovieDetailsDTO(movie)));
        return dtoLst;

    }

    public static Movie movieDetailsDTOToMovie(MovieDetailsDTO dto) {

        Movie movie = Mappers.getMapper(MovieMapper.class).movieDtoToMovie(dto.getMovieDto());

        List<Actor> actors = new ArrayList<>();
        dto.getActorDTOs().forEach(actorDTO -> actors.add(Mappers.getMapper(ActorMapper.class).actorDtoToActor(actorDTO)));
        movie.setActors(actors);

        List<Country> countries = new ArrayList<>();
        dto.getCountryDTOs().forEach(countryDTO -> countries.add(Mappers.getMapper(CountryMapper.class).countryDtoToCountry(countryDTO)));
        movie.setCountries(countries);

        List<Director> directors = new ArrayList<>();
        dto.getDirectrorDTOs().forEach(directorDTO -> directors.add(Mappers.getMapper(DirectorMapper.class).directorDtoToDirector(directorDTO)));
        movie.setDirectors(directors);

        List<Genre> genres = new ArrayList<>();
        dto.getGenreDTOs().forEach(genreDTO -> genres.add(Mappers.getMapper(GenreMapper.class).genreDtoToGenre(genreDTO)));
        movie.setGenres(genres);

        List<Language> languages = new ArrayList<>();
        dto.getLanguageDTOs().forEach(languageDTO -> languages.add(Mappers.getMapper(LanguageMapper.class).languageDtoToLanguage(languageDTO)));
        movie.setLanguages(languages);

        List<Production> productions  = new ArrayList<>();
        dto.getProductionDTOs().forEach(productionDTO -> productions.add(Mappers.getMapper(ProductionMapper.class).productionDtoToProduction(productionDTO)));
        movie.setProductions(productions);

        List<Writer> writers  = new ArrayList<>();
        dto.getWriterDTOs().forEach(writerDTO -> writers.add(Mappers.getMapper(WriterMapper.class).writerDtoToWriter(writerDTO)));
        movie.setWriters(writers);

        return movie;
    }
}
