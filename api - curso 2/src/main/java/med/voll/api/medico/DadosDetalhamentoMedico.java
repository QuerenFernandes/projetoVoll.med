package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

//dto criado para que esses dados sejam passados como retorno na atualização do cadastro do médico.
public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico(Medico medico){
        this(
                medico.getId(),
                medico.getNome(),
                medico.getTelefone(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
