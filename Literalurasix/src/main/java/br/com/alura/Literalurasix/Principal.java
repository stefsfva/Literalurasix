package br.com.alura.Literalurasix;

import br.com.alura.Literalurasix.Model.Autores;
import br.com.alura.Literalurasix.Model.DadosAutores;
import br.com.alura.Literalurasix.Model.DadosLivros;
import br.com.alura.Literalurasix.Model.Livro;
import br.com.alura.Literalurasix.Repository.AutoresRepository;
import br.com.alura.Literalurasix.Repository.LivroRepository;
import br.com.alura.Literalurasix.Services.ComsumoAPI;
import br.com.alura.Literalurasix.Services.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ComsumoAPI api = new ComsumoAPI();
    private AutoresRepository autorRepo;
    private LivroRepository livroRepo;
    private List<Livro> livros = new ArrayList<>();
    private ConverteDados converter = new ConverteDados();
    private static final String BASE_URL = "https://gutendex.com/books?search=";

    public Principal(AutoresRepository autorRepo, LivroRepository livroRepo) {
        this.autorRepo = autorRepo;
        this.livroRepo = livroRepo;
    }

    public void exibirMenu() {
        String menu = """
                **********************************************
                1 - Buscar livro pelo titulo
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
                6 - Top 10 livros
                7 - Buscar autores por nome
                8 - Media de downloads por autor
                
                0 - Sair
                **********************************************
                """;
        int opcao;
        do {
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarNovoLivro();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> listarTop10Livros();
                case 7 -> buscarAutorPorNome();
                case 8 -> calcularMediaDownloadsPorAutor();
                case 0 -> System.out.println("Sair");
                default -> System.out.println("\n*** Opção Inválida ***\n");
            }
        } while (opcao != 0);
    }

    private void buscarNovoLivro() {
        System.out.println("\nQual livro deseja buscar?");
        String busca = scanner.nextLine();
        String dados = api.consumo(BASE_URL + busca.replace(" ", "%20"));
        salvarDados(dados);
        System.out.println(dados);
    }

    private void salvarDados(String dados) {
        try {
            DadosLivros dadosLivro = converter.getData(dados, DadosLivros.class);
            DadosAutores dadosAutor = converter.getData(dados, DadosAutores.class);

            Autores autor = new Autores(dadosAutor);
            if (!autorRepo.existsByNome(autor.getNome())) {
                autor = autorRepo.save(autor);
            } else {
                autor = autorRepo.findByNome(autor.getNome());
            }

            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);
            if (!livroRepo.existsByNome(livro.getNome())) {
                livroRepo.save(livro);
            }

            System.out.println("Livro salvo: " + livro);
        } catch (Exception e) {
            System.out.println("\n\n*** Erro ao salvar o livro ***\n\n");
            e.printStackTrace();
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livrosBd = livroRepo.findAll();
        if (!livrosBd.isEmpty()) {
            System.out.println("\nLivros cadastrados no banco de dados:");
            livrosBd.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum livro encontrado no banco de dados!");
        }
    }

    private void listarAutoresRegistrados() {
        List<Autores> autoresBd = autorRepo.findAll();
        if (!autoresBd.isEmpty()) {
            System.out.println("\nAutores cadastrados no banco de dados:");
            autoresBd.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum autor encontrado no banco de dados!");
        }
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("\nQual ano deseja pesquisar?");
        int ano = scanner.nextInt();
        scanner.nextLine();
        List<Autores> autores = autorRepo.buscarPorAnoDeFalecimento(ano);
        if (!autores.isEmpty()) {
            System.out.println("\n\nAutores vivos no ano de: " + ano);
            autores.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum autor encontrado para esta data!");
        }
    }

    private void listarLivrosPorIdioma() {
        List<String> idiomas = livroRepo.bucasidiomas();
        System.out.println("\nIdiomas disponíveis:");
        idiomas.forEach(System.out::println);
        System.out.println("\nSelecione um dos idiomas:");
        String idioma = scanner.nextLine();
        livroRepo.buscarPorIdioma(idioma).forEach(System.out::println);
    }

    private void listarTop10Livros() {
        List<Livro> top10 = livroRepo.findTop10ByOrderByQuantidadeDeDownloadsDesc();
        top10.forEach(System.out::println);
    }

    private void buscarAutorPorNome() {
        System.out.println("Qual o nome do autor?");
        String nome = scanner.nextLine();
        List<Autores> autores = autorRepo.encontrarPorNome(nome);
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("*** Autor não encontrado! ***");
        }
    }

    private void calcularMediaDownloadsPorAutor() {
        System.out.println("Qual autor deseja buscar?");
        String nome = scanner.nextLine();
        List<Livro> livrosAutor = livroRepo.encontrarLivrosPorAutor(nome);
        DoubleSummaryStatistics stats = livrosAutor.stream()
                .collect(Collectors.summarizingDouble(Livro::getQuantidadeDeDownloads));
        System.out.println("Média de Downloads: " + stats.getAverage());
    }
}
