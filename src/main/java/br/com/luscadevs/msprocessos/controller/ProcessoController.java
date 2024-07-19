package br.com.luscadevs.msprocessos.controller;

import org.springframework.web.bind.annotation.RestController;
import br.com.luscadevs.msprocessos.service.ProcessoService;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<ResponseEntity<Object>> getProcessoByNumber(@PathVariable String numeroProcesso) {
        return processoService.getProcessoByNumber(numeroProcesso)
                .map(processo -> ResponseEntity.ok().body((Object) processo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
