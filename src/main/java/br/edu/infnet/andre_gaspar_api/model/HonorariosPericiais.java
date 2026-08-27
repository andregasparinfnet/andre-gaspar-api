package br.edu.infnet.andre_gaspar_api.model;

import java.math.BigDecimal;

public class HonorariosPericiais {

    private BigDecimal valorProposto;
    private BigDecimal valorFixado;
    private BigDecimal valorRecebido;
    private boolean depositado;

    public HonorariosPericiais(BigDecimal valorProposto) {
        this.valorProposto = valorProposto;
        this.valorFixado = BigDecimal.ZERO;
        this.valorRecebido = BigDecimal.ZERO;
        this.depositado = false;
    }

    public BigDecimal getValorProposto() {
        return valorProposto;
    }

    public BigDecimal getValorFixado() {
        return valorFixado;
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public boolean isDepositado() {
        return depositado;
    }

    public void registrarValorFixado(BigDecimal valorFixado) {
        this.valorFixado = valorFixado;
    }

    public void registrarDeposito() {
        this.depositado = true;
    }

    public void registrarRecebimento(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    @Override
    public String toString() {
        return "HonorariosPericiais{" +
                "valorProposto=" + valorProposto +
                ", valorFixado=" + valorFixado +
                ", valorRecebido=" + valorRecebido +
                ", depositado=" + depositado +
                '}';
    }
}