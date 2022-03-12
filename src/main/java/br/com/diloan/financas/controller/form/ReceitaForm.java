package br.com.diloan.financas.controller.form;

import br.com.diloan.financas.model.bussness.Receita;
import br.com.diloan.financas.model.repository.ReceitaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class ReceitaForm {

    @NotNull(message = "A descrição não pode ser nula.")
    @NotEmpty(message = "A descrição é obrigatoria.")
    private String descricao;
    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor não pode ser menor do que zero")
    private double valor;
    @NotNull(message = "A data da receita não pode ser nula.")
    private LocalDate dataReceita;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(LocalDate dataReceita) {
        this.dataReceita = dataReceita;
    }

    public Receita converter(ReceitaRepository receitaRepository) {
        //Receita receita = (Receita) receitaRepository.findByDescricao(descricao);
        return new Receita(descricao, valor, dataReceita);
    }
}
