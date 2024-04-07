package ifpb.edu.br.pj.ifpbichos.business.service;



import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonatorRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectAlreadyExistsException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DonatorService {

        @Autowired
        private DonatorRepository donatorRepository;


        public List<Donator> findAll() {
            return donatorRepository.findAll();
        }



        public Donator findById(Integer id) throws Exception {
            if (id == null) {
                throw new MissingFieldException("id");
            }else if(!donatorRepository.existsById(id)) {
                throw new ObjectNotFoundException("doador", "nome", id);
            }

            return donatorRepository.getReferenceById(id);
        }

        public Donator save(Donator donator) throws Exception {
            if(donatorRepository.existsByLogin(donator.getLogin())) {
                throw new ObjectAlreadyExistsException("Já existe um doador com esse login");
            }
            return donatorRepository.save(donator);
        }

        public Donator update(Donator donator) throws Exception {
            if(donator.getId() == null) {
                throw new MissingFieldException("id", "update");
            }else if(!donatorRepository.existsById(donator.getId())){
                throw new ObjectNotFoundException("doador", "id", donator.getId());
            }

            return donatorRepository.save(donator);
        }

        public void delete(Donator donator) throws Exception {
            if (donator.getId() == null) {
                throw new MissingFieldException("id", "delete");
            } else if (donatorRepository.existsById(donator.getId())) {
                throw new ObjectNotFoundException("doador", "id", donator.getId());
            }

            donatorRepository.delete(donator);
        }

        public void deleteById(String cpf) throws Exception {

            donatorRepository.deleteById(Integer.valueOf(cpf));
        }
}
