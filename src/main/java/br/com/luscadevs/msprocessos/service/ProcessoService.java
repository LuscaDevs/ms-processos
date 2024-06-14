package br.com.luscadevs.msprocessos.service;

import br.com.luscadevs.msprocessos.model.Processo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProcessoService {

    private final WebClient webClient;

    public ProcessoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api-publica.datajud.cnj.jus.br/api_publica_tjpe")
                .defaultHeader("Authorization", "APIKey cDZHYzlZa0JadVREZDJCendQbXY6SkJlTzNjLV9TRENyQk1RdnFKZGRQdw==")
                .build();
    }

    public Mono<Processo> getProcessoByNumber(String numeroProcesso) {
        return webClient.post()
                .uri("/_search")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue("{\"query\":{\"match\":{\"numeroProcesso\":\"" + numeroProcesso + "\"}}}"))
                .retrieve()
                .bodyToMono(String.class)
                .map(this::convertJsonToProcesso);
    }

    private Processo convertJsonToProcesso(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode hitsNode = rootNode.path("hits").path("hits");
            if (hitsNode.isArray() && hitsNode.size() > 0) {
                JsonNode sourceNode = hitsNode.get(0).path("_source");
                return mapper.treeToValue(sourceNode, Processo.class);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para objeto Processo", e);
        }
    }
}
