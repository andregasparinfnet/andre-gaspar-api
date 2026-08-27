package br.edu.infnet.andre_gaspar_api.model;

import br.edu.infnet.andre_gaspar_api.enums.StatusNomeacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NomeacaoPericial {

    private Long id;
    private String numeroProcesso;
    private LocalDate dataNomeacao;
    private LocalDate dataLimite;
    private int prazoEmDias;
    private StatusNomeacao status;
    private HonorariosPericiais honorarios;
    private List<AtividadePericial> atividades;

    public NomeacaoPericial(
            Long id,
            String numeroProcesso,
            LocalDate dataNomeacao,
            int prazoEmDias,
            HonorariosPericiais honorarios
    ) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
        this.dataNomeacao = dataNomeacao;
        this.prazoEmDias = prazoEmDias;
        this.dataLimite = dataNomeacao.plusDays(prazoEmDias);
        this.honorarios = honorarios;
        this.status = StatusNomeacao.RECEBIDA;
        this.atividades = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public LocalDate getDataNomeacao() {
        return dataNomeacao;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public int getPrazoEmDias() {
        return prazoEmDias;
    }

    public StatusNomeacao getStatus() {
        return status;
    }

    public HonorariosPericiais getHonorarios() {
        return honorarios;
    }

    public List<AtividadePericial> getAtividades() {
        return Collections.unmodifiableList(atividades);
    }

    public void aceitar() {
        this.status = StatusNomeacao.ACEITA;
    }

    public void recusar() {
        this.status = StatusNomeacao.RECUSADA;
    }

    public void alterarStatus(StatusNomeacao novoStatus) {
        this.status = novoStatus;
    }

    public void adicionarAtividade(AtividadePericial atividade) {
        this.atividades.add(atividade);
    }

    public int quantidadeAtividades() {
        return atividades.size();
    }

    public boolean estaAtrasada() {
        return status != StatusNomeacao.FINALIZADA
                && dataLimite.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "NomeacaoPericial{" +
                "id=" + id +
                ", numeroProcesso='" + numeroProcesso + '\'' +
                ", dataNomeacao=" + dataNomeacao +
                ", dataLimite=" + dataLimite +
                ", prazoEmDias=" + prazoEmDias +
                ", status=" + status +
                ", honorarios=" + honorarios +
                ", quantidadeAtividades=" + atividades.size() +
                '}';
    }
}