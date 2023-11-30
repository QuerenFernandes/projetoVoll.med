package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    //como o nome do método não está no padrão em inglês do spring precisamos montar a querie de pesquisa
    // a anotação indica o uso da querie.

    @Query("""
                select m from Medico m
                       where
                       m.ativo = true
                       and
                       m.especialidade = :especialidade
                       and
                       m.id not in(
                           select c.idMedico.id from Consulta c
                           where
                           c.data = :data
                       )
                       order by rand()
                       limit 1
                       
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);


    @Query("""
    select m.ativo
    from Medico m
            where
    m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);
}
