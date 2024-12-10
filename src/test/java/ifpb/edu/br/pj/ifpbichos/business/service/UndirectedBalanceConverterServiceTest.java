package ifpb.edu.br.pj.ifpbichos.business.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import ifpb.edu.br.pj.ifpbichos.business.service.converter.UndirectedBalanceConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import org.junit.jupiter.api.Test;

import ifpb.edu.br.pj.ifpbichos.presentation.dto.UndirectedBalanceDTO;


public class UndirectedBalanceConverterServiceTest {

    private final UndirectedBalanceConverterService converterService = new UndirectedBalanceConverterService();

    @Test
    public void testDtoToBalance() {

        BigDecimal balanceAmount = BigDecimal.valueOf(100);
        UndirectedBalanceDTO dto = new UndirectedBalanceDTO(balanceAmount);

        UndirectedBalance entity = converterService.dtoToBalance(dto);

        assertNotNull(entity);
        assertEquals(balanceAmount, entity.getBalance());
    }

    @Test
    public void testDtoToBalanceNullDto() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            converterService.dtoToBalance(null);
        });
        assertEquals("Não foi possível converter pois o objeto é nulo", thrown.getMessage());
    }

    @Test
    public void testBalanceToDto() {

        BigDecimal balanceAmount = BigDecimal.valueOf(100);
        UndirectedBalance entity = new UndirectedBalance();
        entity.setBalance(balanceAmount);

        UndirectedBalanceDTO dto = converterService.balanceToDto(entity);

        assertNotNull(dto);
        assertEquals(balanceAmount, dto.getBalance());
    }

    @Test
    public void testBalanceToDtoNullEntity() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            converterService.balanceToDto(null);
        });
        assertEquals("Não foi possível converter pois o objeto é nulo", thrown.getMessage());
    }
}

