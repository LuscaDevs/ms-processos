package br.com.luscadevs.msprocessos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.luscadevs.msprocessos.model.Processo;
import br.com.luscadevs.msprocessos.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/processo")

public class ProcessoController {
    @Autowired
    private ProcessoService processoService;

    @GetMapping("/{numeroProcesso}")
    public ResponseEntity<Object> getProcessoByNumber(@PathVariable String numeroProcesso) {
        try {
            Processo processo = processoService.getProcessoByNumber(numeroProcesso);
            return ResponseEntity.ok().body(processo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao buscar usuário Id: " + e.getMessage());
        }
    }
}
