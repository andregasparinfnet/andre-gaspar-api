package br.edu.infnet.andre_gaspar_api.service;

import br.edu.infnet.andre_gaspar_api.exception.DadosInvalidosException;
import br.edu.infnet.andre_gaspar_api.model.Perito;
import org.springframework.stereotype.Service;

@Service
public class PeritoService extends BaseCrudService<Perito> {

    @Override
    protected void validarDadosEspecificos(Perito perito) {
        if (perito.getNome() == null || perito.getNome().isBlank()) {
            throw new DadosInvalidosException(
                    "O nome do perito é obrigatório"
            );
        }
    }
}