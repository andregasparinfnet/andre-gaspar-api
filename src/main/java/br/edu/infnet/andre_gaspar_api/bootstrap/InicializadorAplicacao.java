package br.edu.infnet.andre_gaspar_api.bootstrap;

import br.edu.infnet.andre_gaspar_api.loader.AtividadeLoader;
import br.edu.infnet.andre_gaspar_api.loader.NomeacaoLoader;
import br.edu.infnet.andre_gaspar_api.loader.PeritoLoader;
import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import br.edu.infnet.andre_gaspar_api.model.Perito;
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

        List<Perito> peritos = peritoLoader.carregar();

        List<NomeacaoPericial> nomeacoes =
                nomeacaoLoader.carregar(peritos);

        List<AtividadePericial> atividades =
                atividadeLoader.carregar(nomeacoes);

        System.out.println();
        System.out.println("========================================");
        System.out.println("  SISTEMA DE GESTAO DE PERICIAS");
        System.out.println("  ETAPA 1 - ORIENTACAO A OBJETOS");
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
        System.out.println("========================================");
        System.out.println();
    }
}
