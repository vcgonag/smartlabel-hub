import {
  ScrollView,
  StyleSheet,
  Text,
  View,
  Pressable,
  Image,
} from 'react-native';
import { useState, useEffect } from 'react';
import getData from './src/services/services';

export default App = () => {
  const [array, setArray] = useState([]);

  const getDatos = async () => {
    console.log('HOLAA');
    try {
      const resp = await getData(
        `http://192.168.0.15:8080/productos/listarProductos`
      );
      console.log(resp);
      let aux = [];
      for (let i = 9; i < 20; i++) {
        aux.push(resp.productos[i]);
      }
      setArray(aux);
    } catch (e) {
      console.log('ERROR', e);
    }
  };

  return (
    <View style={styles.container}>
      <Pressable style={styles.button} onPress={() => getDatos()}>
        <Text style={styles.buttonText}>PULSA</Text>
      </Pressable>
      <ScrollView contentContainerStyle={{ paddingTop: 100 }}>
        {array.map((element, index) => (
          <View
            style={{ alignItems: 'center', margin: 20 }}
            key={index.toString()}>
            <Text style={styles.paragraph}>
              {element.marca} - {element.nombre}
            </Text>
            <Image
              style={styles.image}
              source={{
                uri: element.imagenPNG,
              }}
            />
            <Image
              style={styles.imageBarCode}
              source={{
                uri: element.barcodePNG,
              }}
            />
          </View>
        ))}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    //justifyContent: 'center',
    //alignContent:'center',
    alignItems: 'center',
    marginVertical: 80,
    backgroundColor: 'white',
  },
  image: {
    width: 250,
    height: 300,
  },
  imageBarCode: {
    width: 300,
    height: 150,
  },
  paragraph: {
    //margin: 24,
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  button: {
    backgroundColor: 'black',
    width: 120,
    padding: 15,
    borderRadius: 10,
    alignItems: 'center',
    marginTop: 40,
    position: 'absolute',
    zIndex: 1000,
    elevation: 15, // Android
  },
  buttonText: {
    color: 'white',
    fontWeight: '700',
    fontSize: 14,
  },
  containerButtons: {
    //flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
});
