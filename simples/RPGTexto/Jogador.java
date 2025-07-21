package simples.RPGTexto;

public class Jogador {
    private String nome;
    private int vida;
    private int mana;

    public Jogador(String nome, int vida, int mana) {
        this.nome = nome;
        this.vida = vida;
        this.mana = mana;
    }

    public void receberDano(int quantidade) {
        vida -= quantidade;
    }

    public void curar(int quantidade) {
        vida = Math.min(vida + quantidade, 100);
    }

    public void recuperarMana(int quantidade) {
        mana = Math.min(mana + quantidade, 50);
    }

    public void usarMana(int quantidade) {
        mana -= quantidade;
    }

    public boolean estaMorto() {
        return vida <= 0;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }
}
