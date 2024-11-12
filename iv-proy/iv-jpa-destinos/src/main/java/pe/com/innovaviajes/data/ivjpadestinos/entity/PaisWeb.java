package pe.com.innovaviajes.data.ivjpadestinos.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pais_web database table.
 * 
 */
@Entity
@Table(name="pais_web", schema = "soporte")
@NamedQuery(name="PaisWeb.findAll", query="SELECT p FROM PaisWeb p")
public class PaisWeb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAIS_WEB_ID_GENERATOR", sequenceName="SEQ_PAIS", schema = "soporte")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAIS_WEB_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2)
	private String abreviado;

	@Column(length=100)
	private String descripcion;

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

	private Integer idcontinente;

	private Integer idempresa;

	private Integer idestadoregistro;

	private Integer idpais;

	private Integer idusuariocreacion;

	private Integer idusuariomodificacion;

	@Column(length=15)
	private String ipcreacion;

	@Column(length=15)
	private String ipmodificacion;

	@Column(length=2147483647)
	private String otrosnombres;

	//bi-directional many-to-one association to AeropuertoWeb
	@OneToMany(mappedBy="paisWeb")
	private List<AeropuertoWeb> aeropuertoWebs;

	//bi-directional many-to-one association to DestinoCiudad
	@OneToMany(mappedBy="paisWeb")
	private List<DestinoCiudad> destinoCiudads;

	public PaisWeb() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbreviado() {
		return this.abreviado;
	}

	public void setAbreviado(String abreviado) {
		this.abreviado = abreviado;
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

	public Integer getIdcontinente() {
		return this.idcontinente;
	}

	public void setIdcontinente(Integer idcontinente) {
		this.idcontinente = idcontinente;
	}

	public Integer getIdempresa() {
		return this.idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public Integer getIdestadoregistro() {
		return this.idestadoregistro;
	}

	public void setIdestadoregistro(Integer idestadoregistro) {
		this.idestadoregistro = idestadoregistro;
	}

	public Integer getIdpais() {
		return this.idpais;
	}

	public void setIdpais(Integer idpais) {
		this.idpais = idpais;
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

	public String getOtrosnombres() {
		return this.otrosnombres;
	}

	public void setOtrosnombres(String otrosnombres) {
		this.otrosnombres = otrosnombres;
	}

	public List<AeropuertoWeb> getAeropuertoWebs() {
		return this.aeropuertoWebs;
	}

	public void setAeropuertoWebs(List<AeropuertoWeb> aeropuertoWebs) {
		this.aeropuertoWebs = aeropuertoWebs;
	}

	public AeropuertoWeb addAeropuertoWeb(AeropuertoWeb aeropuertoWeb) {
		getAeropuertoWebs().add(aeropuertoWeb);
		aeropuertoWeb.setPaisWeb(this);

		return aeropuertoWeb;
	}

	public AeropuertoWeb removeAeropuertoWeb(AeropuertoWeb aeropuertoWeb) {
		getAeropuertoWebs().remove(aeropuertoWeb);
		aeropuertoWeb.setPaisWeb(null);

		return aeropuertoWeb;
	}

	public List<DestinoCiudad> getDestinoCiudads() {
		return this.destinoCiudads;
	}

	public void setDestinoCiudads(List<DestinoCiudad> destinoCiudads) {
		this.destinoCiudads = destinoCiudads;
	}

	public DestinoCiudad addDestinoCiudad(DestinoCiudad destinoCiudad) {
		getDestinoCiudads().add(destinoCiudad);
		destinoCiudad.setPaisWeb(this);

		return destinoCiudad;
	}

	public DestinoCiudad removeDestinoCiudad(DestinoCiudad destinoCiudad) {
		getDestinoCiudads().remove(destinoCiudad);
		destinoCiudad.setPaisWeb(null);

		return destinoCiudad;
	}

}