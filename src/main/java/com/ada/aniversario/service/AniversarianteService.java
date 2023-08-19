package com.ada.aniversario.service;

import com.ada.aniversario.dto.AniversarianteInputDTO;
import com.ada.aniversario.dto.AniversarianteRespostaDto;
import com.ada.aniversario.entity.Aniversariante;
import com.ada.aniversario.exception.AniversarianteNaoEncontradoException;
import com.ada.aniversario.repository.AniversarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class AniversarianteService {

    private final AniversarianteRepository aniversarianteRepository;

    @Autowired
    public AniversarianteService(AniversarianteRepository aniversarianteRepository) {
        this.aniversarianteRepository = aniversarianteRepository;
    }

    public static AniversarianteRespostaDto transformaEmDTO(Aniversariante aniversariante) {
        return new AniversarianteRespostaDto(aniversariante.getId(), aniversariante.getNome(), aniversariante.getData_nascimento());
    }

    private AniversarianteInputDTO convertAniversarianteDto(Aniversariante aniversariante) {
        AniversarianteInputDTO aniversarianteInputDTO = new AniversarianteInputDTO();
        aniversarianteInputDTO.setId((aniversariante.getId()));
        aniversarianteInputDTO.setNome((aniversariante.getNome()));
        aniversarianteInputDTO.setData_nascimento((aniversariante.getData_nascimento()));
        return aniversarianteInputDTO;
    }


    public List<AniversarianteInputDTO> getAllAniversariantes() {
        return aniversarianteRepository
                .findAll()
                .stream()
                .map(this::convertAniversarianteDto)
                .collect(Collectors.toList());
    }

    public List<AniversarianteInputDTO> findById(Long id) {
        Optional<Aniversariante> niverOptional = aniversarianteRepository.findById(id);
        if (niverOptional.isEmpty()) {
            throw new AniversarianteNaoEncontradoException("Aniversariante não encontrado");
        }
        return aniversarianteRepository
                .findById(id)
                .stream()
                .map(this::convertAniversarianteDto)
                .collect(Collectors.toList());
    }

    public List<AniversarianteInputDTO> buscaPorParteDoNome(String nome) {
        return aniversarianteRepository
                .findByNomeContaining(nome)
                .stream()
                .map(this::convertAniversarianteDto)
                .collect(Collectors.toList());
    }

    public List<AniversarianteInputDTO> listaAniversariantes() {
        return aniversarianteRepository.findAll().stream().map(AniversarianteInputDTO::new).collect(Collectors.toList());
        //exemplo de metodo que nao funciona sem convertAniversarianteDto
    }

    public Aniversariante buscarPorId(Long id) {
        return aniversarianteRepository.findById(id).get();
    }

    public Aniversariante salvar(Aniversariante aniversariante) {
        return aniversarianteRepository.save(aniversariante);
    }

    public int calculateAge(LocalDate nascimento) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(nascimento, currentDate).getYears();
    }

    public boolean isLeapYear(LocalDate nascimento){
        boolean isLeapYear = nascimento.isLeapYear();  //true or false
        return isLeapYear;
    }

}
