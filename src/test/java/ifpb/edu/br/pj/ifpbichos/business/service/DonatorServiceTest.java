package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonatorRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonationHistoryDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectAlreadyExistsException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DonatorServiceTest {

	 @Mock
	 private DonatorRepository repository;

	@Mock
	private UserRepository userRepository;

	 @InjectMocks
	 private DonatorService service;

	 @BeforeEach
	 public void setup() {
		 MockitoAnnotations.openMocks(this);
	     service = new DonatorService();
	     ReflectionTestUtils.setField(service, "donatorRepository", repository);

	     Donator user = new Donator();
	     user.setId(1L);
	     Donator user02 = new Donator();
	     user02.setId(2L);
	     repository.save(user);
	     repository.save(user02);
	 }

	 @BeforeEach
 	 public void beforeEach() {
         MockitoAnnotations.openMocks(this);

       	 Donator user = new Donator();
       	 user.setId(1L);
       	 Donator user02 = new Donator();
       	 user02.setId(2L);
       	 repository.save(user);
         repository.save(user02);
	}



     @DisplayName("Id Valid")
     @Test
     public void testFindByIdObjectValid() {

         when(repository.existsById(anyLong())).thenReturn(true);

         assertDoesNotThrow(() -> service.findById(3L));
         verify(repository).getReferenceById(3L);
     }

	@Test
	public void testGetDonationsByUser() throws ObjectNotFoundException {
		// Arrange
		String login = "userTest@gmail.com";
		Donator donator = new Donator();
		Donation donation1 = new Donation();
		donation1.setDate(LocalDateTime.of(2024, 8, 11, 10, 30));
		Donation donation2 = new Donation();
		donation2.setDate(LocalDateTime.of(2024, 8, 10, 10, 45));
		donator.setDonations(Arrays.asList(donation1, donation2));
		when(userRepository.findByLogin(login)).thenReturn(donator);

		List<DonationHistoryDTO> result = service.getDonationsByUser(login);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.get(0) instanceof DonationHistoryDTO);
	}

	@Test
	public void testGetDonationsByUserWhenUserDoesNotExist() {
		String login = "nonExistentUser";
		when(userRepository.findByLogin(login)).thenReturn(null);

		Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.getDonationsByUser(login);
		});
		assertEquals("Usuario não encontrado", exception.getMessage()); // Ajuste a mensagem conforme necessário
	}

}
