package br.com.diloan.financas.model.repository;

import br.com.diloan.financas.model.bussness.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByDescricao(String descricao);

    Optional<Receita>findByDescricaoAndDataReceita(String descricao, LocalDate data);

}
