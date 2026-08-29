package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.enums.StatusNomeacao;
import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeNaoEncontradaException;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;

import java.util.Comparator;
import java.util.List;

public class NomeacaoPericialService
        extends BaseCrudService<NomeacaoPericial> {
    public List<NomeacaoPericial> listarPorStatus(
            StatusNomeacao status
    ) {
        if (status == null) {
            throw new DadosInvalidosException(
                    "O status da nomeação é obrigatório"
            );
        }
        return listarTodos()
                .stream()
                .filter(nomeacao -> nomeacao.getStatus() == status)
                .toList();
    }

    public List<NomeacaoPericial> listarOrdenadasPorPrazo() {
        return listarTodos()
                .stream()
                .sorted(Comparator.comparing(
                        NomeacaoPericial::getDataLimite
                ))
                .toList();
    }

    public NomeacaoPericial obterPorNumeroProcesso(
            String numeroProcesso
    ) {
        if (numeroProcesso == null || numeroProcesso.isBlank()) {
            throw new DadosInvalidosException(
                    "O número do processo é obrigatório"
            );
        }

        return listarTodos()
                .stream()
                .filter(nomeacao -> nomeacao.getNumeroProcesso()
                        .equals(numeroProcesso))
                .findFirst()
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Nomeação não encontrada para o processo: "
                                        + numeroProcesso
                        )
                );
    }

    public List<String> listarNumerosProcessos() {
        return listarTodos()
                .stream()
                .map(NomeacaoPericial::getNumeroProcesso)
                .toList();
    }

    @Override
    protected void validarDadosEspecificos(
            NomeacaoPericial nomeacao
    ) {
        if (nomeacao.getNumeroProcesso() == null
                || nomeacao.getNumeroProcesso().isBlank()) {
            throw new DadosInvalidosException(
                    "O número do processo é obrigatório"
            );
        }

        if (nomeacao.getDataNomeacao() == null) {
            throw new DadosInvalidosException(
                    "A data da nomeação é obrigatória"
            );
        }

        if (nomeacao.getPrazoEmDias() <= 0) {
            throw new DadosInvalidosException(
                    "O prazo da nomeação deve ser positivo"
            );
        }

        if (nomeacao.getHonorarios() == null) {
            throw new DadosInvalidosException(
                    "Os honorários da nomeação são obrigatórios"
            );
        }
    }
}