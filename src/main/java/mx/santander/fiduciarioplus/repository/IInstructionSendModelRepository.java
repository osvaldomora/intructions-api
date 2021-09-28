package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.santander.fiduciarioplus.model.InstruccionEnviadaModel;

public interface IInstructionSendModelRepository extends JpaRepository<InstruccionEnviadaModel, Long>{

}
