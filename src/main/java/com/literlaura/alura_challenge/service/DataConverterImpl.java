package com.literlaura.alura_challenge.service;

public interface DataConverterImpl {
    public <T> T convertData(String json, Class<T> tClass);
}
