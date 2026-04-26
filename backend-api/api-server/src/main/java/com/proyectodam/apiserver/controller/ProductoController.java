package com.proyectodam.apiserver.controller;

import com.proyectodam.common.model.Producto;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import org.bson.types.Binary;
import org.springframework.http.MediaType;

@RestController
@CrossOrigin(origins = "http://localhost:4200/") // <--- ESTO DA PERMISO A ANGULAR
public class ProductoController {

	// http://192.168.0.15:8080/productos/listarProductos
	@GetMapping("productos/listarProductos")
	public ResponseEntity<String> listarLibros() {
		System.out.println(">>> PETICION RECIBIDA");
		String json = listarProductos();
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	// http://192.168.0.15:8080/productos/listaProducto?id=xxxxxxxxxxxxx
	@GetMapping("productos/listaProducto")
	public ResponseEntity<String> listarProducto(@RequestParam(value = "id") String id) {
		String json = listaProducto(id);
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

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
		Binary bin = doc.get("imagenPNG", Binary.class);
		String mime = doc.getString("mime"); // "image/jpeg"
		byte[] bytes = bin.getData();

		mongoClient.close();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mime)).body(bytes);
	}

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

	// http://localhost:8080/producto/update
	@PostMapping("productos/update")
	ResponseEntity<Object> update(@RequestBody String cuerpoPeticion) throws IOException {
		JSONObject obj = new JSONObject(cuerpoPeticion);

		System.out.println("\n" + obj.toString(4) + "\n");

		Producto p = new Producto();
		p.setId((String) obj.get("id"));
		p.setNombre((String) obj.get("nombre"));
		p.setMarca((String) obj.get("marca"));
		p.setCategoria((String) obj.get("categoria"));
		p.setPeso((String) obj.get("peso"));
		p.setPrecioReferencia(((Number) obj.get("precioReferencia")).doubleValue());
		p.setPrecioPromo(((Number) obj.get("precioPromo")).doubleValue());
		p.setDescripcion((String) obj.get("descripcion"));
		p.setSincronizado((boolean) obj.get("sincronizado"));
		p.setVinculado((boolean) obj.get("vinculado"));

		System.out.println(p.toString());

		if (productoExistente(p.getId())) {
			productoUpdate(p);
			return ResponseEntity.ok("Producto actualizado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El producto con ID  " + p.getId() + "  no existe.");
		}

	}

	@PostMapping("productos/create")
	ResponseEntity<Object> create(@RequestBody String cuerpoPeticion) throws IOException {
		JSONObject obj = new JSONObject(cuerpoPeticion);

		System.out.println("\n" + obj.toString(4) + "\n");

		Producto p = new Producto();
		String id = String.valueOf(obj.get("id"));
		p.setId(String.valueOf(obj.get("id")));
		p.setNombre((String) obj.get("nombre"));
		p.setMarca((String) obj.get("marca"));
		p.setCategoria((String) obj.get("categoria"));
		p.setPeso((String) obj.get("peso"));
		p.setPrecioReferencia(((Number) obj.get("precioReferencia")).doubleValue());
		p.setPrecioPromo(((Number) obj.get("precioPromo")).doubleValue());
		p.setDescripcion((String) obj.get("descripcion"));
		p.setSincronizado((boolean) obj.get("sincronizado"));
		p.setVinculado((boolean) obj.get("vinculado"));

		System.out.println(p.toString());

		// if (!ProductoExistente(p.getId())) {
		addProducto(p);
		return ResponseEntity.ok("Producto insertado correctamente");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body("Error: El producto con ID  " + p.getId() + "  ya existe.");
//		}

	}

	@PostMapping("productos/delete")
	ResponseEntity<Object> delete(@RequestBody String cuerpoPeticion) throws IOException {

		String productoId = cuerpoPeticion;
		System.out.println("Intentando eliminar producto con ID:" + productoId);

		if (borrarProducto(productoId) > 0) {
			return ResponseEntity.ok("Producto eleminado correctamente ");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto");
		}
	}

	// METODOS
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
			double precioReferencia = doc.getDouble("precioReferencia");
			double precioPromo = doc.getDouble("precioPromo");
			String descripcion = doc.getString("descripcion");
			boolean sincronizado = doc.getBoolean("sincronizado");
			boolean vinculado = doc.getBoolean("vinculado");

			// Construimos objeto JSON limpio (SIN imagen)
			JSONObject prod = new JSONObject();
			prod.put("id", id);
			prod.put("nombre", nombre);
			prod.put("marca", marca);
			prod.put("categoria", categoria);
			prod.put("peso", peso);
			prod.put("precioReferencia", precioReferencia);
			prod.put("precioPromo", precioPromo);
			prod.put("descripcion", descripcion);
			prod.put("sincronizado", sincronizado);
			prod.put("vinculado", vinculado);

			// Cuando estoy con el modem movil
//			prod.put("imagenPNG", "http://172.20.10.5:8080/productos/foto?id=" + id);
//			prod.put("barcodePNG", "http://172.20.10.5:8080/productos/barcode?id=" + id);

			// Cuando estoy con la red local
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
				prod.put("precioReferencia", doc.getString("precioReferencia"));
				prod.put("precioPromo", doc.getDouble("precioPromo"));
				prod.put("descripcion", doc.getString("descripcion"));

//				prod.put("imagenPNG", "http://172.20.10.2:8080/productos/foto?id=" + idProducto);
//				prod.put("barcodePNG", "http://172.20.10.2:8080/productos/barcode?id=" + idProducto);

				prod.put("imagenPNG", "http://192.168.0.1:8080/productos/foto?id=" + idProducto);
				prod.put("barcodePNG", "http://192.168.0.1:8080/productos/barcode?id=" + idProducto);
			}
		}
		return prod.toString(4);
	}

	public boolean productoExistente(String idProducto) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProductos = database.getCollection("productos");

		MongoCursor<Document> cursor = coleccionProductos.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			if (doc.getString("_id").equals(idProducto)) {
				return true;
			}
		}

		mongoClient.close();

		return false;
	}

	public void productoUpdate(Producto p) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProd = database.getCollection("productos");

		coleccionProd.updateOne(eq("_id", p.getId()),
				new Document("$set", new Document("nombre", p.getNombre()).append("_id", p.getId())
						.append("marca", p.getMarca()).append("categoria", p.getCategoria()).append("peso", p.getPeso())
						.append("descripcion", p.getDescripcion()).append("precioReferencia", p.getPrecioReferencia())
						.append("precioPromo", p.getPrecioPromo()).append("vinculado", p.getVinculado())
						.append("sincronizado", p.getSincronizado())));

		mongoClient.close();
	}

	public void addProducto(Producto p) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProd = database.getCollection("productos");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");

		Document doc = new Document();
		Document docMedia = new Document();

		System.out.println("Insertar producto ... \n");
		doc.append("_id", p.getId());// Codigo de barras
		doc.append("nombre", p.getNombre());
		doc.append("marca", p.getMarca());
		doc.append("categoria", p.getCategoria());
		doc.append("peso", p.getPeso());
		doc.append("precioReferencia", p.getPrecioReferencia());
		doc.append("precioPromo", p.getPrecioPromo());
		doc.append("descripcion", p.getDescripcion());
		doc.append("descripcion", p.getDescripcion());
		doc.append("vinculado", p.getVinculado());
		doc.append("sincronizado", p.getSincronizado());

		docMedia.append("_id", p.getId());
		docMedia.append("nombre", p.getNombre());
		docMedia.append("mime", p.getMime());
		// docMedia.append("productoPNG", new Binary(p.getImagenPNG()));
		// docMedia.append("barCodePNG", new Binary(fileContentBarCode));

		coleccionProd.insertOne(doc);
		coleccionMedia.insertOne(docMedia);

		mongoClient.close();
	}

	public int borrarProducto(String idProucto) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProd = database.getCollection("productos");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");

//		Bson query = eq("_id", productoId);
//		MongoCursor<Document> cursor = coleccionProd.find(query).iterator();

		DeleteResult resultado = coleccionProd.deleteOne(eq("_id", idProucto));
		coleccionMedia.deleteOne(eq("_id", idProucto));

		mongoClient.close();

		System.out.println(resultado.getDeletedCount());
		return (int) resultado.getDeletedCount();

	}

}
