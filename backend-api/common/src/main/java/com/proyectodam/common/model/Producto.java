package com.proyectodam.common.model;

public class Producto {

	private String id; // Codigo de barras
	private String nombre;
	private String marca;
	private String categoria;
	private String peso;
	private String descripcion;
	private String imagenPNG; // Aquí guardamos el String de la ruta/path
	private String barcodePNG;
	private String mime;


	// Precios
	private Double precioReferencia;
	private Double precioPromo;
	
	// Estados de la Baliza (Control de flujo)
    private Boolean vinculado;    // ¿Tiene una baliza física asignada?
    private Boolean sincronizado; // ¿Los datos de la DB coinciden con los de la baliza?
	

	public Producto() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagenPNG() {
		return imagenPNG;
	}

	public void setImagenPNG(String imagenPNG) {
		this.imagenPNG = imagenPNG;
	}

	public String getBarcodePNG() {
		return barcodePNG;
	}

	public void setBarcodePNG(String barcodePNG) {
		this.barcodePNG = barcodePNG;
	}

	public Double getPrecioReferencia() {
		return precioReferencia;
	}

	public void setPrecioReferencia(Double precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	public Double getPrecioPromo() {
		return precioPromo;
	}

	public void setPrecioPromo(Double precioPromo) {
		this.precioPromo = precioPromo;
	}

	public String getMine() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public Boolean getVinculado() {
		return vinculado;
	}

	public void setVinculado(Boolean vinculado) {
		this.vinculado = vinculado;
	}

	public Boolean getSincronizado() {
		return sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

	public String getMime() {
		return mime;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", categoria=" + categoria + ", peso="
				+ peso + ", descripcion=" + descripcion + ", imagenPNG=" + imagenPNG + ", barcodePNG=" + barcodePNG
				+ ", mime=" + mime + ", precioReferencia=" + precioReferencia + ", precioPromo=" + precioPromo
				+ ", vinculado=" + vinculado + ", sincronizado=" + sincronizado + "]";
	}
	
	

}
