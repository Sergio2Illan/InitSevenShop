package com.initseven.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Carrito database table.
 * 
 */
@Entity
@NamedQuery(name="Carrito.findAll", query="SELECT c FROM Carrito c")
public class Carrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcarro;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="numproducto")
	private Producto producto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Carrito() {
	}

	public int getIdcarro() {
		return this.idcarro;
	}

	public void setIdcarro(int idcarro) {
		this.idcarro = idcarro;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}