package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeJaExistenteException;
import br.edu.infnet.andre_gaspar_api.exception.EntidadeNaoEncontradaException;
import br.edu.infnet.andre_gaspar_api.model.Identificavel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCrudService<T extends Identificavel>
        implements CrudService<T, Long> {

    private final Map<Long, T> dados = new LinkedHashMap<>();

    @Override
    public T incluir(T objeto) {
        validarObjeto(objeto);

        if (dados.containsKey(objeto.getId())) {
            throw new EntidadeJaExistenteException(
                    "Já existe uma entidade com o ID " + objeto.getId()
            );
        }

        dados.put(objeto.getId(), objeto);
        return objeto;
    }

    @Override
    public T alterar(T objeto) {
        validarObjeto(objeto);

        if (!dados.containsKey(objeto.getId())) {
            throw new EntidadeNaoEncontradaException(
                    "Entidade não encontrada: " + objeto.getId()
            );
        }

        dados.put(objeto.getId(), objeto);
        return objeto;
    }

    @Override
    public void excluir(Long id) {
        validarId(id);

        if (dados.remove(id) == null) {
            throw new EntidadeNaoEncontradaException(
                    "Entidade não encontrada: " + id
            );
        }
    }

    @Override
    public T obterPorId(Long id) {
        validarId(id);

        T objeto = dados.get(id);

        if (objeto == null) {
            throw new EntidadeNaoEncontradaException(
                    "Entidade não encontrada: " + id
            );
        }

        return objeto;
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    protected void validarDadosEspecificos(T objeto) {
    }

    private void validarObjeto(T objeto) {
        if (objeto == null) {
            throw new DadosInvalidosException(
                    "Os dados da entidade são obrigatórios"
            );
        }

        validarId(objeto.getId());
        validarDadosEspecificos(objeto);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new DadosInvalidosException(
                    "O ID da entidade deve ser positivo"
            );
        }
    }
}