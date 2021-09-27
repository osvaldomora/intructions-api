package mx.santander.fiduciarioplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.santander.fiduciarioplus.model.TipoInstruccionModel;


/***
 * Esta interface es el repositorio que permite realizar consultar a la BD, de acuerdo al
 * modelo establecido: TipoInstruccionModel
 *
 */
@Repository
public interface ITypeInstructionRepository extends JpaRepository<TipoInstruccionModel, Long>{

}
