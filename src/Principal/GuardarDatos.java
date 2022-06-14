
package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class GuardarDatos 
{
     List<String> lista = new ArrayList<>();
        
    public GuardarDatos()
    {}
  
    
    public void guardarDatos(String archivo,String datos,Boolean sobreScribir)
    {
         FileWriter fichero= null;
         PrintWriter pw= null;
        
        try
        {
            //Recordar que se puede cambiar la extensión del archivo. (ej: txt, csv).
            fichero= new FileWriter("src/Principal/"+archivo+".txt", sobreScribir); //true: agrega las líneas nuevas al final.
                                                                 //false: sobreescribe el archivo.
            pw= new PrintWriter(fichero);
            
            pw.println(datos);
            
            
        }catch(Exception e)
        {
            System.out.println("error al guardar los datos");
            //e.printStackTrace();
        }finally
        {
            try
            {
                if(fichero!= null)
                {
                    fichero.close();
                }
                
            }catch(Exception e2)
            {
                System.out.println("error al cerrar el archio");
                //e2.printStackTrace();
            }
        }
        
        System.out.println("se guardaron los datos");
    }
 
    public void cargarDatos(String ruta)
    {
       
        File archivo= null;
        FileReader fr= null;
        BufferedReader br= null;
        
        try
        {
            archivo= new File("src/Principal/"+ruta + ".txt");
            fr= new FileReader(archivo);
            br= new BufferedReader(fr);
            
            String linea;
            
            while((linea = br.readLine())!=null)
            {
                
                this.lista.add(linea);
                 
            }
          
          
            
        }catch(IOException e)
        {
           
             System.out.println("error al traer los datos");
             
        }finally
        {
            try
            {
                if(fr != null)
                {
                    fr.close();
                }
            }catch(Exception e2)
            {
                //e2.printStackTrace();
                System.out.println("error al cerrar el archivo cargar");
            }
        }
        
        System.out.println("se cargaron los datos");    
          
    }
  
    
    public List getLista()
    {
    return this.lista;
    
    }
    
    public void printList()
    {
        for (String item : lista) 
        {
             System.out.println(item);
        }
       
    }
    
    public void tamanioLista()
    {
     System.out.println("tamanio lista--"+lista.size());
    }
    
}
