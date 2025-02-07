package ifpb.edu.br.pj.ifpbichos.business.service;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.model.repository.UndirectedBalanceRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UndirectedBalanceService {

    @Autowired
    private UndirectedBalanceRepository repository;

    public UndirectedBalance save(UndirectedBalance bank) throws Exception {

        return repository.save(bank);
    }

    public UndirectedBalance update(UndirectedBalance bank, BigDecimal newBalance) throws Exception {
        BigDecimal currentBalance = bank.getBalance();
        BigDecimal updatedBalance = currentBalance.add(newBalance);
        bank.setBalance(updatedBalance);

        return repository.save(bank);
    }

    public void delete(UndirectedBalance bank) throws Exception {
        if (bank.getId() == null) {
            throw new MissingFieldException("id", "delete");
        } else if (repository.existsById(bank.getId())) {
            throw new ObjectNotFoundException("uma entidade saldo", "id", bank.getId());
        }

        repository.delete(bank);
    }

    public UndirectedBalance findOne(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("uma entidade saldo", "id", id));
    }

    public BigDecimal getCurrentBalance() throws Exception {
        UndirectedBalance undirectedBalance = getCurrentBalanceEntity();
        return undirectedBalance.getBalance();
    }

    public UndirectedBalance getCurrentBalanceEntity() throws Exception {
        return repository.findFirstByOrderByIdAsc().orElseGet(() -> {
            UndirectedBalance newBalance = new UndirectedBalance();
            newBalance.setBalance(BigDecimal.ZERO);
            try {
                return repository.save(newBalance);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create initial undirected balance", e);
            }
        });
    }
}
