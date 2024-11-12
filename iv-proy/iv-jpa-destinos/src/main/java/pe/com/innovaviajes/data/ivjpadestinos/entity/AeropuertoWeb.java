package pe.com.innovaviajes.data.ivjpadestinos.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the aeropuerto_web database table.
 * 
 */
@Entity
@Table(name="aeropuerto_web", schema = "soporte")
@NamedQuery(name="AeropuertoWeb.findAll", query="SELECT a FROM AeropuertoWeb a")
public class AeropuertoWeb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AEROPUERTO_WEB_ID_GENERATOR", sequenceName="SEQ_AEROPUERTO_WEB", schema = "soporte")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AEROPUERTO_WEB_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=10)
	private String codigoiata;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Column(nullable=false)
	private Timestamp fechacreacion;

	@Column(nullable=false)
	private Timestamp fechamodificacion;

	@Column(nullable=false)
	private Integer idestadoregistro;

	@Column(nullable=false)
	private Integer idusuariocreacion;

	@Column(nullable=false)
	private Integer idusuariomodificacion;

	@Column(nullable=false, length=15)
	private String ipcreacion;

	@Column(nullable=false, length=15)
	private String ipmodificacion;

	//bi-directional many-to-one association to DestinoCiudad
	@ManyToOne
	@JoinColumn(name="iddestino", nullable=false)
	private DestinoCiudad destinoCiudad;

	//bi-directional many-to-one association to PaisWeb
	@ManyToOne
	@JoinColumn(name="idpais", nullable=false)
	private PaisWeb paisWeb;

	public AeropuertoWeb() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoiata() {
		return this.codigoiata;
	}

	public void setCodigoiata(String codigoiata) {
		this.codigoiata = codigoiata;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Integer getIdestadoregistro() {
		return this.idestadoregistro;
	}

	public void setIdestadoregistro(Integer idestadoregistro) {
		this.idestadoregistro = idestadoregistro;
	}

	public Integer getIdusuariocreacion() {
		return this.idusuariocreacion;
	}

	public void setIdusuariocreacion(Integer idusuariocreacion) {
		this.idusuariocreacion = idusuariocreacion;
	}

	public Integer getIdusuariomodificacion() {
		return this.idusuariomodificacion;
	}

	public void setIdusuariomodificacion(Integer idusuariomodificacion) {
		this.idusuariomodificacion = idusuariomodificacion;
	}

	public String getIpcreacion() {
		return this.ipcreacion;
	}

	public void setIpcreacion(String ipcreacion) {
		this.ipcreacion = ipcreacion;
	}

	public String getIpmodificacion() {
		return this.ipmodificacion;
	}

	public void setIpmodificacion(String ipmodificacion) {
		this.ipmodificacion = ipmodificacion;
	}

	public DestinoCiudad getDestinoCiudad() {
		return this.destinoCiudad;
	}

	public void setDestinoCiudad(DestinoCiudad destinoCiudad) {
		this.destinoCiudad = destinoCiudad;
	}

	public PaisWeb getPaisWeb() {
		return this.paisWeb;
	}

	public void setPaisWeb(PaisWeb paisWeb) {
		this.paisWeb = paisWeb;
	}

}