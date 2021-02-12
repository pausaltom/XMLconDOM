/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadxmldom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Alumno
 */
public class XMLconDOM {

    private File fichero;

    public XMLconDOM(File fichero) {
        this.fichero = fichero;
    }

    

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    public Document abrirXML(File fichero) {
        Document doc = null;//Representa al árbol DOM
        try {
            //Se crea un objeto DocumentBuiderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Indica que el modelo DOM no debe contemplar los comentarios //que tenga el XML.
            factory.setIgnoringComments(true);
            //Ignora los espacios en blanco que tenga el documento
            factory.setIgnoringElementContentWhitespace(true);
            //Se crea un objeto DocumentBuilder para cargar en él la //estructura de árbol DOM a partir del XML
            //seleccionado.
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Interpreta (parsear) el documento XML (file) y genera el DOM //equivalente.
            doc = builder.parse(fichero);
            //Ahora doc apunta al árbol DOM listo para ser recorrido.
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        Node node;
        int contador=1;
        //Obtiene el primero nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();
        
        //Obtiene una lista de nodos con todos los nodos hijo del raíz.
        NodeList nodelist = raiz.getChildNodes();
        
        //Procesa los nodos hijo
        for (int i = 0; i < nodelist.getLength(); i++) {
            node = nodelist.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Es un nodo libro
                datos_nodo = procesarXML(node);
                
                salida = salida + "\n " + node.getNodeName().toUpperCase()+" "+(contador)+": ";
                salida = salida + "\n " + "Name: " + datos_nodo[0];
                salida = salida + "\n " + "Price: " + datos_nodo[1];
                salida = salida + "\n " + "Description: " + datos_nodo[2];
                salida = salida + "\n " + "Calories: " + datos_nodo[3]+"\n";
                contador++;
            }
        }
        return salida;
    }

    private String[] procesarXML(Node node) {
        String datos[] = new String[4];
        Node ntemp = null;
        int contador = 0;
//        System.out.println("node "+node);
        //Obtiene los hijos del Libro (titulo y autor)
        NodeList nodos = node.getChildNodes();
//            System.out.println("Nodos "+nodos);
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
//            System.out.println("ntemp "+ntemp);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                //IMPORTANTE: para obtener el texto con el título y autor se accede al nodo //TEXT hijo de ntemp y se
                //saca su valor.
                
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
//                System.out.println("datos"+datos[contador]);
                contador++;
            }
        }
        return datos;
    }

}
