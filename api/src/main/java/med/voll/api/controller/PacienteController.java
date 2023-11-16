package med.voll.api.controller;

import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPaciente dados){
        System.out.println("Dados do Paciente" + dados.nome());
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, page = 0) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }




}


