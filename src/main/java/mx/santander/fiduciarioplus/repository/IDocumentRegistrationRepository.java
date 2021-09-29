package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.santander.fiduciarioplus.model.RegistroDocumentoModel;

@Repository
public interface IDocumentRegistrationRepository extends JpaRepository<RegistroDocumentoModel, Long>{

}
