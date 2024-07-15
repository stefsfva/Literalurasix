package br.com.alura.Literalurasix.Repository;

import br.com.alura.Literalurasix.Model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    Boolean existsByNome(String nome);

    Autores findByNome(String nome);

    @Query("SELECT a FROM Autores a WHERE a.dataDeFalecimento >= :anoSelecionado AND :anoSelecionado >= a.dataDeNascimento")
    List<Autores> buscarPorAnoDeFalecimento(int anoSelecionado);

    @Query("SELECT a FROM Autores a WHERE a.nome ILIKE %:pesquisa%")
    List<Autores> encontrarPorNome(String pesquisa);
}
