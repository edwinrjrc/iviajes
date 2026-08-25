/**
 * 
 */
package pe.com.innovaviajes.web.ivservicerentcar.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

/**
 * @author Edwin
 *
 */
@Service
public class UtilSecurity {

	private static final Logger log = LoggerFactory.getLogger(UtilSecurity.class);

	@Value("${security.rsa.private-key-path}")
	private String privateKeyPath;

	private PrivateKey privateKey;

	public String descencripta(String encryptedData) {
		try {
			if (StringUtils.isBlank(encryptedData)) {
				return "";
			}

			// Usamos el objeto privateKey ya cargado en memoria
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, this.privateKey);

			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
			return new String(decryptedBytes, StandardCharsets.UTF_8);

		} catch (Exception e) {
			log.error("Error al desencriptar: {}", e.getMessage());
			return "";
		}
	}

	@PostConstruct
	public void init() throws Exception {
		// Se ejecuta una sola vez al iniciar el microservicio
		String path = privateKeyPath.replace("file:", "");
		String keyContent = new String(Files.readAllBytes(Paths.get(path)));

		String privateKeyPEM = keyContent.replace("-----BEGIN PRIVATE KEY-----", "")
				.replace("-----END PRIVATE KEY-----", "").replaceAll("\\s", ""); // Quita espacios, tabs y saltos de
																					// línea (\r, \n)

		byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		this.privateKey = kf.generatePrivate(keySpec);
	}

}
