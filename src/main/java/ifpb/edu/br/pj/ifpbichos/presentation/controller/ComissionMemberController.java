package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.ComissionMemberService;
import ifpb.edu.br.pj.ifpbichos.business.service.DonationService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.ComissionMemberConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ComissionMemberDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonationHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comissionMember")
public class ComissionMemberController {

    @Autowired
    private ComissionMemberService comissionMemberService;

    @Autowired
    private ComissionMemberConverterService converterService;
    @Autowired
    private DonationService donationService;

    @GetMapping
    public ResponseEntity getAll() {
        List<ComissionMember> entityList = comissionMemberService.findAll();

        List<ComissionMemberDTO> dtoList = converterService.comissionMembersToDtos(entityList);

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/manual-donations")
    public ResponseEntity getAllDonationsManual() {
        List<DonationDTO> entityList = donationService.findAllDonationsManual();


        return ResponseEntity.ok().body(entityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {

        try {
            ComissionMember entity = comissionMemberService.findById(id);
            ComissionMemberDTO dto = converterService.comissionMemberToDto(entity);

            return ResponseEntity.ok().body(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ComissionMemberDTO dto) {

        try {
            ComissionMember entity = converterService.dtoToComissionMember(dto);
            entity = comissionMemberService.save(entity);
            dto = converterService.comissionMemberToDto(entity);

            return new ResponseEntity(dto, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String cpf, @RequestBody ComissionMemberDTO dto) {

        try {
            dto.setCPF(cpf);
            ComissionMember entity = converterService.dtoToComissionMember(dto);
            entity = comissionMemberService.update(entity);
            dto = converterService.comissionMemberToDto(entity);

            return ResponseEntity.ok().body(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            comissionMemberService.deleteById(id);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
