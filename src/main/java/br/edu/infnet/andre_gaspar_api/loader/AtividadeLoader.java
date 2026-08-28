package br.edu.infnet.andre_gaspar_api.loader;

import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import br.edu.infnet.andre_gaspar_api.service.AtividadePericialService;
import br.edu.infnet.andre_gaspar_api.service.NomeacaoPericialService;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

public class AtividadeLoader {

    public List<AtividadePericial> carregar(
            NomeacaoPericialService nomeacaoPericialService,
            AtividadePericialService atividadePericialService
    ) throws IOException {

        ClassPathResource arquivo =
                new ClassPathResource("dados/atividades.txt");

        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(
                        arquivo.getInputStream(),
                        StandardCharsets.UTF_8
                )
        )) {
            leitor.readLine();

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split(";", -1);

                Long id = Long.valueOf(campos[0]);
                Long nomeacaoId = Long.valueOf(campos[1]);
                String descricao = campos[2];
                LocalDate prazo = LocalDate.parse(campos[3]);
                double horasEstimadas =
                        Double.parseDouble(campos[4]);
                boolean concluida =
                        Boolean.parseBoolean(campos[5]);

                AtividadePericial atividade =
                        new AtividadePericial(
                                id,
                                descricao,
                                prazo,
                                horasEstimadas
                        );

                if (concluida) {
                    atividade.concluir();
                }

                NomeacaoPericial nomeacao =
                        nomeacaoPericialService.obterPorId(nomeacaoId);

                nomeacao.adicionarAtividade(atividade);
                atividadePericialService.incluir(atividade);
            }
        }

        return atividadePericialService.listarTodos();
    }
}