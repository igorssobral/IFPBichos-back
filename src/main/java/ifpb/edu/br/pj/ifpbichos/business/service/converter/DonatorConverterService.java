package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonatorDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonatorConverterService {
    public Donator dtoToDonator(DonatorDTO dto){
        if (dto != null) {
            Donator entity = new Donator(dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getRegistration(),dto.getDonatorType());
            return entity;
        }
        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public DonatorDTO donatorToDto(Donator entity) {
        if (entity != null) {
            DonatorDTO dto = new DonatorDTO(entity.getName(), entity.getPhoneNumber(), entity.getEmail(), entity.getRegistration(),entity.getDonatorType());
            return dto;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public List<Donator> dtosToDonators(List<DonatorDTO> dtoList) {

        if (dtoList != null) {
            List<Donator> entityList = new ArrayList<>();

            Donator entity = null;

            if (dtoList != null && !dtoList.isEmpty()) {
                for (DonatorDTO dto : dtoList) {
                    entity = dtoToDonator(dto);
                    entityList.add(entity);
                }
            }

            return entityList;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public List<DonatorDTO> donatorsToDtos(List<Donator> entityList) {
        if (entityList != null) {
            List<DonatorDTO> dtoList = new ArrayList<>();

            DonatorDTO dto = null;

            if (!entityList.isEmpty()) {
                for (Donator donator: entityList) {
                    dto = donatorToDto(donator);
                    dtoList.add(dto);
                }
            }

            return dtoList;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }
}
