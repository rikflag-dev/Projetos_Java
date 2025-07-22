package Intermediarios.Financas.Modelo;

import java.time.LocalDate;

public class Transacao {

    private String descricao;
    private double valor;
    private String categoria;
    private boolean entrada;
    private LocalDate data;

    public Transacao(String descricao, double valor, String categoria, boolean entrada, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.entrada = entrada;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public LocalDate getData() {
        return data;
    }
}

