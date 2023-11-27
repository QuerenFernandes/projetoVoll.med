package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //anotação usada para informar ao spring que essa variável deve ler as informações do arquivo de config
    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(Usuario usuario) {
        System.out.println(secret);
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    //"dono do token
                    .withIssuer("API Voll.med")
                    //pessoa relacionada com o token
                    .withSubject(usuario.getLogin())
                    //método para definir o tempo de expiração
                    .withExpiresAt(dataExpiracao())
                    //guarda o id do usuário
                    .withClaim("id", usuario.getId())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerrar token jwt", exception);

        }
    }

    //método criado para validar o token para que o mesmo expire após 2h
    private Instant dataExpiracao() {
        return LocalDateTime
                .now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

