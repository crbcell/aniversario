package com.ada.aniversario.service;

import com.ada.aniversario.dto.AniversarianteInputDTO;
import com.ada.aniversario.entity.Aniversariante;
import com.ada.aniversario.exception.AniversarianteNaoEncontradoException;
import com.ada.aniversario.repository.AniversarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
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

    /*public static AniversarianteOutputDto transformaEmDTO(Aniversariante aniversariante) {
        return new AniversarianteOutputDto(aniversariante.getId(), aniversariante.getNome(), aniversariante.getData_nascimento());
    }*/

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

    public List<AniversarianteInputDTO> searchToPartOfName(String nome) {
        return aniversarianteRepository
                .findByNomeContaining(nome)
                .stream()
                .map(this::convertAniversarianteDto)
                .collect(Collectors.toList());
    }

    public List<AniversarianteInputDTO> birthdayList() {
        return aniversarianteRepository.findAll().stream().map(AniversarianteInputDTO::new).collect(Collectors.toList());
        //exemplo de metodo que nao funciona sem convertAniversarianteDto
    }

    public Aniversariante searchById(Long id) {
        return aniversarianteRepository.findById(id).get();
    }

    public Aniversariante save(Aniversariante aniversariante) {
        return aniversarianteRepository.save(aniversariante);
    }

    public int calculateAge(LocalDate nascimento) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(nascimento, currentDate).getYears();
    }

    public boolean isLeapYear(LocalDate nascimento) {
        return nascimento.isLeapYear();//true or false
    }

    /*private String getMapMapString(String key, Long id) throws JsonProcessingException {
        Map<String, Object> object = new HashMap<>();
        object.put(key, this
                .calculateAge(searchById(id)
                        .getData_nascimento()));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }*/

}
