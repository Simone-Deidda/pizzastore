package it.prova.pizzastore.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import it.prova.pizzastore.model.auth.Utente;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codice")
	private String codice;
	@Column(name = "costoTotaleOrdine")
	private Integer costoTotaleOrdine;
	@Column(name = "data")
	private Date data;
	@Column(name = "cloded")
	private boolean cloded;

	// campi per le time info del record
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "pizza_ordine", joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"))
	private Set<Pizza> pizze = new HashSet<Pizza>();

	public Ordine() {
	}

	public Ordine(String codice, Date data) {
		this.codice = codice;
		this.data = data;
		this.cloded = false;
		calcolaPrezziPizze();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getCostoTotaleOrdine() {
		return costoTotaleOrdine;
	}

	public void setCostoTotaleOrdine(Integer costoTotaleOrdine) {
		this.costoTotaleOrdine = costoTotaleOrdine;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isCloded() {
		return cloded;
	}

	public void setCloded(boolean cloded) {
		this.cloded = cloded;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
	}

	@Override
	public String toString() {
		return "Ordine [id=" + id + ", codice=" + codice + ", costoTotaleOrdine=" + costoTotaleOrdine + ", data=" + data
				+ ", cloded=" + cloded + "]";
	}

	private void calcolaPrezziPizze() {
		if (this.pizze == null || this.pizze.isEmpty()) {
			this.costoTotaleOrdine = 0;
			return;
		} else {
			costoTotaleOrdine = 0;
			for (Pizza pizza : this.pizze) {
				costoTotaleOrdine += pizza.getPrezzoBase();
			}
		}
	}

}
