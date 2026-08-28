# Changelog

Todas as alterações relevantes deste projeto serão documentadas neste arquivo.

O formato é baseado no [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/), e o versionamento segue o [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [Não publicado]

Alterações em desenvolvimento para a próxima versão da aplicação.

## [0.2.0] — 2026-08-28

**Marco acadêmico:** `etapa-2`

**Competência:** Estruturas de Dados e Camada de Serviço

### Adicionado

* Interface genérica `CrudService<T, ID>` com operações CRUD.
* Classe `PeritoService` para gerenciamento dos peritos em memória.
* Classe `NomeacaoPericialService` para gerenciamento das nomeações.
* Classe `AtividadePericialService` para gerenciamento das atividades.
* Estruturas `LinkedHashMap` utilizando o identificador como chave e o
  objeto correspondente como valor.
* Operações para incluir, alterar, excluir, obter por identificador e listar
  objetos.
* Exceção `DadosInvalidosException`.
* Exceção `EntidadeJaExistenteException`.
* Exceção `EntidadeNaoEncontradaException`.
* Consulta de nomeações por status utilizando `filter`.
* Ordenação de nomeações por prazo utilizando `sorted`.
* Busca de nomeação pelo número do processo utilizando `findFirst`.
* Transformação das nomeações em números processuais utilizando `map`.
* Demonstração das consultas com Streams no console.
* Testes unitários do serviço de nomeações.
* Teste integrado da leitura dos arquivos e carga nos Services.

### Alterado

* Loaders integrados à camada de serviço.
* Dados lidos dos arquivos-texto armazenados nos Maps.
* Busca de peritos e nomeações realizada pelos respectivos Services.
* Relacionamentos um-para-muitos preservados durante a carga dos dados.
* Inicializador atualizado para demonstrar a arquitetura da Etapa 2.
* Saída do console atualizada com consultas baseadas em Streams.
* Documentação atualizada para a Etapa 2.
* Versão do projeto atualizada para `0.2.0`.

### Validado

* Armazenamento de um perito, duas nomeações e quatro atividades nos Maps.
* Relacionamento entre o perito e suas duas nomeações.
* Relacionamento entre as nomeações e suas respectivas atividades.
* Operações de inclusão, alteração, exclusão, consulta e listagem.
* Tratamento de identificador duplicado.
* Tratamento de entidade inexistente.
* Filtragem, ordenação, busca e transformação de coleções.
* Leitura dos três arquivos-texto.
* Execução de seis testes com zero falhas e zero erros.
* Compilação finalizada com `BUILD SUCCESS`.

## [0.1.0] — 2026-08-27

**Marco acadêmico:** `etapa-1`

**Competência:** Orientação a Objetos Avançada

### Adicionado

* Projeto Java com Spring Boot e Maven.
* Modelo de negócio para gerenciamento de nomeações periciais.
* Classe abstrata `Pessoa`.
* Classe `Perito`, utilizando herança por meio de `extends Pessoa`.
* Classe `NomeacaoPericial` para representar uma nomeação recebida pelo perito.
* Classe `AtividadePericial` para representar tarefas e prazos vinculados à nomeação.
* Classe `HonorariosPericiais` para controle dos valores propostos, fixados, depositados e recebidos.
* Enum `StatusNomeacao` para representar os possíveis estados de uma nomeação.
* Relacionamento um-para-muitos entre `Perito` e `NomeacaoPericial`.
* Relacionamento um-para-muitos entre `NomeacaoPericial` e `AtividadePericial`.
* Relacionamento entre `NomeacaoPericial` e `HonorariosPericiais`.
* Atributos dos tipos `String`, `int`, `double`, `boolean`, `LocalDate` e `BigDecimal`.
* Comportamentos para aceitar, recusar e alterar o status das nomeações.
* Comportamentos para concluir, reabrir e verificar o atraso das atividades.
* Comportamentos para registrar a fixação, o depósito e o recebimento dos honorários.
* Implementação do método `toString()` nas classes do modelo.
* Arquivo `peritos.txt` com dados fictícios para carga inicial.
* Arquivo `nomeacoes.txt` com dados fictícios e relacionamento com o perito.
* Arquivo `atividades.txt` com dados fictícios e relacionamento com as nomeações.
* Classe `PeritoLoader` para leitura do arquivo de peritos e criação dos objetos.
* Classe `NomeacaoLoader` para leitura das nomeações e associação ao respectivo perito.
* Classe `AtividadeLoader` para leitura das atividades e associação às respectivas nomeações.
* Classe `InicializadorAplicacao`, utilizando `CommandLineRunner`.
* Rotina de inicialização para leitura dos arquivos, criação dos objetos e estabelecimento dos relacionamentos.
* Apresentação das informações dos objetos no console.
* Resumo da quantidade de peritos, nomeações e atividades carregadas.
* Teste de inicialização do contexto do Spring Boot.
* Documentação da Etapa 1 no arquivo `README.md`.
* Diagrama de classes em Mermaid.
* Instruções para teste e execução no Linux e WSL.
* Documentação dos limites da Etapa 1 e da evolução planejada para as etapas seguintes.

### Alterado

* Versão do projeto definida como `0.1.0`, seguindo Semantic Versioning.
* Permissão de execução do Maven Wrapper para permitir o uso de `./mvnw`.
* Configuração do projeto com suporte ao Spring Boot DevTools.
* Classe principal da aplicação com configuração de Logger.
* Finais de linha dos arquivos normalizados para o padrão Linux.

### Removido

* Arquivo `mvnw.cmd:Zone.Identifier`, criado pelo Windows e sem utilidade para o projeto.

### Validado

* Inicialização da aplicação com Spring Boot.
* Leitura dos três arquivos-texto.
* Carregamento de um perito fictício.
* Associação de duas nomeações ao perito.
* Associação de quatro atividades às respectivas nomeações.
* Representação textual dos objetos no console.
* Relacionamentos um-para-muitos em memória.
* Herança entre `Perito` e `Pessoa`.
* Execução dos testes com zero falhas e zero erros.
* Compilação finalizada com `BUILD SUCCESS`.

[Não publicado]: https://github.com/andregasparinfnet/andre-gaspar-api/compare/etapa-2...HEAD
[0.2.0]: https://github.com/andregasparinfnet/andre-gaspar-api/tree/etapa-2
[0.1.0]: https://github.com/andregasparinfnet/andre-gaspar-api/tree/etapa-1
