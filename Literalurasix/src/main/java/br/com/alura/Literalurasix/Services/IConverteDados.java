package br.com.alura.Literalurasix.Services;

public interface IConverteDados {
    <T> T getData(String json, Class<T> classe);
}
