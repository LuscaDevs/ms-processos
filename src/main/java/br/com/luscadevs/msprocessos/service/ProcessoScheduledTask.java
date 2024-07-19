package br.com.luscadevs.msprocessos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessoScheduledTask {

    @Autowired
    private ProcessoService processoService;

    @Scheduled(fixedRate = 14400000) // A cada 4 horas
    public void executeProcessoTask() {
        String numeroProcesso = "00011646420238173390"; // Número do processo a ser consultado
        processoService.getProcessoByNumber(numeroProcesso)
                .subscribe(processo -> {
                    // Lógica adicional, se necessário
                    System.out.println("Processo consultado: " + processo);
                });
    }
}
