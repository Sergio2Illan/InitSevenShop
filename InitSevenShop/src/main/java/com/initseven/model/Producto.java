package com.initseven.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int numproducto;

	private int capacidad;

	@Lob
	private String descripcion;

	private byte estado;

	private String nombre;

	//bi-directional many-to-one association to Carrito
	@OneToMany(mappedBy="producto")
	private List<Carrito> carritos;

	public Producto() {
	}

	public int getNumproducto() {
		return this.numproducto;
	}

	public void setNumproducto(int numproducto) {
		this.numproducto = numproducto;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Carrito> getCarritos() {
		return this.carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}

	public Carrito addCarrito(Carrito carrito) {
		getCarritos().add(carrito);
		carrito.setProducto(this);

		return carrito;
	}

	public Carrito removeCarrito(Carrito carrito) {
		getCarritos().remove(carrito);
		carrito.setProducto(null);

		return carrito;
	}

}