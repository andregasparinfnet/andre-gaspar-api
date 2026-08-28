package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.enums.StatusNomeacao;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeJaExistenteException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeNaoEncontradaException;
import br.edu.infnet.andre_gaspar_api.model.HonorariosPericiais;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
class NomeacaoPericialServiceTest {

    @Test
    void deveIncluirEObterNomeacaoPorId() {
        NomeacaoPericialService service =
                new NomeacaoPericialService();

        NomeacaoPericial nomeacao = criarNomeacao(
                1L,
                "0000001-00.2026.8.00.0001",
                LocalDate.of(2026, 8, 20),
                StatusNomeacao.RECEBIDA
        );

        service.incluir(nomeacao);

        assertSame(nomeacao, service.obterPorId(1L));
        assertEquals(1, service.listarTodos().size());
    }

    @Test
    void deveAlterarEExcluirNomeacao() {
        NomeacaoPericialService service =
                new NomeacaoPericialService();

        NomeacaoPericial original = criarNomeacao(
                1L,
                "PROCESSO-ORIGINAL",
                LocalDate.of(2026, 8, 20),
                StatusNomeacao.RECEBIDA
        );

        NomeacaoPericial alterada = criarNomeacao(
                1L,
                "PROCESSO-ALTERADO",
                LocalDate.of(2026, 8, 21),
                StatusNomeacao.ACEITA
        );

        service.incluir(original);
        service.alterar(alterada);

        assertSame(alterada, service.obterPorId(1L));

        service.excluir(1L);

        assertEquals(0, service.listarTodos().size());
        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.obterPorId(1L)
        );
    }

    @Test
    void deveRejeitarDuplicidadeEIdInexistente() {
        NomeacaoPericialService service =
                new NomeacaoPericialService();

        NomeacaoPericial nomeacao = criarNomeacao(
                1L,
                "PROCESSO-1",
                LocalDate.of(2026, 8, 20),
                StatusNomeacao.RECEBIDA
        );

        service.incluir(nomeacao);

        assertThrows(
                EntidadeJaExistenteException.class,
                () -> service.incluir(nomeacao)
        );

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.obterPorId(99L)
        );
    }

    @Test
    void deveConsultarNomeacoesComStreams() {
        NomeacaoPericialService service =
                new NomeacaoPericialService();

        NomeacaoPericial primeira = criarNomeacao(
                1L,
                "PROCESSO-1",
                LocalDate.of(2026, 9, 10),
                StatusNomeacao.ACEITA
        );

        NomeacaoPericial segunda = criarNomeacao(
                2L,
                "PROCESSO-2",
                LocalDate.of(2026, 8, 10),
                StatusNomeacao.RECEBIDA
        );

        service.incluir(primeira);
        service.incluir(segunda);

        assertEquals(
                1,
                service.listarPorStatus(
                        StatusNomeacao.RECEBIDA
                ).size()
        );

        assertEquals(
                List.of(segunda, primeira),
                service.listarOrdenadasPorPrazo()
        );

        assertSame(
                primeira,
                service.obterPorNumeroProcesso("PROCESSO-1")
        );

        assertEquals(
                List.of("PROCESSO-1", "PROCESSO-2"),
                service.listarNumerosProcessos()
        );
    }

    private NomeacaoPericial criarNomeacao(
            Long id,
            String numeroProcesso,
            LocalDate dataNomeacao,
            StatusNomeacao status
    ) {
        HonorariosPericiais honorarios =
                new HonorariosPericiais(
                        new BigDecimal("1000.00")
                );

        NomeacaoPericial nomeacao =
                new NomeacaoPericial(
                        id,
                        numeroProcesso,
                        dataNomeacao,
                        5,
                        honorarios
                );

        nomeacao.alterarStatus(status);
        return nomeacao;
    }
}