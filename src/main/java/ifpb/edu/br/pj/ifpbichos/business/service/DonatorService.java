package ifpb.edu.br.pj.ifpbichos.business.service;



import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonatorRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
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
            }

            return donatorRepository.getReferenceById(id);
        }

        public Donator save(Donator comissionMember) throws Exception {

            return donatorRepository.save(comissionMember);
        }

        public Donator update(Donator donator) throws Exception {

            return donatorRepository.save(donator);
        }

        public void delete(Donator donator) throws Exception {

            donatorRepository.delete(donator);
        }

        public void deleteById(String cpf) throws Exception {

            donatorRepository.deleteById(Integer.valueOf(cpf));
        }
}
