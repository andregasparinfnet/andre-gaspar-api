package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeJaExistenteException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeNaoEncontradaException;
import br.edu.infnet.andre_gaspar_api.model.Perito;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PeritoService implements CrudService<Perito, Long> {

    private final Map<Long, Perito> peritos = new LinkedHashMap<>();

    @Override
    public Perito incluir(Perito perito) {
        validarPerito(perito);

        if (peritos.containsKey(perito.getId())) {
            throw new EntidadeJaExistenteException(
                    "Já existe um perito com o ID " + perito.getId()
            );
        }

        peritos.put(perito.getId(), perito);
        return perito;
    }

    @Override
    public Perito alterar(Perito perito) {
        validarPerito(perito);

        if (!peritos.containsKey(perito.getId())) {
            throw new EntidadeNaoEncontradaException(
                    "Perito não encontrado: " + perito.getId()
            );
        }

        peritos.put(perito.getId(), perito);
        return perito;
    }

    @Override
    public void excluir(Long id) {
        validarId(id);

        if (peritos.remove(id) == null) {
            throw new EntidadeNaoEncontradaException(
                    "Perito não encontrado: " + id
            );
        }
    }

    @Override
    public Perito obterPorId(Long id) {
        validarId(id);

        Perito perito = peritos.get(id);

        if (perito == null) {
            throw new EntidadeNaoEncontradaException(
                    "Perito não encontrado: " + id
            );
        }

        return perito;
    }

    @Override
    public List<Perito> listarTodos() {
        return new ArrayList<>(peritos.values());
    }

    private void validarPerito(Perito perito) {
        if (perito == null) {
            throw new DadosInvalidosException(
                    "Os dados do perito são obrigatórios"
            );
        }

        validarId(perito.getId());

        if (perito.getNome() == null || perito.getNome().isBlank()) {
            throw new DadosInvalidosException(
                    "O nome do perito é obrigatório"
            );
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new DadosInvalidosException(
                    "O ID do perito deve ser positivo"
            );
        }
    }
}