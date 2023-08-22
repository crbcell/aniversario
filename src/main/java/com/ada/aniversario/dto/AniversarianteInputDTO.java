package com.ada.aniversario.dto;

import com.ada.aniversario.entity.Aniversariante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class AniversarianteInputDTO {
    private Long id;
    @NotNull(message = "Não pode ser nulo o id")
    @Size(min=3, max = 50)
    private String nome;
    @NotNull(message = "Não pode ser nulo a data de nascimento")
    private LocalDate data_nascimento;

    public AniversarianteInputDTO(Aniversariante aniversariante) {
    }

    public Aniversariante transformaParaObjeto() {
        return new Aniversariante(id, nome, data_nascimento);
    }

}
