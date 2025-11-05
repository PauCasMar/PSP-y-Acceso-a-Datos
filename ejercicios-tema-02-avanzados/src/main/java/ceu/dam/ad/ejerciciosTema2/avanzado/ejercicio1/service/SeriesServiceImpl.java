package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.service;

import java.util.ArrayList;
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
		return repoSerie.findById(idSerie).orElseThrow(()->new SerieNotFoundException("No existe esa serie!!"));
		}catch(DataAccessException e) {
			throw new SeriesServiceException("Hubo algún problema buscando la serie"); 
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
			throw new SeriesServiceException ("Hubo un error buscando la serie con el filtro indicado");
		}
		
	}
	
	@Override
	@Transactional
	public Serie crearSerie(Serie serie) throws SeriesServiceException {
		try {
		return repoSerie.save(serie);
		}catch(Exception e){
			throw new SeriesServiceException("Hubo un error al crear la serie");            
		}
		
	}
	
	
	@Override
	@Transactional
	public void elimnarSerie(Long idSerie) throws SeriesServiceException {
		try {
		repoSerie.deleteById(idSerie);
		}catch (Exception e) {
			throw new SeriesServiceException("Hubo problema al eliminar serie");
		}
	}
	
	
	@Override
	@Transactional
	public void actualizarSerie(Serie serie) throws SeriesServiceException {
		try {
		repoSerie.save(serie);
		}catch(Exception e) {
			throw new SeriesServiceException("Hubo un error al actualizar la serie");
		}
		
		
	}
	
	
}
