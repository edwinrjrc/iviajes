/**
 * 
 */
package pe.com.innovaviajes.web.ivservicerentcar.dto;

/**
 * @author Edwin
 *
 */
public class ErrorResponseDto {

	private int status;
    private String mensaje;
    private Boolean error;
    private long timestamp;

    public ErrorResponseDto(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
        this.error = true;
        this.timestamp = System.currentTimeMillis();
    }

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the error
	 */
	public Boolean getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
    
    

}
