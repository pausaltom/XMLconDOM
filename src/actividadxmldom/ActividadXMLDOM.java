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

        Document doc = xmldom.abrirXML(fichero);
        boolean repetir = false;
        boolean repetirId = false;
        String idString = "";
        
        String salida = xmldom.recorrerDOMyMostrar(doc);

        System.out.println(salida);
        do {
            
            System.out.println("Desea modificar el archivo XML?(s/n)");
            String respuesta = leerTeclado.nextLine().toLowerCase();
            
            if (respuesta.equals("s") | respuesta.equals("n")) {
                repetir = false;
                if (respuesta.equals("s")) {
                    do {
                        System.out.println("Porfavor introduzca el id del registro que quiere cambiar:");
                        int id = leerTeclado.nextInt();
                        leerTeclado.nextLine();
                        repetirId = !xmldom.comprobarIdValida(doc, id);
                        idString = Integer.toString(id);
                    } while (repetirId);
                    System.out.println("Porfavor introduzca el name modificado: ");
                    String newName = leerTeclado.nextLine();
                    System.out.println("Porfavor introduzca el price modificado: ");
                    String newPrice = leerTeclado.nextLine();
                    System.out.println("Porfavor introduzca la description modificado: ");
                    String newDescription = leerTeclado.nextLine();
                    System.out.println("Porfavor introduzca las calories modificado: ");
                    String newCalories = leerTeclado.nextLine();
                    
                    xmldom.Update(doc, idString, newName, newPrice, newDescription, newCalories);
                } else {
                    return;
                }

            } else {
                System.out.println("Respuesta no válida");
                repetir = true;
            }
        } while (repetir);
        String salidaModificada = xmldom.recorrerDOMyMostrar(doc);

        System.out.println(salidaModificada);

    }

}
