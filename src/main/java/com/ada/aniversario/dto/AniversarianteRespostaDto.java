package com.ada.aniversario.dto;

import com.ada.aniversario.entity.Aniversariante;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class AniversarianteRespostaDto {
     Long id;
     String nome;
     LocalDate data_nascimento;

     public static AniversarianteRespostaDto transformaEmDTO(Aniversariante aniversariante) {
          return new AniversarianteRespostaDto(aniversariante.getId(), aniversariante.getNome(), aniversariante.getData_nascimento());
     }

}
