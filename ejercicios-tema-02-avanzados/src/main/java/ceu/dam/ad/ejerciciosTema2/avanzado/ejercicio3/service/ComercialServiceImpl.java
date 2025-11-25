package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Marca;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Pais;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Tienda;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.CentroComercialRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.MarcaRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.PaisRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.TiendaRepository;
import jakarta.transaction.Transactional;

@Service
public class ComercialServiceImpl implements ComercialService {
	@Autowired
	private PaisRepository paisRepo;
	@Autowired
	private MarcaRepository marcaRepo;
	@Autowired
	private CentroComercialRepository ccRepo;
	@Autowired
	private TiendaRepository tiendaRepo;

	@Override
	public List<Pais> buscarPaises(String filtro) throws ComercialException {
		try {
			return paisRepo.findByDescripcionStartingWith(filtro);
		} catch (DataAccessException e) {
			throw new ComercialException("Algo falló buscando paises con ese filtro", e);
		}
	}

	@Override
	public void insertarMarca(Marca marca) throws ComercialException {
		try {
			marcaRepo.save(marca);
		} catch (DataAccessException e) {
			throw new ComercialException("Algo falló insertando la marca " + marca, e);
		}
	}

	@Override
	@Transactional
	public void insertarCentroComercial(CentroComercial cc) throws ComercialException {
		try {
			if(!paisRepo.findById(cc.getPais().getCodigo()).isEmpty()) {
					ccRepo.save(cc);
			}
		} catch (DataAccessException e) {
			throw new ComercialException("Algo falló insertando el centro comercial " + cc, e);
		}
	}

	@Override
	public CentroComercial consultarCentroComercial(String uuidCentro) throws ComercialException, NotFoundException {
		try {
			return ccRepo.findById(UUID.fromString(uuidCentro))
					.orElseThrow(() -> new NotFoundException("No se ha encontrado un cc con el uuid " + uuidCentro));
		} catch (DataAccessException e) {
			throw new ComercialException("Algo falló insertando el centro comercial ", e);
		}
	}

	@Override
	public Tienda consularTienda(Long idTienda) throws ComercialException, NotFoundException {
		try {
			return tiendaRepo.findById(idTienda).orElseThrow(
					() -> new NotFoundException("No se ha encontrado ninguna tienda con el id " + idTienda));
		} catch (DataAccessException e) {
			throw new ComercialException("Algo falló insertando el centro comercial ", e);
		}
	}

	@Override
	public void borrarTienda(Long idTienda) throws ComercialException {
		try {
			tiendaRepo.deleteById(idTienda);
		} catch (DataAccessException e) {
			throw new ComercialException("Algo falló borrando la tienda con id " + idTienda, e);
		}

	}

	@Override
	@Transactional
	public void borrarCentroComercial(String uuidCentro) throws ComercialException {
		try {
			ccRepo.delete(consultarCentroComercial(uuidCentro));
			;
		} catch (NotFoundException e) {
			throw new ComercialException("Algo falló borrando el centro comercial con id " + uuidCentro, e);
		}

	}

}
