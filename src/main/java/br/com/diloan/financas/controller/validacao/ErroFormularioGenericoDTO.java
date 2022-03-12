package br.com.diloan.financas.controller.validacao;

public class ErroFormularioGenericoDTO {
    private String erro;

    public ErroFormularioGenericoDTO(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
}
