package mx.santander.fiduciarioplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.santander.fiduciarioplus.model.instruction.Instruction;

@Repository
public interface IInstructionRepository extends JpaRepository<Instruction, Long>{

	List<Instruction> findByBucAndIdBusinessAndIdSubBusiness(String buc, Integer business, Integer subBusiness);

	
}
