package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.santander.fiduciarioplus.model.typeinstruction.Instruccion;

public interface IInstructionRepository extends JpaRepository<Instruccion, Long> {
	
	

}
