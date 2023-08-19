package com.ada.aniversario.dto;

import com.ada.aniversario.entity.Aniversariante;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class AniversarianteOutputDto {
     Long id;
     String nome;
     LocalDate data_nascimento;

     public static AniversarianteOutputDto transformaEmDTO(Aniversariante aniversariante) {
          return new AniversarianteOutputDto(aniversariante.getId(), aniversariante.getNome(), aniversariante.getData_nascimento());
     }

}
