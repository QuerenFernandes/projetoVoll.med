package med.voll.api.infra;
//ESSA CLASSE EXCLUI A NECESSIDADE DE TER UM TRY CATCH DENTRO DAS CLASSES ISOLANDO A FUNCIONALIDADE

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//a anotação informa pro spring que essa classe é de tratamento de erros
@RestControllerAdvice
public class TratadorDeErros {
    //anotação usada para informar que quando tiver essa exception esse método deverá ser chamado
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    //anotação usada para retornar o erro quando os campos informados forem inválidos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    //dto criado dentro da classe de erro porque ela está no contexto do erro e não vai ser usada em outro local.
    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
