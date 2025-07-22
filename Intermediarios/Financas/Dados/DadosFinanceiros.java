package Intermediarios.Financas.Dados;

import java.util.ArrayList;
import java.util.List;
import Intermediarios.Financas.Modelo.Transacao;

public class DadosFinanceiros {

    private static final List<Transacao> transacoes = new ArrayList<>();

    public static void adicionarTransacao(Transacao t) {
        transacoes.add(t);
    }

    public static List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public static void limparTransacoes() {
        transacoes.clear();
    }
}
