package br.com.luscadevs.msprocessos.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Processo {

    String numeroProcesso;
    Classe classe;
    Sistema sistema;
    Formato formato;
    String tribunal;
    Date dataHoraUltimaAtualizacao;
    String grau;
    Date dataAjuizamento;
    List<Movimento> movimentos;
    String id;
    int nivelSigilo;
    OrgaoJulgador orgaoJulgador;
    List<Assunto> assuntos;
}
