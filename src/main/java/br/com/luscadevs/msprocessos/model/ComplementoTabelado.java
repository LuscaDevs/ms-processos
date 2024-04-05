package br.com.luscadevs.msprocessos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "complementosTabelados")
@AllArgsConstructor
@NoArgsConstructor
public class ComplementoTabelado {
    @Id
    int codigo;
    int valor;
    String nome;
    String descricao;
}