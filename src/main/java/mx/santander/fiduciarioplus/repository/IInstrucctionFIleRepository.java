package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.santander.fiduciarioplus.model.sendinstruction.SendFIle;

public interface IInstrucctionFIleRepository extends JpaRepository<SendFIle, Long>{

}
