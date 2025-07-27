package simples.JogoMemoria.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ranking {
    private static final String ARQUIVO = "ranking.txt";

    public static void salvar(String nome, int tempo) {
        try (FileWriter fw = new FileWriter(ARQUIVO, true)) {
            fw.write(nome + "," + tempo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exibir(JFrame parent) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            linhas.add("Nenhum ranking encontrado.");
        }

        linhas.sort(Comparator.comparingInt(l -> Integer.parseInt(l.split(",")[1])));
        StringBuilder sb = new StringBuilder("🏆 Ranking dos Melhores Tempos:\n\n");
        int posicao = 1;
        for (String l : linhas) {
            String[] dados = l.split(",");
            sb.append(posicao++).append("º ").append(dados[0]).append(" - ").append(dados[1]).append("s\n");
            if (posicao > 10) break;
        }

        JOptionPane.showMessageDialog(parent, sb.toString(), "Ranking", JOptionPane.INFORMATION_MESSAGE);
    }
}

