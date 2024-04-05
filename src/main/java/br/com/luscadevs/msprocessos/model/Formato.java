package br.com.luscadevs.msprocessos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formatos")
@AllArgsConstructor
@NoArgsConstructor
public class Formato {
    @Id
    int codigo;
    String nome;
}
