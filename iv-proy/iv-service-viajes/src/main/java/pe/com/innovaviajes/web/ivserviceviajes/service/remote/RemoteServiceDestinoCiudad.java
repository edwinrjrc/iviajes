/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.innovaviajes.cross.util.Constantes;
import pe.com.innovaviajes.dto.DestinoCiudadDto;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceDestinoCiudad {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceDestinoCiudad.class);
	
	private static final String URL_SERVICE_1 = "http://iv-jpa-destinos/DestinoCiudadJPAService/destinosCiudad";

	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public List<DestinoCiudadDto> consultarDestinos(String nombreDestino) throws IvServiceDestinoCiudadException {
		List<DestinoCiudadDto> listaDestinos;
		try {
			listaDestinos = null;
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_1);
			builderURI.queryParam("nombreDestino", nombreDestino);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			HttpStatus codigoStatus = respuesta.getStatusCode();
			
			if (!HttpStatus.NO_CONTENT.equals(codigoStatus)) {
				ObjectMapper mapper = obtenerMapper();
				
			    List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			    listaDestinos = new ArrayList<>();
			    for (Object objeto : datosLista) {
			    	LinkedHashMap map = (LinkedHashMap) objeto;
			    	
			    	listaDestinos.add(mapper.convertValue(map, DestinoCiudadDto.class));
				}
			}
			
			return listaDestinos;
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		} catch (IvServiceDestinoCiudadException e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		}
		
	}
	
	
	@SuppressWarnings("unused")
	private URI obtenerUri(String cadenaUrl) throws IvServiceDestinoCiudadException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);
			
		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new IvServiceDestinoCiudadException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IvServiceDestinoCiudadException(e);
		}
		return url;
	}
	
	private HttpHeaders generarHttpHeaders() throws IvServiceDestinoCiudadException {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			return headers;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IvServiceDestinoCiudadException(e);
		}
	}
	
	private ObjectMapper obtenerMapper () throws IvServiceDestinoCiudadException {
		try {
			log.debug("Time Zone Default: "+TimeZone.getDefault().toString());
			
			log.debug("FORMAT_DATE_MAPPER_FULL: "+Constantes.FORMAT_DATE_MAPPER_FULL);
			SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER_FULL);
			df.setTimeZone(TimeZone.getDefault());
			
			JsonFactory factory = new JsonFactory();
			factory.enable(Feature.ALLOW_SINGLE_QUOTES);
			ObjectMapper mapper = new ObjectMapper(factory);
			mapper.setDateFormat(df);
			mapper.setTimeZone(TimeZone.getDefault());
			
			return mapper;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IvServiceDestinoCiudadException(e);
		}
	}
}
