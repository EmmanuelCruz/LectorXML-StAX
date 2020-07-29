package fciencias.edatos.ejemploStAX;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Ejemplo de lectura de XML usando StAX.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020
 * @since Estructuras de datos 2021-1
 */
public class LectorStAX{
	
	/**
	* Realiza la lectura de un archivo XML.
	* @param nombre el nombre del archivo a leer.
	* @throws XMLStreamException si ocurre algún problema
	* durante la lectura del flujo de datos.
	* @throws FileNotFoundException - Si no existe el archivo
	* XML a parsear.
	*/
	public void lee(String nombre) throws XMLStreamException,
		FileNotFoundException{
		XMLInputFactory entrada = XMLInputFactory.newInstance();
		XMLStreamReader lector = entrada.createXMLStreamReader(
			new FileInputStream(new File(nombre)));

		System.out.println("Los libros de la biblioteca son:");

		while(lector.hasNext()){
			switch(lector.next()){

				// Atributos de las etiquetas
				case XMLStreamConstants.START_ELEMENT:
					if(lector.getLocalName().equalsIgnoreCase("Libro")){
						
						/* Se acceden a los valores de los atributos almacenados en 
						* la etiqueta perteneciente a un libro. */
						System.out.println("\nLibro: "+lector.getAttributeValue(0)+
							"\nAutor: "+lector.getAttributeValue(1));

						// Se obtiene un atributo extra en caso de existir.
						String atributoExtra = lector.getAttributeValue(2);

						if(atributoExtra!=null && !atributoExtra.equals("")){
							System.out.println("Información extra: "+atributoExtra);
						}
					} else if(lector.getLocalName().equalsIgnoreCase("Seccion")){

						// Se acceden a los valores de los atributos almacenados en cada sección.
						System.out.print("    - Sección "+lector.getAttributeValue(0)
							+": ");
					}
					break;

				// Texto dentro de las etiquetas
				case XMLStreamConstants.CHARACTERS:
					String texto = lector.getText().trim();
					if(!texto.equals("") && !texto.equals("\n")){
						System.out.println(texto);
					}
					break;
			}
		}
	}

	public static void main(String[] args) throws XMLStreamException,
		FileNotFoundException {
		LectorStAX lector = new LectorStAX();
		lector.lee("biblioteca.xml");
	}
}
