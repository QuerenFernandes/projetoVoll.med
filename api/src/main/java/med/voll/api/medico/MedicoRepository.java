package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {

    //método criado para alterar a lógica de consulta de médicos que estão ativos.
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
