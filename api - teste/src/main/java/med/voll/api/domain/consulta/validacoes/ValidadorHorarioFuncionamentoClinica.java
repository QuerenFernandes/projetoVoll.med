package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

//classe criada pada fazer a validação de agendamento do horário
public class ValidadorHorarioFuncionamentoClinica {

    //método para validar o horário recebido na solicitação de agendamento
    public void validar(DadosAgendamentoConsulta dados){
        //recuperação da data informada pelo usuário
        var dataConsulta = dados.data();


        //códigos para regra de negócio para verificar funcionamento da clínica.
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do funcionamento da clínica!");
        }
    }
}
