package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.UndirectedBalanceService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.UndirectedBalanceConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UndirectedBalanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance")
public class UndirectedBalanceController {

    @Autowired
    private UndirectedBalanceService service;
    @Autowired
    private UndirectedBalanceConverterService converter;


    private final UndirectedBalanceService undirectedBalanceService;

    @Autowired
    public UndirectedBalanceController(UndirectedBalanceService undirectedBalanceService) {
        this.undirectedBalanceService = undirectedBalanceService;
    }

    @GetMapping
    public ResponseEntity<?> getBalance() {

        try {
            UndirectedBalance balance = undirectedBalanceService.getCurrentBalanceEntity();
            UndirectedBalanceDTO dto = converter.balanceToDto(balance);
            return ResponseEntity.ok().body(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
