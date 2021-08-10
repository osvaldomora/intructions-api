package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.santander.fiduciarioplus.model.sendinstruction.Instruction;

@Repository
public interface IInstructionRepository extends JpaRepository<Instruction, Long>{

}