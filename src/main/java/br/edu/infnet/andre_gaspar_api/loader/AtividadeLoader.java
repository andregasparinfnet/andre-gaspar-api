package br.edu.infnet.andre_gaspar_api.loader;

import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtividadeLoader {

    public List<AtividadePericial> carregar(
            List<NomeacaoPericial> nomeacoes
    ) throws IOException {

        List<AtividadePericial> atividades = new ArrayList<>();

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
                        buscarNomeacao(nomeacoes, nomeacaoId);

                nomeacao.adicionarAtividade(atividade);
                atividades.add(atividade);
            }
        }

        return atividades;
    }

    private NomeacaoPericial buscarNomeacao(
            List<NomeacaoPericial> nomeacoes,
            Long nomeacaoId
    ) {
        for (NomeacaoPericial nomeacao : nomeacoes) {
            if (nomeacao.getId().equals(nomeacaoId)) {
                return nomeacao;
            }
        }

        throw new IllegalArgumentException(
                "Nomeação não encontrada: " + nomeacaoId
        );
    }
}