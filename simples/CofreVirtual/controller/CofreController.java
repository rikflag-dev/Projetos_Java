package simples.CofreVirtual.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import simples.CofreVirtual.util.CriptografiaUtil;

public class CofreController {
    private static final String SENHA_MESTRA = "1234";
    private static final String ARQUIVO = "cofre.txt";

    public boolean autenticar(String senha) {
        return SENHA_MESTRA.equals(senha);
    }

    public void salvarTexto(String texto) {
        try (FileWriter fw = new FileWriter(ARQUIVO)) {
            String codificado = CriptografiaUtil.codificarBase64(texto);
            fw.write(codificado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String carregarTexto() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha = br.readLine();
            if (linha != null) {
                return CriptografiaUtil.decodificarBase64(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}

