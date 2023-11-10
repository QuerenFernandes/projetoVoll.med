package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

//nome da tabela no banco de dados
@Table(name = "medicos")

//informa que a classe é uma entidade do banco de dados
@Entity(name = "Medico")

//automatiza a geração de getters na classe, sem declarar
@Getter
//essa anotação é responsável por gerar um construtor sem parâmetros
@NoArgsConstructor
//é responsável por gerar um construtor com 1 parâmetro para cada atributo de sua classe.
@AllArgsConstructor

//substitui o tostring
@EqualsAndHashCode(of = "id")
public class Medico {
    //representação do ID e instrução de geração do mesmo.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;
    private String crm;

    //representação de um Enum
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    //identifica que a classe endereço compõe a classe Medico
    @Embedded
    private Endereco endereco;

    //CONSTRUTOR DA CLASSE
    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
