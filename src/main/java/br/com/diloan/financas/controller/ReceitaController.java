package br.com.diloan.financas.controller;

import br.com.diloan.financas.controller.dto.ReceitaDTO;
import br.com.diloan.financas.controller.form.ReceitaForm;
import br.com.diloan.financas.model.bussness.Receita;
import br.com.diloan.financas.model.repository.ReceitaRepository;
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
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ReceitaDTO> cadastrarReceita(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {

        Receita receita = form.converter(receitaRepository);
        if (verificaSeExiste(form)) {
            receitaRepository.save(receita);
            URI uri = uriBuilder.path("receitas/{id}").buildAndExpand(receita.getId()).toUri();
            return ResponseEntity.created(uri).body(new ReceitaDTO(receita));
        } else {
            throw new DataIntegrityViolationException("Receita já cadastrada!");
        }
    }

    private Boolean verificaSeExiste(ReceitaForm form) {
        Optional<Receita> receita = receitaRepository.findByDescricaoAndDataReceita(form.getDescricao(), form.getDataReceita());
        return receita.isEmpty();
    }

    @GetMapping
    public List<ReceitaDTO> listarReceitas(String descricao) {
        List<Receita> receitas;
        if (descricao == null) {
            receitas = receitaRepository.findAll();
            return ReceitaDTO.converter(receitas);
        } else {
            receitas = receitaRepository.findByDescricao(descricao);
            return ReceitaDTO.converter(receitas);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> detalharReceita(@PathVariable Long id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()) {
            return ResponseEntity.ok(new ReceitaDTO(receita.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ReceitaDTO> atualizarReceita(@PathVariable Long id , @RequestBody @Valid ReceitaForm form) {
        Optional<Receita> optional = receitaRepository.findById(id);
        if (optional.isPresent()) {
            Receita receita = receitaRepository.getById(id);
            receita.setDescricao(form.getDescricao());
            receita.setValor(form.getValor());
            receita.setDataReceita(form.getDataReceita());
            receitaRepository.save(receita);
            return ResponseEntity.ok(new ReceitaDTO(receita));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ReceitaDTO> removerReceita(@PathVariable Long id) {
        Optional<Receita> optional = receitaRepository.findById(id);
        if (optional.isPresent()) {
            receitaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
