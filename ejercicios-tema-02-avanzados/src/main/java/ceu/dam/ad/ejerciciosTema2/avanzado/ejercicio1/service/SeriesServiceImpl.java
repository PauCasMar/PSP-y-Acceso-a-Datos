package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Serie;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository.SerieRepository;
import jakarta.transaction.Transactional;

@Service
public class SeriesServiceImpl implements SeriesService {
	
	@Autowired
	private SerieRepository repoSerie;
	
	@Override
	public Serie consultarSerie(Long idSerie) throws SerieNotFoundException, SeriesServiceException {
		try {
			//La estructura de orElseThrow devuelve directamente objeto Serie o error. No manda optional, te ahorras el .get() para sacar el objeto del optional
		return repoSerie.findById(idSerie).orElseThrow(()->new SerieNotFoundException("No existe esa serie con la id " + idSerie));
		}catch(DataAccessException e) {
			throw new SeriesServiceException("Hubo algún problema buscando la serie", e); 
		}
		
	}
		
	@Override
	public List<Serie> buscarSeries(String filtroDescripcion) throws SerieNotFoundException, SeriesServiceException {
		try{
			List<Serie> seriesFiltradas = repoSerie.findByDescripcionContaining(filtroDescripcion);	
			if (seriesFiltradas.isEmpty()) {
				throw new SerieNotFoundException("No había series con esa descripción");
			}
			return seriesFiltradas; 	
			
		}catch (DataAccessException e) {
			throw new SeriesServiceException ("Hubo un error buscando la serie con el filtro indicado", e);
		}
		
	}
	
	@Override
	@Transactional //Para métodos que hacen varias cosas, como aquí que guardamos series y sus capitulosy temporadas. Para que si peta, haga rollback
	public Serie crearSerie(Serie serie) throws SeriesServiceException {
		try {
		return repoSerie.save(serie);
		}catch(Exception e){
			throw new SeriesServiceException("Hubo un error al crear la serie", e);            
		}
		
	}
	
	
	@Override
	@Transactional
	public void elimnarSerie(Long idSerie) throws SeriesServiceException {
		try {
		repoSerie.deleteById(idSerie);
		}catch (Exception e) {
			throw new SeriesServiceException("Hubo problema al eliminar serie", e);
		}
	}
	
	
	@Override
	@Transactional
	public void actualizarSerie(Serie serie) throws SeriesServiceException {
		try {
		repoSerie.save(serie);
		}catch(Exception e) {
			throw new SeriesServiceException("Hubo un error al actualizar la serie", e);
		}
	}
}
