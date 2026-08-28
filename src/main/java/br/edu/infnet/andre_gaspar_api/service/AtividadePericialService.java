package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeJaExistenteException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeNaoEncontradaException;
import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AtividadePericialService
        implements CrudService<AtividadePericial, Long> {

    private final Map<Long, AtividadePericial> atividades =
            new LinkedHashMap<>();

    @Override
    public AtividadePericial incluir(AtividadePericial atividade) {
        validarAtividade(atividade);

        if (atividades.containsKey(atividade.getId())) {
            throw new EntidadeJaExistenteException(
                    "Já existe uma atividade com o ID " + atividade.getId()
            );
        }

        atividades.put(atividade.getId(), atividade);
        return atividade;
    }

    @Override
    public AtividadePericial alterar(AtividadePericial atividade) {
        validarAtividade(atividade);

        if (!atividades.containsKey(atividade.getId())) {
            throw new EntidadeNaoEncontradaException(
                    "Atividade não encontrada: " + atividade.getId()
            );
        }

        atividades.put(atividade.getId(), atividade);
        return atividade;
    }

    @Override
    public void excluir(Long id) {
        validarId(id);

        if (atividades.remove(id) == null) {
            throw new EntidadeNaoEncontradaException(
                    "Atividade não encontrada: " + id
            );
        }
    }

    @Override
    public AtividadePericial obterPorId(Long id) {
        validarId(id);

        AtividadePericial atividade = atividades.get(id);

        if (atividade == null) {
            throw new EntidadeNaoEncontradaException(
                    "Atividade não encontrada: " + id
            );
        }

        return atividade;
    }

    @Override
    public List<AtividadePericial> listarTodos() {
        return new ArrayList<>(atividades.values());
    }

    private void validarAtividade(AtividadePericial atividade) {
        if (atividade == null) {
            throw new DadosInvalidosException(
                    "Os dados da atividade são obrigatórios"
            );
        }

        validarId(atividade.getId());

        if (atividade.getDescricao() == null
                || atividade.getDescricao().isBlank()) {
            throw new DadosInvalidosException(
                    "A descrição da atividade é obrigatória"
            );
        }

        if (atividade.getPrazo() == null) {
            throw new DadosInvalidosException(
                    "O prazo da atividade é obrigatório"
            );
        }

        if (atividade.getHorasEstimadas() <= 0) {
            throw new DadosInvalidosException(
                    "As horas estimadas devem ser positivas"
            );
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new DadosInvalidosException(
                    "O ID da atividade deve ser positivo"
            );
        }
    }
}
