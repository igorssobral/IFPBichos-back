package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.DonatorService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.DonatorConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donator")
public class DonatorController {

    @Autowired
    private DonatorService donatorService;

    @Autowired
    private DonatorConverterService converterService;

    @GetMapping
    public ResponseEntity getAll() {
        List<Donator> entityList = donatorService.findAll();

        List<DonatorDTO> dtoList = converterService.donatorsToDtos(entityList);

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {

        try {
            Donator entity = donatorService.findById(id);
            DonatorDTO dto = converterService.donatorToDto(entity);

            return ResponseEntity.ok().body(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DonatorDTO dto) {

        try {
            Donator entity = converterService.dtoToDonator(dto);
            entity = donatorService.save(entity);
            dto = converterService.donatorToDto(entity);

            return new ResponseEntity(dto, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String cpf, @RequestBody DonatorDTO dto) {

        try {
            dto.setRegistration(cpf);
            Donator entity = converterService.dtoToDonator(dto);
            entity = donatorService.update(entity);
            dto = converterService.donatorToDto(entity);

            return ResponseEntity.ok().body(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String cpf) {

        try {
            donatorService.deleteById(cpf);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
