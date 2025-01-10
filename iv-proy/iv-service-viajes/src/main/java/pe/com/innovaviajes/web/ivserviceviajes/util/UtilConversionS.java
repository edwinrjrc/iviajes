/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;

/**
 * @author Edwin
 *
 */
public class UtilConversionS {

	public static final int CRYPTO_AUTH_TAG_LENGTH = 128;
	public static final int CRYPTO_IV_LENGTH = 12;
	public static final int ITERATION_COUNT = 100;
	
	private String algoritmo;
	
	private String modo;
	
	private String padding;
	
	private String instanceCipher;
	
	private String separadorInstanceCipher;
	
	private String llave;

	/**
	 * 
	 */
	public UtilConversionS() {
		this.algoritmo = "AES";
		this.modo = "GCM";
		this.padding = "NoPadding";
		this.llave = "P@s$W0(Contr3n@)";
		
		this.separadorInstanceCipher = "/";
	}
	
	public UtilConversionS(String algoritmo, String modo, String padding, String llave) {
		this.algoritmo = algoritmo;
		this.modo = modo;
		this.padding = padding;
		
		this.llave = llave;
		
		separadorInstanceCipher = "/";
	}

	public String descencripta(String datoEncriptado) throws IvServiceDestinoCiudadException {
		String descencriptado = "";
		try {
			byte[] encryptedDataBytes = Base64.getDecoder()
			        .decode(datoEncriptado.getBytes());

			//remember we stored the IV as the first 12 bytes while encrypting?
			byte[] iv = Arrays.copyOfRange(encryptedDataBytes, 0, CRYPTO_IV_LENGTH);
			
			GCMParameterSpec parameterSpec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv);
			parameterSpec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv);
			
			int tipoEncrypt = Cipher.DECRYPT_MODE;

			Cipher cipher = Cipher.getInstance(this.getInstanceCipher());
			cipher.init(tipoEncrypt, this.generateKey(), parameterSpec);
			
			byte [] cipherBytes = Arrays.copyOfRange(encryptedDataBytes, CRYPTO_IV_LENGTH,
			        encryptedDataBytes.length);
			
			byte[] plainText = cipher.doFinal(cipherBytes);
			
			descencriptado = new String(plainText);
			
			descencriptado = new String(plainText);
		} catch (InvalidKeyException e) {
			throw new IvServiceDestinoCiudadException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new IvServiceDestinoCiudadException(e);
		} catch (NoSuchPaddingException e) {
			throw new IvServiceDestinoCiudadException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new IvServiceDestinoCiudadException(e);
		} catch (IllegalBlockSizeException e) {
			throw new IvServiceDestinoCiudadException(e);
		} catch (BadPaddingException e) {
			throw new IvServiceDestinoCiudadException(e);
		}

		return descencriptado;
	}

	private SecretKey generateKey() throws IvServiceDestinoCiudadException {
		String salt = "mysalt";

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
			KeySpec spec = new PBEKeySpec(this.getLlave().toCharArray(), salt.getBytes(), ITERATION_COUNT, 128);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

			return secretKeySpec;
		} catch (NoSuchAlgorithmException e) {
			throw new IvServiceDestinoCiudadException(e);
		} catch (InvalidKeySpecException e) {
			throw new IvServiceDestinoCiudadException(e);
		}
	}

	private static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	/**
	 * @param algoritmo the algoritmo to set
	 */
	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

	/**
	 * @param modo the modo to set
	 */
	public void setModo(String modo) {
		this.modo = modo;
	}

	/**
	 * @param padding the padding to set
	 */
	public void setPadding(String padding) {
		this.padding = padding;
	}

	/**
	 * @return the instanceCipher
	 */
	public String getInstanceCipher() {
		instanceCipher = this.algoritmo + this.separadorInstanceCipher + this.modo + this.separadorInstanceCipher + this.padding;
		
		return instanceCipher;
	}

	/**
	 * @param separadorInstanceCipher the separadorInstanceCipher to set
	 */
	public void setSeparadorInstanceCipher(String separadorInstanceCipher) {
		this.separadorInstanceCipher = separadorInstanceCipher;
	}

	/**
	 * @return the llave
	 */
	public String getLlave() {
		return llave;
	}

	/**
	 * @param llave the llave to set
	 */
	public void setLlave(String llave) {
		this.llave = llave;
	}

}
