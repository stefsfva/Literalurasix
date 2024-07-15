package br.com.alura.Literalurasix.Repository;

import br.com.alura.Literalurasix.Model.Livro; // Importe a classe Livro correta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> { // Corrigido para usar a classe Livro correta
    boolean existsByNome(String nome);

    @Query("SELECT DISTINCT l.idioma FROM Livro l ORDER BY l.idioma")
    List<String> bucasidiomas();

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idiomaSelecionado")
    List<Livro> buscarPorIdioma(String idiomaSelecionado); // Corrigido para usar a classe Livro correta

    List<Livro> findTop10ByOrderByQuantidadeDeDownloadsDesc(); // Corrigido para usar a classe Livro correta

    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE a.nome ILIKE %:pesquisa%")
    List<Livro> encontrarLivrosPorAutor(String pesquisa); // Corrigido para usar a classe Livro correta
}
