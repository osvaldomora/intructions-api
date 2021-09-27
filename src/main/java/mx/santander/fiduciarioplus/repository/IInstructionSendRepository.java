package mx.santander.fiduciarioplus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.santander.fiduciarioplus.model.InstruccionEnviada;


/**
 * Esta interface es el repositorio que permite realizar consultar a la BD, de acuerdo al
 * modelo establecido: InstruccionEnviada
 */
@Repository
public interface IInstructionSendRepository extends JpaRepository<InstruccionEnviada, Long>{


	List<InstruccionEnviada> findByIdFkBucAndIdNoContrAndIdNoSubContrAndFchRegisInsctAfter(String idBuc, Long idNoContr, Long idNoSubContr, Date dateAfter);
	
	@Query(value = "SELECT * FROM FID_MX_REL_INSTR_NVAS i WHERE i.FK_ID_BUC = ?1 AND i.FCH_REGIS_INSCT >= ?2 AND i.FCH_REGIS_INSCT <= ?3", nativeQuery = true)
	List<InstruccionEnviada> findByBucAndDateInstructionBetweenDates(String buc, Date dateStart, Date dateEnd);
	
	@Query(value = "SELECT * FROM FID_MX_REL_INSTR_NVAS i WHERE i.FK_ID_BUC = ?1 AND i.ID_NO_CONTR = ?2 AND i.ID_NO_SUBCONTR = ?3 AND i.FCH_REGIS_INSCT >= ?4 AND i.FCH_REGIS_INSCT <= ?5", nativeQuery = true)
	List<InstruccionEnviada> findStatusByBucAndIdBusinessAndIdSubBusiness(String buc, Long business, Long subBusiness, Date dateStart, Date dateEnd);
}
