package pe.com.innovaviajes.web.ivserviceviajes.util;

import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilConversionS {
    
    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UtilConversionS.class);
    public static final int CRYPTO_AUTH_TAG_LENGTH = 128;
    public static final int CRYPTO_IV_LENGTH = 12;
    private String llave = "P@s$W0(Contr3n@)"; // Esta es tu "chapa" simétrica

    /**
     * Abre la "chapa" asimétrica que viene del Front (RSA)
     */
    public String descencripta(String datoEncriptado) throws Exception {
    	String privateKeyPKCS8 = null;
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPKCS8);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);

        // Usamos OAEP para máxima seguridad en la chapa asimétrica
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(datoEncriptado));
        return new String(plainText);
    }

    /**
     * Cierra la "chapa" simétrica para enviar al Front (AES-GCM)
     */
    public String encriptaSimetrico(String data) throws Exception {
        byte[] iv = new byte[CRYPTO_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        
        // Usamos tu "chapa" para generar la llave AES
        SecretKeySpec secretKey = new SecretKeySpec(this.llave.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv);
        
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
        byte[] cipherText = cipher.doFinal(data.getBytes());
        
        // Unimos el IV con el contenido para que Angular lo reconozca
        ByteBuffer bb = ByteBuffer.allocate(iv.length + cipherText.length);
        bb.put(iv).put(cipherText);
        return Base64.getEncoder().encodeToString(bb.array());
    }
}