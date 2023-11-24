package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        Endereco endereco
) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(
                paciente.getId(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getNome(),
                paciente.getTelefone(),
                paciente.getEndereco());
    }
}
