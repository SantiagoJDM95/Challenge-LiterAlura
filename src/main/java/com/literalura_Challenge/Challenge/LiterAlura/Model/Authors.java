package com.literalura_Challenge.Challenge.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Authors(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear
) {
}
