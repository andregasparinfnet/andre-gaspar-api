package br.edu.infnet.andre_gaspar_api.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Perito extends Pessoa {

    private List<NomeacaoPericial> nomeacoes;

    public Perito(Long id, String nome, String email) {
        super(id, nome, email);
        this.nomeacoes = new ArrayList<>();
    }

    public List<NomeacaoPericial> getNomeacoes() {
        return Collections.unmodifiableList(nomeacoes);
    }

    public void adicionarNomeacao(NomeacaoPericial nomeacao) {
        this.nomeacoes.add(nomeacao);
    }

    public int quantidadeNomeacoes() {
        return nomeacoes.size();
    }

    @Override
    public String toString() {
        return "Perito{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", quantidadeNomeacoes=" + nomeacoes.size() +
                '}';
    }
}