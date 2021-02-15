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
        // TODO code application logic here
        File fichero = new File("./src/actividadxmldom/Comida.xml");
        
        XMLconDOM xmldom = new XMLconDOM(fichero);
        
       Document doc =xmldom.abrirXML(fichero);
       boolean repetir = false;
        do {            
            System.out.println("Desea modificar el archivo XML?(s/n)");
            String respuesta = leerTeclado.nextLine().toLowerCase();
            if (respuesta == "s" | respuesta == "n") {
                repetir = false;
                if (respuesta == "s") {
                    System.out.println("Porfavor introduzca el id del registro que quiere cambiar:");
                    int id = leerTeclado.nextInt();
                }else{
                    return ;
                }
                
            } else {
                System.out.println("Respuesta no válida");
                repetir = true;
            }
        } while (repetir);
       String salida =xmldom.recorrerDOMyMostrar(doc);
       
        System.out.println(salida);
        
        
    }
    
}
