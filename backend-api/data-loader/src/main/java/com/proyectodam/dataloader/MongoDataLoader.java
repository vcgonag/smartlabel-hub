package com.proyectodam.dataloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.Binary;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDataLoader {

	public static void subirPorductosBD() throws IOException {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProd = database.getCollection("productos");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");
		coleccionProd.drop();
		coleccionMedia.drop();

		// Insertar varios documentos en la coleccion
		System.out.println("Insertar varios documentos... \n");
		ArrayList<Document> listaProductos = new ArrayList<Document>();
		ArrayList<Document> listaMedia = new ArrayList<Document>();// vacio

		File ficheroPoducto = new File("data/productos.json");
		String strJSON = new String(Files.readAllBytes(ficheroPoducto.toPath()));
		JSONArray array = new JSONArray(strJSON);
		for (int i = 0; i < array.length(); i++) {
			//System.out.println(i);
			JSONObject producto = array.getJSONObject(i);
			Document doc = new Document();
			Document docMedia = new Document();
			// doc.append("id", producto.getInt("id"));
			doc.append("_id", producto.getString("codigoBarras"));
			doc.append("nombre", producto.getString("nombre"));
			doc.append("marca", producto.getString("marca"));
			doc.append("categoria", producto.getString("categoria"));
			doc.append("peso", producto.getString("peso"));
			doc.append("precio", producto.getDouble("precio"));
			doc.append("descripcion", producto.getString("descripcion"));
			// doc.append("mime", producto.getString("mime"));
			// doc.append("imagen", producto.getString("imagen"));

			File ficheroImgProd = new File(producto.getString("imagen"));
			byte[] fileContentImgProd = Files.readAllBytes(ficheroImgProd.toPath());
			// String encodedImgProdPng = Base64.encodeBase64String(fileContentImgProd);

			// doc.append("imagenBarCode", producto.getString("imagenBarCode"));
			File ficheroBarCode = new File(producto.getString("imagenBarCode"));
			byte[] fileContentBarCode = Files.readAllBytes(ficheroBarCode.toPath());
			// String encodedBardCodePng = Base64.encodeBase64String(fileContentBarCode);
			docMedia.append("_id", producto.getString("codigoBarras"));
			docMedia.append("nombre", producto.getString("nombre"));
			docMedia.append("mime", producto.getString("mime"));
			docMedia.append("productoPNG", new Binary(fileContentImgProd));
			docMedia.append("barCodePNG", new Binary(fileContentBarCode));

			listaProductos.add(doc);// Añadimos documento a la lista
			listaMedia.add(docMedia);
		}
		coleccionProd.insertMany(listaProductos);
		coleccionMedia.insertMany(listaMedia);

		mongoClient.close();
	}

	public static void leerArchivos() throws IOException {
		File ficheroUser = new File("data/productos.json");
		String strJSON = new String(Files.readAllBytes(ficheroUser.toPath()));
		JSONArray array = new JSONArray(strJSON);
		for (int i = 0; i < array.length(); i++) {
			JSONObject producto = array.getJSONObject(i);
			String nombreProducto = producto.getString("nombre");
			System.out.println(nombreProducto);
		}
	}

	public static void main(String[] args) throws IOException {
		subirPorductosBD();
	}

}
