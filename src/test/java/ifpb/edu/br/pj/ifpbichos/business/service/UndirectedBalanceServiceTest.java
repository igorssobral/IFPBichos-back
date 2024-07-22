package ifpb.edu.br.pj.ifpbichos.business.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.model.repository.UndirectedBalanceRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

public class UndirectedBalanceServiceTest {

    @InjectMocks
    private UndirectedBalanceService undirectedBalanceService;

    @Mock
    private UndirectedBalanceRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() throws Exception {

        UndirectedBalance balance = new UndirectedBalance();
        balance.setBalance(BigDecimal.valueOf(100));
        when(repository.save(balance)).thenReturn(balance);

        UndirectedBalance savedBalance = undirectedBalanceService.save(balance);

        assertNotNull(savedBalance);
        assertEquals(BigDecimal.valueOf(100), savedBalance.getBalance());
        verify(repository).save(balance);
    }

    @Test
    public void testUpdate() throws Exception {

        UndirectedBalance balance = new UndirectedBalance();
        balance.setId(1L);
        balance.setBalance(BigDecimal.valueOf(100));

        BigDecimal additionalAmount = BigDecimal.valueOf(50);
        BigDecimal updatedAmount = BigDecimal.valueOf(150);

        when(repository.save(balance)).thenReturn(balance);

        UndirectedBalance updatedBalance = undirectedBalanceService.update(balance, additionalAmount);

        assertNotNull(updatedBalance);
        assertEquals(updatedAmount, updatedBalance.getBalance());
        verify(repository).save(balance);
    }

    @Test
    public void testDelete() throws Exception {

        UndirectedBalance balance = new UndirectedBalance();
        balance.setId(1L);

        when(repository.existsById(balance.getId())).thenReturn(true);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            undirectedBalanceService.delete(balance);
        });
        assertEquals("Não foi encontrado uma entidade saldo com id 1", exception.getMessage());

        verify(repository, never()).delete(balance);
    }

    @Test
    public void testFindOne() throws Exception {

        Long id = 1L;
        UndirectedBalance balance = new UndirectedBalance();
        balance.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(balance));

        UndirectedBalance foundBalance = undirectedBalanceService.findOne(id);

        assertNotNull(foundBalance);
        assertEquals(id, foundBalance.getId());
    }

    @Test
    public void testGetCurrentBalance() throws Exception {

        UndirectedBalance balance = new UndirectedBalance();
        balance.setBalance(BigDecimal.valueOf(100));

        when(repository.findFirstByOrderByIdAsc()).thenReturn(Optional.of(balance));

        BigDecimal currentBalance = undirectedBalanceService.getCurrentBalance();

        assertEquals(BigDecimal.valueOf(100), currentBalance);
    }

    @Test
    public void testGetCurrentBalanceEntity() throws Exception {

        UndirectedBalance balance = new UndirectedBalance();
        balance.setBalance(BigDecimal.ZERO);

        when(repository.findFirstByOrderByIdAsc()).thenReturn(Optional.empty());
        when(repository.save(any(UndirectedBalance.class))).thenReturn(balance);

        UndirectedBalance currentBalanceEntity = undirectedBalanceService.getCurrentBalanceEntity();

        assertNotNull(currentBalanceEntity);
        assertEquals(BigDecimal.ZERO, currentBalanceEntity.getBalance());
    }
}

