package br.com.luscadevs.msprocessos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessoScheduledTask {

    @Autowired
    private ProcessoService processoService;

    @Scheduled(fixedRate = 8 * 60 * 60 * 1000) // A cada 8 horas
    public void executeProcessoTask() {
        String numeroProcesso = "12345"; // Número do processo a ser consultado
        processoService.getProcessoByNumber(numeroProcesso)
                .subscribe(processo -> {
                    // Lógica adicional, se necessário
                    System.out.println("Processo consultado: " + processo);
                });
    }
}
