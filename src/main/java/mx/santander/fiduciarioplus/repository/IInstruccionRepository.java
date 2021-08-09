package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.santander.fiduciarioplus.model.tipoinstruccion.Instruccion;

public interface IInstruccionRepository extends JpaRepository<Instruccion, Long> {
	
	

}
