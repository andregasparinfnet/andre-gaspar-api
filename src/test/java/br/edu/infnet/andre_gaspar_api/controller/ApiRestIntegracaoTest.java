package br.edu.infnet.andre_gaspar_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiRestIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveListarOsTresContextosDeNegocio() throws Exception {
        mockMvc.perform(get("/api/peritos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        mockMvc.perform(get("/api/nomeacoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        mockMvc.perform(get("/api/atividades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void deveExecutarCrudCompletoDeAtividade() throws Exception {
        String atividadeNova = """
                {
                  "id": 50,
                  "descricao": "Organizar documentos da perícia",
                  "prazo": "2026-09-20",
                  "horasEstimadas": 3.0,
                  "concluida": false
                }
                """;

        mockMvc.perform(post("/api/atividades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atividadeNova))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(50))
                .andExpect(jsonPath("$.descricao")
                        .value("Organizar documentos da perícia"));

        String atividadeAlterada = """
                {
                  "id": 50,
                  "descricao": "Organizar e revisar documentos da perícia",
                  "prazo": "2026-09-22",
                  "horasEstimadas": 4.0,
                  "concluida": false
                }
                """;

        mockMvc.perform(put("/api/atividades/50")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atividadeAlterada))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao")
                        .value("Organizar e revisar documentos da perícia"))
                .andExpect(jsonPath("$.horasEstimadas").value(4.0));

        mockMvc.perform(get("/api/atividades/50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(50));

        mockMvc.perform(delete("/api/atividades/50"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/atividades/50"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void devePadronizarRespostasDeErro() throws Exception {
        mockMvc.perform(get("/api/peritos/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.erro").value("Not Found"))
                .andExpect(jsonPath("$.mensagem")
                        .value("Entidade não encontrada: 999"))
                .andExpect(jsonPath("$.caminho")
                        .value("/api/peritos/999"));

        mockMvc.perform(get("/api/atividades/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void deveDisponibilizarDocumentacaoOpenApi() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title")
                        .value("André Gaspar API"))
                .andExpect(jsonPath("$.info.version")
                        .value("0.3.0"))
                .andExpect(jsonPath("$.paths['/api/peritos'].get")
                        .exists())
                .andExpect(jsonPath("$.paths['/api/nomeacoes'].get")
                        .exists())
                .andExpect(jsonPath("$.paths['/api/atividades'].post")
                        .exists())
                .andExpect(jsonPath(
                        "$.paths['/api/atividades']"
                                + ".post.responses['201']"
                ).exists());
    }
}