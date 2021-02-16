/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadxmldom;

import java.io.File;
import java.util.Scanner;
import org.w3c.dom.Document;

/**
 *
 * @author Alumno
 */
public class ActividadXMLDOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leerTeclado = new Scanner(System.in);
        // Creamos el fichero 
        File fichero = new File("./src/actividadxmldom/Comida.xml");
        //Instanciamos la clase XMLconDOM donde estan todos los métodos para trabajar con archivos XML
        XMLconDOM xmldom = new XMLconDOM(fichero);
        //Creamos un objeto Document que recoja la respuesta de abrirXML 
        Document doc = xmldom.abrirXML(fichero);
        // Creamos un string que recoja la respuesta de recorrerDOM... el cual le hemos enviado el objeto Document
        String salida = xmldom.recorrerDOMyMostrar(doc);
        // Mostramos la salida antes de las modificaciones
        System.out.println(salida);
        // Llamamos al método Update que como sunombre indica, su función 
        // es modificar el archivo xml y tambien llama a un método "savexml" que guarda los cambios 
        xmldom.Update(doc, "4", "Potato Omeltette", "6.7", "Typical spanish meal", "780");
        // Creamos un string que recoja la respuesta de recorrerDOM... el cual le hemos enviado el objeto Document
        String salidaModificada = xmldom.recorrerDOMyMostrar(doc);
        // Mostramos el archivo xml con los cambios guardados
        System.out.println("    Archivo modificado:");
        System.out.println(salidaModificada);

    }

}
