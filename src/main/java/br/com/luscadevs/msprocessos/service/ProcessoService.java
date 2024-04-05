package br.com.luscadevs.msprocessos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.luscadevs.msprocessos.model.Processo;
import br.com.luscadevs.msprocessos.repository.ProcessoRepository;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;

    private final String apiUrl = "https://api-publica.datajud.cnj.jus.br/api_publica_tjpe/_search";

    @Autowired
    private RestTemplate restTemplate;

    public Processo getProcessoByNumber(String numeroProcesso) {
        // Criando o corpo da solicitação JSON
        String jsonInputString = "{\"query\":{\"match\":{\"numeroProcesso\":\"" + numeroProcesso + "\"}}}";

        // Criando os cabeçalhos da solicitação
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "APIKey cDZHYzlZa0JadVREZDJCendQbXY6SkJlTzNjLV9TRENyQk1RdnFKZGRQdw==");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Criando a entidade de solicitação com o corpo e os cabeçalhos definidos
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        // Fazendo a solicitação e obtendo a resposta
        ResponseEntity<Processo> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
                Processo.class);

        // Verificando se o corpo da resposta não é nulo
        Processo processo = responseEntity.getBody();
        if (processo != null) {
            // Salvando o processo no banco de dados
            return processo;
        } else {
            throw new RuntimeException("O corpo da resposta da solicitação é nulo.");
        }
    }
}
