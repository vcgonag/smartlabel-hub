package com.proyectodam.apiserver.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.types.Binary;
import org.springframework.http.MediaType;

@RestController
public class ProductoController {

	// http://172.20.10.2:8080/productos/listarProductos
	// http://192.168.0.15:8080/productos/listarProductos
	@GetMapping("productos/listarProductos")
	public ResponseEntity<String> listarLibros() {
		System.out.println(">>> PETICION RECIBIDA");
		String json = listarProductos();
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	// http://172.20.10.2:8080/productos/listaProducto?id=xxxxxxxxxxxxx
	// http://192.168.0.15:8080/productos/listaProducto?id=xxxxxxxxxxxxx
	@GetMapping("productos/listaProducto")
	public ResponseEntity<String> listarProducto(@RequestParam(value = "id") String id) {
		String json = listaProducto(id);
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	// http://172.20.10.2:8080/productos/foto?id=xxxxxxxxxxxxx
	// http://192.168.0.15:8080/productos/foto?id=xxxxxxxxxxxxx
	@GetMapping("productos/foto")
	public ResponseEntity<byte[]> foto(@RequestParam("id") String id) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");

		Document doc = coleccionMedia.find(new Document("_id", id)).first();

		if (doc == null) {
			mongoClient.close();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Recuperamos binario y mime
		Binary bin = doc.get("productoPNG", Binary.class);
		String mime = doc.getString("mime"); // "image/jpeg"
		byte[] bytes = bin.getData();

		mongoClient.close();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mime)).body(bytes);
	}

	// http://172.20.10.2:8080/productos/barcode?id=xxxxxxxxxxxxx
	// http://192.168.0.15:8080/productos/barcode?id=xxxxxxxxxxxxx
	@GetMapping("productos/barcode")
	public ResponseEntity<byte[]> barcode(@RequestParam("id") String id) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");

		Document doc = coleccionMedia.find(new Document("_id", id)).first();

		if (doc == null) {
			mongoClient.close();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Recuperamos binario y mime
		Binary bin = doc.get("barCodePNG", Binary.class);
		String mime = doc.getString("mime"); // "image/jpeg"
		byte[] bytes = bin.getData();

		mongoClient.close();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mime)).body(bytes);
	}
	
	// ******************************************* METODOS *******************************************

	public String listarProductos() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProductos = database.getCollection("productos");

		MongoCursor<Document> cursor = coleccionProductos.find().iterator();
		JSONArray arrayProductos = new JSONArray();
		while (cursor.hasNext()) {
			Document doc = cursor.next();

			// Sacamos datos
			String id = doc.getString("_id");
			String nombre = doc.getString("nombre");
			String marca = doc.getString("marca");
			String categoria = doc.getString("categoria");
			String peso = doc.getString("peso");
			double precio = doc.getDouble("precio");
			String descripcion = doc.getString("descripcion");

			// Construimos objeto JSON limpio (SIN imagen)
			JSONObject prod = new JSONObject();
			prod.put("id", id);
			prod.put("nombre", nombre);
			prod.put("marca", marca);
			prod.put("categoria", categoria);
			prod.put("peso", peso);
			prod.put("precio", precio);
			prod.put("descripcion", descripcion);

//			prod.put("imagenPNG", "http://172.20.10.5:8080/productos/foto?id=" + id);
//			prod.put("barcodePNG", "http://172.20.10.5:8080/productos/barcode?id=" + id);
			
			prod.put("imagenPNG", "http://192.168.0.15:8080/productos/foto?id=" + id);
			prod.put("barcodePNG", "http://192.168.0.15:8080/productos/barcode?id=" + id);

			arrayProductos.put(prod);
		}
		cursor.close();
		mongoClient.close();

		JSONObject respuesta = new JSONObject();
		respuesta.put("productos", arrayProductos);
		// System.out.println(respuesta);
		return respuesta.toString(4);
	}

	public String listaProducto(String idProducto) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProductos = database.getCollection("productos");

		MongoCursor<Document> cursor = coleccionProductos.find().iterator();
		JSONObject prod = new JSONObject();
		while (cursor.hasNext()) {
			Document doc = cursor.next();

			if (doc.getString("_id").equals(idProducto)) {
				prod.put("id", doc.getString("_id"));
				prod.put("nombre", doc.getString("nombre"));
				prod.put("marca", doc.getString("marca"));
				prod.put("categoria", doc.getString("categoria"));
				prod.put("peso", doc.getString("peso"));
				prod.put("precio", doc.getDouble("precio"));
				prod.put("descripcion", doc.getString("descripcion"));

//				prod.put("imagenPNG", "http://172.20.10.2:8080/productos/foto?id=" + idProducto);
//				prod.put("barcodePNG", "http://172.20.10.2:8080/productos/barcode?id=" + idProducto);
				
				prod.put("imagenPNG", "http://192.168.0.1:8080/productos/foto?id=" + idProducto);
				prod.put("barcodePNG", "http://192.168.0.1:8080/productos/barcode?id=" + idProducto);
			}
		}
		return prod.toString(4);
	}

}
