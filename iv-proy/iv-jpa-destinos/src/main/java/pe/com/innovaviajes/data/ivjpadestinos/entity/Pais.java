package pe.com.innovaviajes.data.ivjpadestinos.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name="pais", schema = "soporte")
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAIS_ID_GENERATOR", sequenceName="SEQ_PAIS", schema = "soporte")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAIS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2)
	private String abreviado;

	@Column(length=100)
	private String descripcion;

	@Column(nullable=false)
	private Timestamp fechacreacion;

	@Column(nullable=false)
	private Timestamp fechamodificacion;

	@Column(nullable=false)
	private Integer idcontinente;

	private Integer idempresa;

	@Column(nullable=false)
	private Integer idestadoregistro;

	private Integer idpais;

	@Column(nullable=false)
	private Integer idusuariocreacion;

	@Column(nullable=false)
	private Integer idusuariomodificacion;

	@Column(nullable=false, length=15)
	private String ipcreacion;

	@Column(nullable=false, length=15)
	private String ipmodificacion;

	//bi-directional many-to-one association to Destino
	@OneToMany(mappedBy="pais")
	private List<Destino> destinos;

	public Pais() {
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

	public List<Destino> getDestinos() {
		return this.destinos;
	}

	public void setDestinos(List<Destino> destinos) {
		this.destinos = destinos;
	}

	public Destino addDestino(Destino destino) {
		getDestinos().add(destino);
		destino.setPais(this);

		return destino;
	}

	public Destino removeDestino(Destino destino) {
		getDestinos().remove(destino);
		destino.setPais(null);

		return destino;
	}

}