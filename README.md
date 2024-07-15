# Literalurasix

## Descrição
Literalurasix é um projeto (proposto como desafio pelo Alura) de gerenciamento de livros utilizando Spring Boot e PostgreSQL. Este projeto permite buscar, salvar e gerenciar livros e autores, fornecendo uma interface de linha de comando para interação com o usuário.

## Instalação

### Pré-requisitos
- Java 22
- Maven
- PostgreSQL

### Passos para instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/Literalurasix.git
    cd Literalurasix
    ```

2. Configure as variáveis de ambiente no seu sistema, no arquivo aplication properties ou nas variáveis do ambiente do seu sistema operacional, subistitua os DBs pelos suas próprias credenciais:
    ```bash
    export DB_HOST=seu_host
    export DB_NAME=seu_banco
    export DB_USER=seu_usuario
    export DB_KEY=sua_senha
    ```

3. Execute o projeto:
    ```bash
    mvn spring-boot:run
    ```

## Uso
Após a instalação e execução do projeto, você pode interagir com o sistema através da linha de comando. Aqui estão alguns comandos úteis:
- Pesquisar livros por título
- Listar livros mais baixados
- Buscar autores por nome

## Contribuição
Sinta-se à vontade para contribuir com este projeto. Você pode abrir issues e pull requests. Para maiores informações, consulte o arquivo CONTRIBUTING.md.

## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Contato
Para mais informações ou dúvidas, entre em contato através de (stephanne.fva@gmail.com)
