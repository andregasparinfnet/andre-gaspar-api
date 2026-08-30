package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.model.AtividadePericial;
import org.springframework.stereotype.Service;

@Service
public class AtividadePericialService
        extends BaseCrudService<AtividadePericial> {

    @Override
    protected void validarDadosEspecificos(AtividadePericial atividade) {
        if (atividade.getDescricao() == null
                || atividade.getDescricao().isBlank()) {
            throw new DadosInvalidosException(
                    "A descrição da atividade é obrigatória"
            );
        }

        if (atividade.getPrazo() == null) {
            throw new DadosInvalidosException(
                    "O prazo da atividade é obrigatório"
            );
        }

        if (atividade.getHorasEstimadas() <= 0) {
            throw new DadosInvalidosException(
                    "As horas estimadas devem ser positivas"
            );
        }
    }
}
