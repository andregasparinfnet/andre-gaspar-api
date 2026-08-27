package br.edu.infnet.andre_gaspar_api.model;

import java.time.LocalDate;

public class AtividadePericial {

    private Long id;
    private String descricao;
    private LocalDate prazo;
    private double horasEstimadas;
    private boolean concluida;

    public AtividadePericial(
            Long id,
            String descricao,
            LocalDate prazo,
            double horasEstimadas
    ) {
        this.id = id;
        this.descricao = descricao;
        this.prazo = prazo;
        this.horasEstimadas = horasEstimadas;
        this.concluida = false;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public double getHorasEstimadas() {
        return horasEstimadas;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        this.concluida = true;
    }

    public void reabrir() {
        this.concluida = false;
    }

    public boolean estaAtrasada() {
        return !concluida && prazo.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "AtividadePericial{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", prazo=" + prazo +
                ", horasEstimadas=" + horasEstimadas +
                ", concluida=" + concluida +
                '}';
    }
}