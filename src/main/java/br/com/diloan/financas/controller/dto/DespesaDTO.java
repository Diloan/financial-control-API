package br.com.diloan.financas.controller.dto;

import br.com.diloan.financas.model.bussness.Despesa;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DespesaDTO {
    private Long id;
    private String descricao;
    private double valor;
    private LocalDate dataDespesa;

    public DespesaDTO(Despesa Despesa) {
        this.id = Despesa.getId();
        this.descricao = Despesa.getDescricao();
        this.valor = Despesa.getValor();
        this.dataDespesa = Despesa.getDataDespesa();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public static List<DespesaDTO> converter(List<Despesa> Despesas) {
        return Despesas.stream().map(DespesaDTO::new).collect(Collectors.toList());
    }
}
