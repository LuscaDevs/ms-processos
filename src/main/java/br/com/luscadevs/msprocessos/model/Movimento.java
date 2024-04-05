package br.com.luscadevs.msprocessos.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimentos")
@AllArgsConstructor
@NoArgsConstructor
public class Movimento {
    @Id
    private int codigo;
    private String nome;
    private Date dataHora;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComplementoTabelado> complementosTabelados;
}