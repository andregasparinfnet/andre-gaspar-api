package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.enums.StatusNomeacao;
import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeJaExistenteException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeNaoEncontradaException;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NomeacaoPericialService
        implements CrudService<NomeacaoPericial, Long> {

    private final Map<Long, NomeacaoPericial> nomeacoes =
            new LinkedHashMap<>();

    @Override
    public NomeacaoPericial incluir(NomeacaoPericial nomeacao) {
        validarNomeacao(nomeacao);

        if (nomeacoes.containsKey(nomeacao.getId())) {
            throw new EntidadeJaExistenteException(
                    "Já existe uma nomeação com o ID " + nomeacao.getId()
            );
        }

        nomeacoes.put(nomeacao.getId(), nomeacao);
        return nomeacao;
    }

    @Override
    public NomeacaoPericial alterar(NomeacaoPericial nomeacao) {
        validarNomeacao(nomeacao);

        if (!nomeacoes.containsKey(nomeacao.getId())) {
            throw new EntidadeNaoEncontradaException(
                    "Nomeação não encontrada: " + nomeacao.getId()
            );
        }

        nomeacoes.put(nomeacao.getId(), nomeacao);
        return nomeacao;
    }

    @Override
    public void excluir(Long id) {
        validarId(id);

        if (nomeacoes.remove(id) == null) {
            throw new EntidadeNaoEncontradaException(
                    "Nomeação não encontrada: " + id
            );
        }
    }

    @Override
    public NomeacaoPericial obterPorId(Long id) {
        validarId(id);

        NomeacaoPericial nomeacao = nomeacoes.get(id);

        if (nomeacao == null) {
            throw new EntidadeNaoEncontradaException(
                    "Nomeação não encontrada: " + id
            );
        }

        return nomeacao;
    }

    @Override
    public List<NomeacaoPericial> listarTodos() {
        return new ArrayList<>(nomeacoes.values());
    }

    public List<NomeacaoPericial> listarPorStatus(
            StatusNomeacao status
    ) {
        if (status == null) {
            throw new DadosInvalidosException(
                    "O status da nomeação é obrigatório"
            );
        }

        return nomeacoes.values()
                .stream()
                .filter(nomeacao -> nomeacao.getStatus() == status)
                .toList();
    }

    public List<NomeacaoPericial> listarOrdenadasPorPrazo() {
        return nomeacoes.values()
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

        return nomeacoes.values()
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
        return nomeacoes.values()
                .stream()
                .map(NomeacaoPericial::getNumeroProcesso)
                .toList();
    }

    private void validarNomeacao(NomeacaoPericial nomeacao) {
        if (nomeacao == null) {
            throw new DadosInvalidosException(
                    "Os dados da nomeação são obrigatórios"
            );
        }

        validarId(nomeacao.getId());

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

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new DadosInvalidosException(
                    "O ID da nomeação deve ser positivo"
            );
        }
    }
}

