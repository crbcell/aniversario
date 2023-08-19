package com.ada.aniversario.dto;

import com.ada.aniversario.entity.Aniversariante;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AniversarianteInputDTO {
    private Long id;
    private String nome;
    private LocalDate data_nascimento;

    public AniversarianteInputDTO(Aniversariante aniversariante) {
    }

    public Aniversariante transformaParaObjeto(){
        return new Aniversariante(id, nome, data_nascimento);
    }

}
