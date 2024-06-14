package br.com.luscadevs.msprocessos.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Movimento {

    private int codigo;
    private String nome;
    private Date dataHora;

    private List<ComplementoTabelado> complementosTabelados;
}