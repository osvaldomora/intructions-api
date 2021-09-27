package mx.santander.fiduciarioplus.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.santander.fiduciarioplus.dto.validateInstruction.DataDto;
import mx.santander.fiduciarioplus.dto.validateInstruction.DataValidationInstructionResDto;
import mx.santander.fiduciarioplus.exception.BusinessException;
import mx.santander.fiduciarioplus.exception.InvalidDataException;
import mx.santander.fiduciarioplus.exception.catalog.BusinessCatalog;
import mx.santander.fiduciarioplus.exception.catalog.InvalidDataCatalog;

@Service
public class ValidationIntstructionService implements IValidationIntstructionService {

	private final Long MAX_SIZE_FILE_BYTES = 15728640L;
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public DataValidationInstructionResDto validate(List<MultipartFile> files) {
		LOG.info("ValidationIntstructionService.DataSendInstructionResDto");

		// SE VA A LLAMAR EN EL SERVICIO
		if (files.size() > 1 || files.isEmpty()) { // Se han enviado mas doumentos de los esperados
			LOG.warn("WARN: BUSI.003, Descripcion: Se han enviado mas documentos de los esperados.");
			throw new BusinessException(BusinessCatalog.BUSI003, "Se han enviado mas documentos de los esperados.");
		}
		MultipartFile file = files.get(0);

		if (!file.getContentType().equalsIgnoreCase("text/plain")) { // Valida tipo de archivo, disponible solo PDF
			LOG.warn("WARN: BUSI.002, FORMATO ARCHIVO: " + file.getContentType());
			throw new BusinessException(BusinessCatalog.BUSI002, "Formato aceptado: txt");
		}
		if (file.getSize() > MAX_SIZE_FILE_BYTES) { // Valida tamanio de archivo, tiene que se menor a 15MB
			LOG.warn("WARN: BUSI.001, TAMAÑO ARCHIVO: " + file.getSize());
			throw new BusinessException(BusinessCatalog.BUSI001, "El archivo ha superado el limite de MB.");
		}
		if (file.getSize() == 0) { // Valida que el archivo no este vacio
			LOG.warn("WARD: BUSI.001, TAMAÑO ARCHIVO: " + file.getSize());
			throw new BusinessException(BusinessCatalog.BUSI001, "El archivo no puede estar vacio.");
		}

		DataValidationInstructionResDto dataResponse = validateFile(file);

		return dataResponse;
	}

	private DataValidationInstructionResDto validateFile(MultipartFile file) {
		LOG.info("ValidationIntstructionService.validateFile");

		// Definición de variables locales
		BufferedReader br;
		List<String> result = new ArrayList<>();
		int i = 1,  f1 = 0, f2 = 0;
		String line = "";
		Boolean band = false;

		try {

			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {

				result = Arrays.asList(line.split("\\|"));// line.split("\\|")
				if (i == 1) {
					f1 = result.size();
				}

				if (i == 2) {
					f2 = result.size();
					System.out.println("f2:" + f2);

					if (f1 < f2)// fila 2 mayor fila 1
					{
						band = true;
					}
				}
				if (i >= 3) {
					if (band) {
						if (result.size() > f2 || line.isEmpty()) {
							LOG.info("FORMATO INCORRECTO, Linea:{}", i);
							throw new BusinessException(BusinessCatalog.BUSI004, "Estructura de archivo no valido.");
						}
					} else if (result.size() > f1 || line.isEmpty()) {
						LOG.info("FORMATO INCORRECTO, Linea:{}", i);
						throw new BusinessException(BusinessCatalog.BUSI004, "Estructura de archivo no valido.");

					}
				}

				i++;
			}

		} catch (IOException e) {
			throw new InvalidDataException(InvalidDataCatalog.INVD001, "Error al leer el archivo");
		}

		return DataValidationInstructionResDto.builder().data(DataDto.builder().validation(true).build()).build();

	}

}
