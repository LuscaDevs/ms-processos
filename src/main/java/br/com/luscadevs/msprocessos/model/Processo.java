package br.com.luscadevs.msprocessos.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Processo {
    @Id
    String numeroProcesso;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Classe classe;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Sistema sistema;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Formato formato;
    String tribunal;
    Date dataHoraUltimaAtualizacao;
    String grau;
    Date dataAjuizamento;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Movimento> movimentos;
    String id;
    int nivelSigilo;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    OrgaoJulgador orgaoJulgador;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Assunto> assuntos;
}
