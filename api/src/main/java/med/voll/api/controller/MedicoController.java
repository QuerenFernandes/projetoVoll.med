package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    // nos métodos que precisam de transação, por exemplo: salvar, alterar, excluir, etc.
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){

        repository.save(new Medico(dados));
    }

    @GetMapping
    @RequestMapping("medicos")
    public List<DadosListagemMedico> listar(){
        //método stream e map foi usado para converter o List de médicos que era o óbvio em DadosListagemMédico que é
        // a lista específica do que foi solicitado
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }

}
