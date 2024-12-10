package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonatorDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UndirectedBalanceDTO;
import org.springframework.stereotype.Service;

@Service
public class UndirectedBalanceConverterService {

    public UndirectedBalance dtoToBalance(UndirectedBalanceDTO dto){
        if (dto != null) {
            UndirectedBalance entity = new UndirectedBalance(dto.getBalance());
            return entity;
        }
        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public UndirectedBalanceDTO balanceToDto(UndirectedBalance entity) {
        if (entity != null) {
            UndirectedBalanceDTO dto = new UndirectedBalanceDTO(entity.getBalance());
            return dto;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }
}
