package med.voll.api.domain.medico;

//record criado porque nas solicatões de listagem, não eram necessárias todas as informações do cadastro
public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {


    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
