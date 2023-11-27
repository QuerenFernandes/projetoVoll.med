package med.voll.api.domain.usuario;
//classe que contém a lógica de autenticação do usuário

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//anotação para que o spring reconheça que a classe se trata de um serviço e reconheça a mesma
@Service
public class AutenticacaoService implements UserDetailsService{
    //injeção de dependências
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
