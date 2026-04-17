package com.proyectodam.common.model;

public class Producto {
	String id;
	String nombre;
	String marca;
	String categoria;
	String peso;
	double precio;
	String descripcion;

	String mime;
	//byte[] productoPNG;
	//byte[] barcodePNG;

	public Producto() {
	}

	public Producto(String id, String nombre, String marca, String categoria, String peso, double precio,
			String descripcion, String mime){/*, byte[] productoPNG, byte[] barcodePNG) {*/
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.peso = peso;
		this.precio = precio;
		this.descripcion = descripcion;

		this.mime = mime;
		//this.productoPNG = productoPNG;
		//this.barcodePNG = barcodePNG;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

//	public byte getProductoPNG() {
//		return productoPNG;
//	}
//
//	public void setProductoPNG(byte[] productoPNG) {
//		this.productoPNG = productoPNG;
//	}
//
//	public byte getBarcodePNG() {
//		return barcodePNG;
//	}
//
//	public void setBarcodePNG(byte[] barcodePNG) {
//		this.barcodePNG = barcodePNG;
//	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", categoria=" + categoria + ", peso="
				+ peso + ", precio=" + precio + ", descripcion=" + descripcion + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
