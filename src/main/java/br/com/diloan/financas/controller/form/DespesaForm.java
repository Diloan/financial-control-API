package br.com.diloan.financas.controller.form;

import br.com.diloan.financas.model.bussness.Despesa;
import br.com.diloan.financas.model.repository.DespesaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class DespesaForm {

    @NotNull(message = "A descrição não pode ser nula.")
    @NotEmpty(message = "A descrição é obrigatoria.")
    private String descricao;
    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor não pode ser menor do que zero")
    private double valor;
    @NotNull(message = "A data da despesa não pode ser nula.")
    private LocalDate dataDespesa;

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

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public Despesa converter(DespesaRepository despesaRepository) {
        //Despesa despesa = (Despesa) despesaRepository.findByDescricao(descricao);
        return new Despesa(descricao, valor, dataDespesa);
    }
}
