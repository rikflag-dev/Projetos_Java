package simples.RPGTexto;

public class Inimigo {
    private String nome;
    private int vida;
    private int mana;

    public Inimigo(String nome, int vida, int mana) {
        this.nome = nome;
        this.vida = vida;
        this.mana = mana;
    }

    public void receberDano(int quantidade) {
        vida -= quantidade;
    }

    public boolean estaMorto() {
        return vida <= 0;
    }

    public String getNome() {
        return nome;
    }
}
