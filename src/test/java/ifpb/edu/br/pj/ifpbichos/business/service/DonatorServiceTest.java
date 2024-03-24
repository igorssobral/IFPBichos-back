package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonatorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DonatorServiceTest {

    @Mock
    private static DonatorRepository repository;

    @InjectMocks
    private static Donator donatorExp;
    private static DonatorService service;
    @BeforeAll
    public static void setup() {
        service = new DonatorService();
        donatorExp = new Donator();

        ReflectionTestUtils.setField(service, "donatorRepository", repository);

    }

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);

        Donator user = new Donator();
        user.setId(1);
        Donator user02 = new Donator();
        user02.setId(2);
        repository.save(user);
        repository.save(user02);
    }



    @DisplayName("Id Valid")
    @Test
    public void testFindByIdObjectValid() {

        when(repository.existsById(anyInt())).thenReturn(true);

        assertDoesNotThrow(() -> service.findById(2));
        verify(repository).getById(2);
    }

}
