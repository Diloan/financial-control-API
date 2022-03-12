package br.com.diloan.financas.model.repository;

import br.com.diloan.financas.model.bussness.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findByDescricao(String descricao);

    Optional<Despesa>findByDescricaoAndDataDespesa(String descricao, LocalDate data);

}
