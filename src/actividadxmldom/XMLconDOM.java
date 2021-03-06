/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadxmldom;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.Serializer;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Alumno
 */
public class XMLconDOM {

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

                salida = salida + "\n " + node.getNodeName().toUpperCase() + " " + datos_nodo[0] + ": ";
                salida = salida + "\n " + "Id: " + datos_nodo[0];
                salida = salida + "\n " + "Name: " + datos_nodo[1];
                salida = salida + "\n " + "Price: " + datos_nodo[2];
                salida = salida + "\n " + "Description: " + datos_nodo[3];
                salida = salida + "\n " + "Calories: " + datos_nodo[4] + "\n";
            }
        }
        return salida;
    }

    private String[] procesarXML(Node node) {
        String datos[] = new String[5];
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

    public void Update(Document doc, String id, String name, String price, String description, String calories) {
        try {
            //Creamos un nodelist de etiquetas food
            NodeList nl = doc.getElementsByTagName("food");
            //Recorremos los nodos food
            for (int i = 0; i < nl.getLength(); i++) {
                //creamos un elemento food para cada nodo food para poder modificar el contenido del xml 
                Element food = (Element) nl.item(i);
                // comparamos el id de entrada con los ids del fichero para ubicar el "puntero" en esa posición
                if (food.getElementsByTagName("id").item(0).getTextContent()
                        .equals(id)) {
                    // Cambiamos el name 
                    food.getElementsByTagName("name").item(0)
                            .setTextContent(name);
                    // Cambiamos el price 
                    food.getElementsByTagName("price").item(0)
                            .setTextContent("$" + price);
                    // Cambiamos la description 
                    food.getElementsByTagName("description").item(0)
                            .setTextContent(description);
                    // Cambiamos las calories 
                    food.getElementsByTagName("calories").item(0)
                            .setTextContent(calories);
                }
            }
            //write to file
            guardarDOMcomoFILE(doc, "./src/actividadxmldom/Comida.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    public static void saveXMLContent(Document doc, String path) {
//        try {
//            TransformerFactory tff = TransformerFactory.newInstance();
//            Transformer tf = tff.newTransformer();
//            tf.setOutputProperty(OutputKeys.INDENT, "yes");
//            DOMSource ds = new DOMSource(doc);
//            StreamResult sr = new StreamResult(path);
//            tf.transform(ds, sr);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

    public void guardarDOMcomoFILE(Document doc,String ruta) {
        try {
            //Crea un fichero llamado salida.xml
            File archivo_xml = new File(ruta);
            //Especifica el formato desalida
            OutputFormat format = new OutputFormat(doc);
            //Especifica que la salida esté indentada.
            format.setIndenting(true);
            //Escribe el contenido en el File
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);
            serializer.serialize(doc);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
