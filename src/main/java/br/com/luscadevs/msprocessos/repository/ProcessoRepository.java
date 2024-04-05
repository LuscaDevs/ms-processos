package br.com.luscadevs.msprocessos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luscadevs.msprocessos.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, String> {

}
