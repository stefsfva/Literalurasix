package br.com.alura.Literalurasix.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("title")String nomeDoLivro,
                          @JsonAlias("download_count") Integer quantidadeDeDownloads,
                          @JsonAlias("languages") List<String> idiomas) {

}