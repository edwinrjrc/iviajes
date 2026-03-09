/**
 * 
 */
package pe.com.innovaviajes.config.ivconfigserver;

import java.io.File;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Edwin
 *
 */
@Component
public class TestRutaConfig implements CommandLineRunner {

	@Override
    public void run(String... args) throws Exception {
        String ruta = "D:/claves";
        File carpeta = new File(ruta);
        
        System.out.println("--- PRUEBA DE RUTA NATIVA ---");
        System.out.println("Buscando en: " + carpeta.getAbsolutePath());
        
        if (carpeta.exists() && carpeta.isDirectory()) {
            System.out.println("¡ÉXITO! La carpeta existe.");
            String[] archivos = carpeta.list();
            System.out.println("Archivos encontrados: " + Arrays.toString(archivos));
        } else {
            System.out.println("ERROR: La carpeta NO existe o no es accesible.");
        }
        System.out.println("-----------------------------");
    }

}
