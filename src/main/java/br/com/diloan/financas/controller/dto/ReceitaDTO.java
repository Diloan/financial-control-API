package br.com.diloan.financas.controller.dto;

import br.com.diloan.financas.model.bussness.Receita;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReceitaDTO {
    private Long id;
    private String descricao;
    private double valor;
    private LocalDate dataReceita;

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.dataReceita = receita.getDataReceita();
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

    public LocalDate getDataReceita() {
        return dataReceita;
    }

    public static List<ReceitaDTO> converter(List<Receita> receitas) {
        return receitas.stream().map(ReceitaDTO::new).collect(Collectors.toList());
    }
}
