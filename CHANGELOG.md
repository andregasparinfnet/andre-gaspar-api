# Changelog

Todas as alterações relevantes deste projeto serão documentadas neste arquivo.

O formato é baseado no [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/), e o versionamento segue o [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [Não publicado]

Alterações em desenvolvimento para a próxima versão da aplicação.

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

[Não publicado]: https://github.com/andregasparinfnet/andre-gaspar-api/compare/etapa-1...HEAD
[0.1.0]: https://github.com/andregasparinfnet/andre-gaspar-api/tree/etapa-1
