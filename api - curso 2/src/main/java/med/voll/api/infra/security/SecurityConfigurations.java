package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//classe criada para concentrar todas as configurações de segurança.
//@configuration é para mostrar ao spring do que essa classe se trata
//@enablewebsecurity informa ao spring que vamos personalizar as configurações de segurança
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    //método padrão: csrf (desabilita ataques do tipo cross-site),
    // sessionManagement(mostra como será o gerenciamento da sessão),
    //sessionCreationPolicy: qual a política de criação da sessão, definimos aqui que é stateless,
    //build: cria o objeto
    //bean ensina como o spring cria um objeto
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    //método criado para ensinar o spring injetar os objetos
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    //método para ensinar o spring qual o tipo de hash de senha
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
