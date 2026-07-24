/**
 * 
 */
package pe.com.innovaviajes.config.ivconfigserver;

import java.io.File;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Edwin
 *
 */
@Component
public class TestRutaConfig implements CommandLineRunner {
	
	@Value("${spring.cloud.config.server.native.search-locations}")
    private String rutaConfigurada;

	@Override
    public void run(String... args) throws Exception {
        System.out.println("--- PRUEBA DE SUDOKU DE SEGURIDAD ---");
        
        // Si sale el hash, el descifrado falló. Si sale la ruta real, ¡ÉXITO!
        System.out.println("Ruta resuelta por Spring: " + rutaConfigurada);
        
        // Limpiamos el prefijo 'file:///' si existe para que Java File pueda leerlo
        String rutaLimpia = rutaConfigurada.replace("file:///", "");
        File carpeta = new File(rutaLimpia);
        
        if (carpeta.exists() && carpeta.isDirectory()) {
            System.out.println("ESTADO: ¡ÉXITO! La carpeta física es accesible.");
            String[] archivos = carpeta.list();
            System.out.println("Archivos detectados en " + carpeta.getName() + ": " + Arrays.toString(archivos));
        } else {
            System.out.println("ESTADO: ERROR. No se puede acceder a la ruta física.");
            System.out.println("Verifica la ENCRYPT_KEY y el valor de la variable de entorno.");
        }
        System.out.println("-------------------------------------");
    }

}
