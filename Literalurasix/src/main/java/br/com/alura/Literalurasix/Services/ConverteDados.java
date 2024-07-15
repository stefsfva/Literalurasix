package br.com.alura.Literalurasix.Services;

import br.com.alura.Literalurasix.Model.DadosAutores;
import br.com.alura.Literalurasix.Model.DadosLivros;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> classe) {
        try {
            JsonNode node = mapper.readTree(json);
            if (classe == DadosLivros.class) {
                var s = node.get("results").get(0);
                return mapper.treeToValue(s, classe);
            } else if (classe == DadosAutores.class) {
                var s = node.get("results").get(0).get("authors").get(0);
                return mapper.treeToValue(s, classe);
            } else {
                return mapper.readValue(json, classe);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
