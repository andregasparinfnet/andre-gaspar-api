package br.edu.infnet.andre_gaspar_api.loader;

import br.edu.infnet.andre_gaspar_api.enums.StatusNomeacao;
import br.edu.infnet.andre_gaspar_api.model.HonorariosPericiais;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import br.edu.infnet.andre_gaspar_api.model.Perito;
import br.edu.infnet.andre_gaspar_api.service.NomeacaoPericialService;
import br.edu.infnet.andre_gaspar_api.service.PeritoService;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

public class NomeacaoLoader {

    public List<NomeacaoPericial> carregar(
            PeritoService peritoService,
            NomeacaoPericialService nomeacaoService
    ) throws IOException {

        ClassPathResource arquivo =
                new ClassPathResource("dados/nomeacoes.txt");

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
                Long peritoId = Long.valueOf(campos[1]);
                String numeroProcesso = campos[2];
                LocalDate dataNomeacao = LocalDate.parse(campos[3]);
                int prazoEmDias = Integer.parseInt(campos[4]);

                StatusNomeacao status =
                        StatusNomeacao.valueOf(campos[5]);

                BigDecimal valorProposto =
                        new BigDecimal(campos[6]);

                BigDecimal valorFixado =
                        new BigDecimal(campos[7]);

                boolean depositado =
                        Boolean.parseBoolean(campos[8]);

                BigDecimal valorRecebido =
                        new BigDecimal(campos[9]);

                HonorariosPericiais honorarios =
                        new HonorariosPericiais(valorProposto);

                honorarios.registrarValorFixado(valorFixado);

                if (depositado) {
                    honorarios.registrarDeposito();
                }

                if (valorRecebido.compareTo(BigDecimal.ZERO) > 0) {
                    honorarios.registrarRecebimento(valorRecebido);
                }

                NomeacaoPericial nomeacao =
                        new NomeacaoPericial(
                                id,
                                numeroProcesso,
                                dataNomeacao,
                                prazoEmDias,
                                honorarios
                        );

                nomeacao.alterarStatus(status);

                Perito perito = peritoService.obterPorId(peritoId);
                perito.adicionarNomeacao(nomeacao);

                nomeacaoService.incluir(nomeacao);
            }
        }

        return nomeacaoService.listarTodos();
    }
}