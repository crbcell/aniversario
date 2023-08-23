package com.ada.aniversario.repository;

import com.ada.aniversario.entity.Aniversariante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AniversarianteRepository extends JpaRepository<Aniversariante, Long> {
    //@Query("select c from tb_aniversariantes c where c.nome like'%:c%'")
    //List<Aniversariante> findByPart(@Param("c") int c);

    List<Aniversariante> findByNomeContaining(String nome);
   /* @Query(value = "SELECT * FROM tb_aniversariantes WHERE data_nascimento > ?1 AND data_nascimento <= ?2", nativeQuery = true)
    List<Statement> findAllByDate(LocalDate startDate, LocalDate endDate);*/

}

