package br.edu.infnet.andre_gaspar_api.loader;

import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import br.edu.infnet.andre_gaspar_api.model.Perito;
import br.edu.infnet.andre_gaspar_api.service.AtividadePericialService;
import br.edu.infnet.andre_gaspar_api.service.NomeacaoPericialService;
import br.edu.infnet.andre_gaspar_api.service.PeritoService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class LoaderIntegracaoTest {

    @Test
    void deveCarregarArquivosNosServicosEPreservarRelacionamentos()
            throws IOException {

        PeritoService peritoService = new PeritoService();
        NomeacaoPericialService nomeacaoService =
                new NomeacaoPericialService();
        AtividadePericialService atividadeService =
                new AtividadePericialService();

        PeritoLoader peritoLoader = new PeritoLoader();
        NomeacaoLoader nomeacaoLoader = new NomeacaoLoader();
        AtividadeLoader atividadeLoader = new AtividadeLoader();

        List<Perito> peritos =
                peritoLoader.carregar(peritoService);

        List<NomeacaoPericial> nomeacoes =
                nomeacaoLoader.carregar(
                        peritoService,
                        nomeacaoService
                );

        List<AtividadePericial> atividades =
                atividadeLoader.carregar(
                        nomeacaoService,
                        atividadeService
                );

        assertEquals(1, peritos.size());
        assertEquals(2, nomeacoes.size());
        assertEquals(4, atividades.size());

        assertEquals(1, peritoService.listarTodos().size());
        assertEquals(2, nomeacaoService.listarTodos().size());
        assertEquals(4, atividadeService.listarTodos().size());

        Perito perito = peritoService.obterPorId(1L);

        assertEquals(2, perito.getNomeacoes().size());
        assertSame(
                nomeacaoService.obterPorId(1L),
                perito.getNomeacoes().get(0)
        );

        assertEquals(
                3,
                nomeacaoService
                        .obterPorId(1L)
                        .getAtividades()
                        .size()
        );

        assertEquals(
                1,
                nomeacaoService
                        .obterPorId(2L)
                        .getAtividades()
                        .size()
        );

        assertSame(
                atividadeService.obterPorId(1L),
                nomeacaoService
                        .obterPorId(1L)
                        .getAtividades()
                        .get(0)
        );
    }
}
