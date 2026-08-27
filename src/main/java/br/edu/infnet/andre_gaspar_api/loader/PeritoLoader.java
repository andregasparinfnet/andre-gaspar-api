package br.edu.infnet.andre_gaspar_api.loader;

import br.edu.infnet.andre_gaspar_api.model.Perito;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PeritoLoader {

    public List<Perito> carregar() throws IOException {
        List<Perito> peritos = new ArrayList<>();

        ClassPathResource arquivo =
                new ClassPathResource("dados/peritos.txt");

        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(
                        arquivo.getInputStream(),
                        StandardCharsets.UTF_8
                )
        )) {
            leitor.readLine();

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split(";", -1);

                Long id = Long.valueOf(campos[0]);
                String nome = campos[1];
                String email = campos[2];

                Perito perito = new Perito(id, nome, email);
                peritos.add(perito);
            }
        }

        return peritos;
    }
}