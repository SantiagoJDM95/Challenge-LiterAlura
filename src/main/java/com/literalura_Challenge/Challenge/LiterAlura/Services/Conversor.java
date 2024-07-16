package com.literalura_Challenge.Challenge.LiterAlura.Services;

public interface Conversor {
    <T> T getData(String json, Class<T> tClass);
}
