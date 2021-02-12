/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadxmldom;

import java.io.File;
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
        // TODO code application logic here
        File fichero = new File("./src/actividadxmldom/Comida.xml");
        
        XMLconDOM xmldom = new XMLconDOM(fichero);
        
       Document doc =xmldom.abrirXML(fichero);
       
       String salida =xmldom.recorrerDOMyMostrar(doc);
       
        System.out.println(salida);
        
        
    }
    
}
