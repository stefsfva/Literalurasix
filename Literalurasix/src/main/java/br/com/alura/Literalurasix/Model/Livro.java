package br.com.alura.Literalurasix.Model;

import jakarta.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String idioma;
    private Integer quantidadeDeDownloads;

    @ManyToOne
    private Autores autor;

    // Construtor padrão
    public Livro() {}

    // Construtor com DadosLivro
    public Livro(DadosLivros dadosLivro) {
        this.nome = dadosLivro.nomeDoLivro();
        this.idioma = String.join(", ", dadosLivro.idiomas()); // Converte a lista em uma string
        this.quantidadeDeDownloads = dadosLivro.quantidadeDeDownloads();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getQuantidadeDeDownloads() {
        return quantidadeDeDownloads;
    }

    public void setQuantidadeDeDownloads(Integer quantidadeDeDownloads) {
        this.quantidadeDeDownloads = quantidadeDeDownloads;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return new StringJoiner("\n---------------------------------------\n")
                .add("Nome: " + nome)
                .add("Idioma: " + idioma)
                .add("Autor: " + (autor != null ? autor.getNome() : "N/A"))
                .add("Quantidade De Downloads: " + quantidadeDeDownloads)
                .toString();
    }
}
