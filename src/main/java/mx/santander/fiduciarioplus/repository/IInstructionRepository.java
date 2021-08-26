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

	/*SELECT BUC, ID_BUSINESS, ID_SUB_BUSINESS, STATUS_INSTRUCTION, COUNT(STATUS_INSTRUCTION) AS STATUS FROM INSTRUCTION WHERE BUC = '980714019292' OR (ID_BUSINESS = '1' OR ID_SUB_BUSINESS = '1' )  GROUP BY STATUS_INSTRUCTION, ID_BUSINESS, ID_SUB_BUSINESS*/
	
	
	@Query( value = "SELECT * FROM INSTRUCTION u WHERE u.buc = ?1", nativeQuery = true) 
	/*@Query(value = "SELECT U.BUC, U.ID_BUSINESS, U.ID_SUB_BUSINESS, U.STATUS_INSTRUCTION,"
			+ " COUNT(U.STATUS_INSTRUCTION) FROM INSTRUCTION U WHERE U.BUC=:buc"
			+ " GROUP BY U.STATUS_INSTRUCTION, U.ID_BUSINESS, U.ID_SUB_BUSINESS", nativeQuery=true)*/
	List<Instruction> findByBuc(String buc);
	
	/*@Query(value = "SELECT U.BUC, U.ID_BUSINESS, U.ID_SUB_BUSINESS, U.STATUS_INSTRUCTION,"
			+ " COUNT(U.STATUS_INSTRUCTION) FROM INSTRUCTION U WHERE U.BUC=:buc"
			+ " GROUP BY U.STATUS_INSTRUCTION, U.ID_BUSINESS, U.ID_SUB_BUSINESS", nativeQuery=true)*/
	//@Modifying
	//@Transactional
	//List<Instruction> findStatusByBucAndIdBusinessAndIdSubBusiness(String buc);
	//@Query(value="SELECT BUC, ID_BUSINESS, ID_SUB_BUSINESS, STATUS_INSTRUCTION, COUNT(STATUS_INSTRUCTION) AS STATUS FROM INSTRUCTION WHERE BUC = '980714019292' GROUP BY STATUS_INSTRUCTION, ID_BUSINESS, ID_SUB_BUSINESS",nativeQuery = true)
	//@Modifying
	//@Transactional
	//List<Instruction> findByBuc(@Param("buc")String buc);
	
	
    
	
	
	
	
	
}
