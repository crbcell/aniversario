package com.ada.aniversario.controller;

import com.ada.aniversario.dto.AniversarianteInputDTO;
import com.ada.aniversario.dto.AniversarianteRespostaDto;
import com.ada.aniversario.entity.Aniversariante;
import com.ada.aniversario.service.AniversarianteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/aniversario")
@RestController
public class AniversarianteController {

    private final AniversarianteService aniversarianteService;

    @Autowired
    public AniversarianteController(AniversarianteService aniversarianteService) {
        this.aniversarianteService = aniversarianteService;
    }

    @Operation(summary = "Lista completa sem ppe", description = "Endpoint para consultar lista o campo Pessoa Politicamente Exposta ")
    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AniversarianteInputDTO> getAllAniversariantes() {
        return aniversarianteService.getAllAniversariantes();
    }

    @Operation(summary = "Cadastrar aniversariante", description = "Endpoint para cadastrar um aniversariante")
    @PostMapping(value = "/add")
    public ResponseEntity<AniversarianteRespostaDto> salvar(@RequestBody AniversarianteInputDTO dto) {
        Aniversariante aniversariante = aniversarianteService.salvar(dto.transformaParaObjeto());
        return new ResponseEntity<>(AniversarianteRespostaDto.transformaEmDTO(aniversariante), CREATED);
    }
    @Operation(summary = "Consultar id COM ppe", description = "Endpoint para consultar id com o campo Pessoa Politicamente Exposta ")
    @GetMapping("/{id}")
    public ResponseEntity<Aniversariante> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.aniversarianteService.buscarPorId(id));
    }
    @Operation(summary = "Consultar id SEM ppe", description = "Endpoint para consultar id sem o campo Pessoa Politicamente Exposta ")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AniversarianteInputDTO> getAniversarianteById(@PathVariable Long id) {
        return aniversarianteService.findById(id);
    }

    @Operation(summary = "Consultar por parte do nome", description = "Endpoint para consultar aniversariante por parte do nome")
    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AniversarianteInputDTO> getAniversarianteByNamePart(@PathVariable String nome) {
        return aniversarianteService.buscaPorParteDoNome(nome);
    }

    @Operation(summary = "Consultar idade", description = "Endpoint para consultar a idade a partir de um id")
    @GetMapping(value = "/idade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String idadeDaPessoa(@PathVariable Long id) throws JsonProcessingException {
        Map<String, Object> object = new HashMap<>();
        object.put("idade", this.aniversarianteService
                .calculateAge(aniversarianteService
                        .buscarPorId(id)
                        .getData_nascimento()));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Operation(summary = "Verificar ano bissexto", description = "Endpoint para consultar se o ano de nascimento é bissexto")
    @GetMapping(value = "/ano/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String anoBissexto(@PathVariable Long id) throws JsonProcessingException {
        Map<String, Object> object = new HashMap<>();
        object.put("bissexto", this.aniversarianteService.isLeapYear(aniversarianteService
                .buscarPorId(id)
                .getData_nascimento()));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /* @GetMapping(value = "/idade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> idadeDaPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(this.aniversarianteService.calculateAge(aniversarianteService.buscarPorId(id).getData_nascimento()));
    }*/

    /*@GetMapping(value = "/idade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> idadeDaPessoa(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Integer> entity = new ResponseEntity<>(this.aniversarianteService.calculateAge(aniversarianteService.buscarPorId(id).getData_nascimento()), headers, HttpStatus.CREATED);
        return entity;
    }*/

}
