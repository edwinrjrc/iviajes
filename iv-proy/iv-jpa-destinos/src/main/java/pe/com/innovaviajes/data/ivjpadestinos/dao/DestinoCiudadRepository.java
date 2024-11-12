/**
 * 
 */
package pe.com.innovaviajes.data.ivjpadestinos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.innovaviajes.data.ivjpadestinos.entity.DestinoCiudad;


/**
 * @author Edwin
 *
 */
public interface DestinoCiudadRepository extends JpaRepository<DestinoCiudad, Integer>{
	
	public List<DestinoCiudad> findByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(String descripcion);
}
