package br.edu.infnet.andre_gaspar_api.bootstrap;
import br.edu.infnet.andre_gaspar_api.enums.StatusNomeacao;
import br.edu.infnet.andre_gaspar_api.loader.AtividadeLoader;
import br.edu.infnet.andre_gaspar_api.loader.NomeacaoLoader;
import br.edu.infnet.andre_gaspar_api.loader.PeritoLoader;
import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import br.edu.infnet.andre_gaspar_api.model.Perito;
import br.edu.infnet.andre_gaspar_api.service.AtividadePericialService;
import br.edu.infnet.andre_gaspar_api.service.NomeacaoPericialService;
import br.edu.infnet.andre_gaspar_api.service.PeritoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InicializadorAplicacao implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        PeritoLoader peritoLoader = new PeritoLoader();
        NomeacaoLoader nomeacaoLoader = new NomeacaoLoader();
        AtividadeLoader atividadeLoader = new AtividadeLoader();

        PeritoService peritoService = new PeritoService();
        NomeacaoPericialService nomeacaoService =
                new NomeacaoPericialService();
        AtividadePericialService atividadeService =
                new AtividadePericialService();

        List<Perito> peritos = peritoLoader.carregar(peritoService);

        List<NomeacaoPericial> nomeacoes =
                nomeacaoLoader.carregar(peritoService,nomeacaoService);

        List<AtividadePericial> atividades =
                atividadeLoader.carregar(nomeacaoService,atividadeService);

        System.out.println();
        System.out.println("========================================");
        System.out.println("  SISTEMA DE GESTAO DE PERICIAS");
        System.out.println("  ETAPA 2 - ESTRUTURAS DE DADOS E SERVICOS");
        System.out.println("========================================");

        for (Perito perito : peritos) {
            System.out.println();
            System.out.println(perito);

            for (NomeacaoPericial nomeacao
                    : perito.getNomeacoes()) {

                System.out.println("  " + nomeacao);
                System.out.println(
                        "    " + nomeacao.getHonorarios()
                );

                for (AtividadePericial atividade
                        : nomeacao.getAtividades()) {

                    System.out.println("    " + atividade);
                }
            }
        }

        System.out.println();
        System.out.println("------------- RESUMO -------------------");
        System.out.println("Peritos carregados: " + peritos.size());
        System.out.println(
                "Nomeacoes carregadas: " + nomeacoes.size()
        );
        System.out.println(
                "Atividades carregadas: " + atividades.size()
        );
        System.out.println();
        System.out.println("------- CONSULTAS COM STREAMS -----------");

        System.out.println(
                "Nomeacoes recebidas: "
                        + nomeacaoService
                        .listarPorStatus(StatusNomeacao.RECEBIDA)
                        .size()
        );

        System.out.println(
                "Numeros dos processos: "
                        + nomeacaoService.listarNumerosProcessos()
        );

        System.out.println("Nomeacoes ordenadas por prazo:");

        for (NomeacaoPericial nomeacao
                : nomeacaoService.listarOrdenadasPorPrazo()) {
            System.out.println(
                    "  " + nomeacao.getDataLimite()
                            + " - " + nomeacao.getNumeroProcesso()
            );
        }

        NomeacaoPericial nomeacaoEncontrada =
                nomeacaoService.obterPorNumeroProcesso(
                        "0000001-00.2026.8.00.0001"
                );

        System.out.println(
                "Busca por numero: "
                        + nomeacaoEncontrada.getNumeroProcesso()
        );
        System.out.println("========================================");
        System.out.println();
    }
}
