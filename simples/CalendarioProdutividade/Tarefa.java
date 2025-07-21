package simples.CalendarioProdutividade;

public class Tarefa {
    private String descricao;
    private TarefaTipo tipo;

    public Tarefa(String descricao, TarefaTipo tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public TarefaTipo getTipo() {
        return tipo;
    }
}
