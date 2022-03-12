package br.com.diloan.financas.controller;

import br.com.diloan.financas.controller.dto.DespesaDTO;
import br.com.diloan.financas.controller.form.DespesaForm;
import br.com.diloan.financas.model.bussness.Despesa;
import br.com.diloan.financas.model.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DespesaDTO> cadastrarDespesa(@RequestBody @Valid DespesaForm form, UriComponentsBuilder uriBuilder) {

        Despesa despesa = form.converter(despesaRepository);
        if (verificaSeExiste(form)) {
            despesaRepository.save(despesa);
            URI uri = uriBuilder.path("despesas/{id}").buildAndExpand(despesa.getId()).toUri();
            return ResponseEntity.created(uri).body(new DespesaDTO(despesa));
        } else {
            throw new DataIntegrityViolationException("Despesa já cadastrada!");
        }
    }

    private Boolean verificaSeExiste(DespesaForm form) {
        Optional<Despesa> despesa = despesaRepository.findByDescricaoAndDataDespesa(form.getDescricao(), form.getDataDespesa());
        return despesa.isEmpty();
    }

    @GetMapping
    public List<DespesaDTO> listarDespesas(String descricao) {
        List<Despesa> despesas;
        if (descricao == null) {
            despesas = despesaRepository.findAll();
            return DespesaDTO.converter(despesas);
        } else {
            despesas = despesaRepository.findByDescricao(descricao);
            return DespesaDTO.converter(despesas);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<DespesaDTO> detalharDespesa(@PathVariable Long id) {
        Optional<Despesa> despesa = despesaRepository.findById(id);
        if (despesa.isPresent()) {
            return ResponseEntity.ok(new DespesaDTO(despesa.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DespesaDTO> atualizarDespesa(@PathVariable Long id , @RequestBody @Valid DespesaForm form) {
        Optional<Despesa> optional = despesaRepository.findById(id);
        if (optional.isPresent()) {
            Despesa despesa = despesaRepository.getById(id);
            despesa.setDescricao(form.getDescricao());
            despesa.setValor(form.getValor());
            despesa.setDataDespesa(form.getDataDespesa());
            despesaRepository.save(despesa);
            return ResponseEntity.ok(new DespesaDTO(despesa));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DespesaDTO> removerDespesa(@PathVariable Long id) {
        Optional<Despesa> optional = despesaRepository.findById(id);
        if (optional.isPresent()) {
            despesaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
