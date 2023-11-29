package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;
//classe criada pada fazer a validação de tempo de antecedência de agendamento
public class ValidadorHorarioAntecedencia {

    //método criado para validar o horário de agendamento
    public void validar(DadosAgendamentoConsulta dados){
        //recuperação da data informada pelo usuário
        var dataConsulta = dados.data();

        //código para validar os horários "agora" e a diferença em minutos.
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if(diferencaEmMinutos < 30 ){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
