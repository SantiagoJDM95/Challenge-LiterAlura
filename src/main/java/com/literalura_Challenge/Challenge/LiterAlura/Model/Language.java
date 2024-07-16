package com.literalura_Challenge.Challenge.LiterAlura.Model;


public enum Language {
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    ITALIAN("it"),
    PORTUGUESE("pt");

    private String languagesAlura;

    Language ( String languagesAlura ) {
        this.languagesAlura = languagesAlura;
    }

    public static Language fromString(String text) {
        for (Language categoria : Language.values())
            if ( categoria.languagesAlura.equalsIgnoreCase(text) ) {
                return categoria;
            }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

}
