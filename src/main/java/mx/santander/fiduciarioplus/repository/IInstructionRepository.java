package mx.santander.fiduciarioplus.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.santander.fiduciarioplus.model.instruction.Instruction;

@Repository
public interface IInstructionRepository extends JpaRepository<Instruction, Long>{
	

	List<Instruction> findByBucAndIdBusinessAndIdSubBusiness(String buc, Integer business, Integer subBusiness);

	@Query( value = "SELECT * FROM INSTRUCTION u WHERE u.buc = ?1", nativeQuery = true) 
	List<Instruction> findByBuc(String buc);
	
	@Query( value = "SELECT * FROM INSTRUCTION u WHERE u.buc = ?1 AND ID_BUSINESS = ?2 AND ID_SUB_BUSINESS = ?3", nativeQuery = true) 
	List<Instruction> findStatusByBucAndIdBusinessAndIdSubBusiness(String buc, Integer business, Integer subBusiness);

	
	
}
