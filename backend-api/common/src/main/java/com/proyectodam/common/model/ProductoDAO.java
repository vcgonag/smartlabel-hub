package com.proyectodam.common.model;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import static com.mongodb.client.model.Filters.*;

public class ProductoDAO {

	public static void subirProductosBD(Producto productoNew) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProd = database.getCollection("productos");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");

		Document doc = new Document();
		Document docMedia = new Document();

//		System.out.println("Insertar producto ... \n");
//		doc.append("_id", productoNew.getId());// Codigo de barras
//		doc.append("nombre", productoNew.getNombre());
//		doc.append("marca", productoNew.getMarca());
//		doc.append("categoria", productoNew.getCategoria());
//		doc.append("peso", productoNew.getPeso());
//		doc.append("precio", productoNew.getPrecio());
//		doc.append("descripcion", productoNew.getDescripcion());
//
//		docMedia.append("_id", productoNew.getId());
//		docMedia.append("nombre", productoNew.getNombre());
//		docMedia.append("mime", productoNew.getMime());
		
		//FALTARIA AÑADIR IMAGEN
		// docMedia.append("productoPNG", new Binary(productoNew.getProductoPNG()));
		// docMedia.append("barCodePNG", new Binary(fileContentBarCode));

		coleccionProd.insertOne(doc);
		coleccionMedia.insertOne(docMedia);

		mongoClient.close();
	}

	public static void modificarProductosBD(Producto productoNew, String id) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Proyecto_DAM");
		MongoCollection<Document> coleccionProd = database.getCollection("productos");
		MongoCollection<Document> coleccionMedia = database.getCollection("media");

		//podemos modificar cualquier 
		coleccionProd.updateOne(eq("_id", id), new Document("$set", new Document("nombre", "producto")));
		coleccionMedia.updateOne(eq("_id", id), new Document("$set", new Document("nombre", "producto")));

		mongoClient.close();
	}
	
	public static void buscarProdcutoBD(Producto productoNew, String id) {
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Producto prod = new Producto("1234", "Nuevo", "Nuevo", "Nuevo", "nuevo", 10, "Nuevo", "png");
		// subirProductosBD(prod);
		//modificarProductosBD(prod, "1234");

	}

}
