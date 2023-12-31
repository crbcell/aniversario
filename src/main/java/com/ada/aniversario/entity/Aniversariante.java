package com.ada.aniversario.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_aniversariantes")
public class Aniversariante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate data_nascimento;
    private boolean ppe = false;
    //Criado o campo ppe (pessoa politicamente exposta) para fazer sentido o uso do DTO para ser ocultado.


    public Aniversariante(Long id, String nome, LocalDate data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
    }
}
