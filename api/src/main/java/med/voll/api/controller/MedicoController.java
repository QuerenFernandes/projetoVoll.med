package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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


    //pageable é a classe do spring que faz a paginação automaticamente
    // Page substitui o "List", que devolve informações sobre a paginação
    //método stream e map foi usado para converter o List de médicos que era o óbvio em DadosListagemMédico que é
    // a lista específica do que foi solicitado
    // métodos .stream() e .toList removidos devido ao uso do Page, a conversão necessário do DadosListagemMedico é feita automaticamente
    //OBSERVAR NA REQUISIÇÃO QUE VAI TER INFORMAÇÕES NECESSÁRIAS PARA O FRONTEND.
    //A anotação @PagleableDefalut define a regra de ordenação por nome e apenas 10 cadastros por página na requisição


    /*@GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){

        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    */

    //novo método para listar apenas os médicos que estão ativos
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){

        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaoMedico dados){
        //acessando o objeto no banco de dados pelo id
        var medico = repository.getReferenceById(dados.id());
        //acessando o método da classe médico para atualizar os dados
        medico.atualizarInformacoes(dados);
   }
/*
   //MÉTODO PARA EXCLUIR DO BANCO.
   @DeleteMapping("/{id}")
   @Transactional
   // anotação @pathvariable usada para informar o spring que o id é uma variável para acessar o id do objeto
   public void excluir(@PathVariable Long id){
        //linha usada para deletar o objeto do banco de dados
        repository.deleteById(id);

   }

 */
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        //acessando o objeto no banco de dados pelo id
        var medico = repository.getReferenceById(id);
        //acessando o método da classe médico para inativar o cadastro
        medico.excluir();
    }
}
