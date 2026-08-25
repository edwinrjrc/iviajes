/**
 * 
 */
package pe.com.innovaviajes.data.ivjpadestinos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.innovaviajes.data.ivjpadestinos.exception.JpaDestinosException;
/**
 * @author Edwin
 *
 */
public class UtilIvDto {
	
	private static final Logger log = LoggerFactory.getLogger(UtilIvDto.class);
	
	/**
	 * Patron por defecto para metodos que no envien el patron
	 */
	private static final String PATRON_SDF_DEFECTO = "dd/MM/yyyy";

	public static void pintaLog(Object bean, String prefijo) {
		ObjectMapper converter = new ObjectMapper();
		try {
			log.debug(prefijo+" : "+converter.writeValueAsString(bean));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public static boolean listaNoVacia(List<?> lista) {
		boolean resultado = (lista != null && !lista.isEmpty());
		
		return resultado;
	}
	
	public static String escribeObjetoEnLog(Object objeto) throws JpaDestinosException {
		try {
			ObjectMapper Obj = new ObjectMapper();
			
			return Obj.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			throw new JpaDestinosException(e);
		}
	}
	
	public static Date parseStringADate(String cadenaFecha, String patron, TimeZone timeZone) throws JpaDestinosException {
		try {
			if (StringUtils.isBlank(cadenaFecha)) {
				throw new JpaDestinosException("La fecha a convertir esta en blanco o es nula");
			}
			SimpleDateFormat sdf = new SimpleDateFormat((StringUtils.isBlank(patron)?PATRON_SDF_DEFECTO:patron));
			sdf.setTimeZone(TimeZone.getDefault());
			if (timeZone != null) {
				sdf.setTimeZone(timeZone);
			}
			
			return sdf.parse(cadenaFecha);
		} catch (ParseException e) {
			throw new JpaDestinosException(e);
		}
	}
	
	public static String parseDateAString(Date fecha, String patron) throws JpaDestinosException {
		if (fecha == null) {
			throw new JpaDestinosException("La fecha es nula");
		}
		SimpleDateFormat sdf = new SimpleDateFormat((StringUtils.isBlank(patron)?PATRON_SDF_DEFECTO:patron));
		return sdf.format(fecha);
	}
	
	public static Timestamp parseDateASqlTimestamp(Date fecha) {
		Timestamp t = new Timestamp(fecha.getTime());
		
		return t;
	}
	
	public static Timestamp hoy() {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		return t;
	}
	
	public static TimeZone obtenerTimeZoneLocal() {
		TimeZone timeZone = TimeZone.getDefault();
		return timeZone;
	}
	
	public static BigDecimal parseBigDecimal(short valor) {
		BigDecimal resultado = BigDecimal.ZERO;
		
		resultado = BigDecimal.valueOf(valor);
		
		return resultado;
	}
	
	public static BigDecimal redondea(BigDecimal valor, int numeroDecimales) throws JpaDestinosException {
		try {
			if (valor != null) {
				return valor.setScale(numeroDecimales, RoundingMode.HALF_UP);
			}
			else {
				throw new JpaDestinosException("Valor en nulo");
			}
		} catch (NullPointerException e) {
			throw new JpaDestinosException("Error: valor nulo", e);
		} catch (Exception e) {
			throw new JpaDestinosException(e.getMessage(), e);
		}
	}
	
	public static BigDecimal redondeA2(BigDecimal valor) throws JpaDestinosException {
		return redondea(valor,2);
	}
	
	public static BigDecimal redondeA3(BigDecimal valor) throws JpaDestinosException {
		return redondea(valor,3);
	}
	
	public static BigDecimal redondeA4(BigDecimal valor) throws JpaDestinosException {
		return redondea(valor,4);
	}
	
	public static BigDecimal redondeA5(BigDecimal valor) throws JpaDestinosException {
		return redondea(valor,5);
	}
	
	public static BigDecimal redondeA6(BigDecimal valor) throws JpaDestinosException {
		return redondea(valor,6);
	}
}
