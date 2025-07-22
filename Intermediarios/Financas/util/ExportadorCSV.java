package Intermediarios.Financas.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Intermediarios.Financas.Modelo.Transacao;

public class ExportadorCSV {

    public static void exportarParaCSV(List<Transacao> lista, String caminhoArquivo) {
        try (FileWriter escritor = new FileWriter(caminhoArquivo)) {
            escritor.write("Descrição;Valor;Categoria;Tipo;Data\n");
            for (Transacao t : lista) {
                String tipo = t.isEntrada() ? "Entrada" : "Saída";
                escritor.write(String.format("%s;%.2f;%s;%s;%s\n",
                    t.getDescricao(),
                    t.getValor(),
                    t.getCategoria(),
                    tipo,
                    t.getData().toString()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
