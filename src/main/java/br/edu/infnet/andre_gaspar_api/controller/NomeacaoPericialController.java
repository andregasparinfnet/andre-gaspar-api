package br.edu.infnet.andre_gaspar_api.controller;

import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.model.NomeacaoPericial;
import br.edu.infnet.andre_gaspar_api.service.NomeacaoPericialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nomeacoes")
@Tag(
        name = "Nomeações periciais",
        description = "Operações para gerenciamento das nomeações"
)
public class NomeacaoPericialController {

    private final NomeacaoPericialService nomeacaoService;

    public NomeacaoPericialController(
            NomeacaoPericialService nomeacaoService
    ) {
        this.nomeacaoService = nomeacaoService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as nomeações periciais")
    @ApiResponse(
            responseCode = "200",
            description = "Lista de nomeações obtida com sucesso"
    )
    public ResponseEntity<List<NomeacaoPericial>> listarTodos() {
        return ResponseEntity.ok(nomeacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma nomeação pelo identificador")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Nomeação encontrada"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Identificador inválido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nomeação não encontrada"
            )
    })
    public ResponseEntity<NomeacaoPericial> obterPorId(
            @Parameter(description = "Identificador da nomeação")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(nomeacaoService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "Inclui uma nova nomeação pericial")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Nomeação incluída com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da nomeação inválidos"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Já existe uma nomeação com o identificador"
            )
    })
    public ResponseEntity<NomeacaoPericial> incluir(
            @RequestBody NomeacaoPericial nomeacao
    ) {
        NomeacaoPericial nomeacaoIncluida =
                nomeacaoService.incluir(nomeacao);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nomeacaoIncluida);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera uma nomeação pericial existente")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Nomeação alterada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados ou identificador inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nomeação não encontrada"
            )
    })
    public ResponseEntity<NomeacaoPericial> alterar(
            @Parameter(description = "Identificador da nomeação")
            @PathVariable Long id,
            @RequestBody NomeacaoPericial nomeacao
    ) {
        if (!id.equals(nomeacao.getId())) {
            throw new DadosInvalidosException(
                    "O ID da URL deve ser igual ao ID da nomeação"
            );
        }

        return ResponseEntity.ok(
                nomeacaoService.alterar(nomeacao)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma nomeação pelo identificador")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Nomeação excluída com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Identificador inválido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nomeação não encontrada"
            )
    })
    public ResponseEntity<Void> excluir(
            @Parameter(description = "Identificador da nomeação")
            @PathVariable Long id
    ) {
        nomeacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}