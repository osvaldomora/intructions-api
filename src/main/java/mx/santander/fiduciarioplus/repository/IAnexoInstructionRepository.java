package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.santander.fiduciarioplus.model.InstruccionAnexoModel;


@Repository
public interface IAnexoInstructionRepository extends JpaRepository<InstruccionAnexoModel, Long>{

}
