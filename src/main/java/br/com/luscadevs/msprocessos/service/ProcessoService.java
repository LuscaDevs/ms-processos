package br.com.luscadevs.msprocessos.service;

import br.com.luscadevs.msprocessos.model.Processo;
import br.com.luscadevs.msprocessos.repository.ProcessoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo getProcessoByNumber(String numeroProcesso) {
        try {
            // Defina a URL do endpoint
            String url = "https://api-publica.datajud.cnj.jus.br/api_publica_tjpe/_search";

            // Defina o corpo da solicitação JSON
            String jsonInputString = "{\"query\":{\"match\":{\"numeroProcesso\":\"" + numeroProcesso + "\"}}}";

            // Crie uma conexão HTTP
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization",
                    "APIKey cDZHYzlZa0JadVREZDJCendQbXY6SkJlTzNjLV9TRENyQk1RdnFKZGRQdw==");
            conn.setRequestProperty("Content-Type", "application/json");

            // Permita a escrita para enviar o corpo da solicitação
            conn.setDoOutput(true);

            // Escreva o corpo da solicitação no fluxo de saída
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Obtenha a resposta
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Feche a conexão
            conn.disconnect();

            // Converta a resposta para o objeto Processo
            Processo processo = convertJsonToProcesso(response.toString());
            if (processo != null) {
                // Salve o processo no banco de dados
                return processo;
            } else {
                throw new RuntimeException("O corpo da resposta da solicitação é nulo.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao processar a resposta da API: " + e.getMessage());
        }
    }

    private Processo convertJsonToProcesso(String jsonResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        JsonNode hitsNode = rootNode.path("hits").path("hits");
        if (hitsNode.isArray() && hitsNode.size() > 0) {
            JsonNode sourceNode = hitsNode.get(0).path("_source");
            return mapper.treeToValue(sourceNode, Processo.class);
        }
        return null;
    }
}
