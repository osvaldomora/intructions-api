package mx.santander.fiduciarioplus.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.santander.fiduciarioplus.dto.tipoinstruccion.Archivo;
import mx.santander.fiduciarioplus.dto.tipoinstruccion.Datos;
import mx.santander.fiduciarioplus.dto.tipoinstruccion.InstruccionDto;
import mx.santander.fiduciarioplus.dto.tipoinstruccion.TipoInstruccion;
import mx.santander.fiduciarioplus.model.tipoinstruccion.Instruccion;
import mx.santander.fiduciarioplus.repository.IInstruccionRepository;
@Service
public class InstruccionService implements IInstruccionService {
	
	private InstruccionDto  instDto= new InstruccionDto();

	
	@Autowired
	IInstruccionRepository instruccionRepository;
	public static final ModelMapper MODELMAPPER = new ModelMapper();

	@Override
	public InstruccionDto getInstrucciones() {

		List<Instruccion> instruccionEntity = instruccionRepository.findAll();
		List<TipoInstruccion> li=instruccionEntity.stream().map((insEntity)-> {
			System.out.println(insEntity.getName());
			
				
			TipoInstruccion tipoInstruccion=MODELMAPPER.map(insEntity, TipoInstruccion.class);		
			Archivo archivo=MODELMAPPER.map(insEntity, Archivo.class);
			tipoInstruccion.setArchivo(Arrays.asList(archivo));

			return tipoInstruccion;	
		}).collect(Collectors.toList());
		
//		instDto.setResultado(new Resultado(200,"ok"));	
		Datos dato = new Datos(li);
		instDto.setDatos(dato);

		return instDto;
	}
	


}
