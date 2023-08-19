package com.ada.aniversario.dto;

import com.ada.aniversario.entity.Aniversariante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class AniversarianteInputDTO {
    private Long id;
    private String nome;
    private LocalDate data_nascimento;

    public AniversarianteInputDTO(Aniversariante aniversariante) {
    }

    public Aniversariante transformaParaObjeto() {
        return new Aniversariante(id, nome, data_nascimento);
    }

}
