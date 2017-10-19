/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sedifusov2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Jordi Ibañez
 */
public class Archivos {
    
    public ArrayList obtenerDatos(String nombre, String id) throws IOException{
        RandomAccessFile archivo = new RandomAccessFile(nombre, "r");
        ArrayList <Integer> numeros = new ArrayList();
        int x1,x2,x3,x4,tipo;
        String cadena = "";
        archivo.seek(0);
        while(archivo.getFilePointer() != archivo.length()){
            char caracter[]=new char[20],temp1;
            for(int c=0;c<caracter.length;c++){
                temp1=archivo.readChar();
                caracter[c]=temp1;
            }
            String texto = new String(caracter).replace('\0',' ');
            for (int x=0; x < texto.length(); x++) {
                if (texto.charAt(x) != ' '){
                    cadena += texto.charAt(x);
                }
            }
            tipo = archivo.readInt();
            x1 = archivo.readInt();
            x2 = archivo.readInt();
            x3 = archivo.readInt();
            x4 = archivo.readInt();
            if(cadena.equals(id)){
                numeros.add(tipo);
                numeros.add(x1);
                numeros.add(x2);
                numeros.add(x3);
                numeros.add(x4);
            }
            cadena = "";
        }
        archivo.close();
        return numeros;
    }
    
    public static void escribirFuncion(String nombre, String id, int tipo, int x1, int x2, int x3, int x4) throws IOException{
        RandomAccessFile archivo = new RandomAccessFile(nombre, "rw");
        StringBuffer buffer=null;
        archivo.seek(archivo.length());
        buffer = new StringBuffer(id);
        buffer.setLength(20);
        archivo.writeChars(buffer.toString());
        archivo.writeInt(tipo);
        archivo.writeInt(x1);
        archivo.writeInt(x2);
        archivo.writeInt(x3);
        archivo.writeInt(x4);
        archivo.close();
    }
    
    public void modificarDatos(String nombre, String id, int tipo, int x1, int x2, int x3, int x4) throws IOException{
        RandomAccessFile archivo = new RandomAccessFile(nombre, "rw");
        StringBuffer buffer=null;
        String cadena= "";
        archivo.seek(0);
        while(archivo.getFilePointer() != archivo.length()){
            char caracter[]=new char[20],temp1;
            for(int c=0;c<caracter.length;c++){
                temp1=archivo.readChar();
                caracter[c]=temp1;
            }
            String texto = new String(caracter).replace('\0',' ');
            for (int x=0; x < texto.length(); x++) {
                if (texto.charAt(x) != ' '){
                    cadena += texto.charAt(x);
                }
            }
            if(cadena.equals(id)){
                archivo.writeInt(tipo);
                archivo.writeInt(x1);
                archivo.writeInt(x2);
                archivo.writeInt(x3);
                archivo.writeInt(x4);
            } else {
                archivo.readInt();
                archivo.readInt();
                archivo.readInt();
                archivo.readInt();
                archivo.readInt();
            }
            cadena = "";
        }
        
        archivo.close();
    }
    
    public void traslape_entusiasmo(int porcentaje) throws IOException {
        int nada2,poco2,medio2,suficiente2,mucho2, traslape2,nuevo_nada, nuevo_poco, nuevo_poco2, nuevo_medio, nuevo_medio2, nuevo_suficiente, nuevo_suficiente2, nuevo_mucho;
        ArrayList<Integer> nada = new ArrayList();
        nada = obtenerDatos("Entusiasmo","NADA");
        ArrayList<Integer> poco = new ArrayList();
        poco = obtenerDatos("Entusiasmo","POCO");
        ArrayList<Integer> medio = new ArrayList();
        medio = obtenerDatos("Entusiasmo","MEDIO");
        ArrayList<Integer> suficiente = new ArrayList();
        suficiente = obtenerDatos("Entusiasmo","SUFICIENTE");
        ArrayList<Integer> mucho = new ArrayList();
        mucho = obtenerDatos("Entusiasmo","MUCHO");
        
        if(nada.get(0)==0){
            nada2 = nada.get(4)-nada.get(2);
            traslape2 = nada2*porcentaje/100;
            nuevo_nada = nada.get(4)+traslape2;
            modificarDatos("Entusiasmo", "NADA", nada.get(0), nada.get(1), nada.get(2), nada.get(3), nuevo_nada);
        } else if(nada.get(0)==1){
            nada2 = nada.get(4)-nada.get(1);
            traslape2 = nada2*porcentaje/100;
            nuevo_nada = nada.get(4)+traslape2;
            modificarDatos("Entusiasmo", "NADA", nada.get(0), nada.get(1), nada.get(2), nada.get(3), nuevo_nada);
        } else if(nada.get(0)==2){
            nada2 = nada.get(4)-nada.get(2);
            traslape2 = nada2*porcentaje/100;
            nuevo_nada = nada.get(4)+traslape2;
            modificarDatos("Entusiasmo", "NADA", nada.get(0), nada.get(1), nada.get(2), nada.get(3), nuevo_nada);
        } else if(nada.get(0)==3){
            nada2 = nada.get(3)-nada.get(1);
            traslape2 = nada2*porcentaje/100;
            nuevo_nada = nada.get(3)+traslape2;
            if(!(nuevo_nada<100)){
                nuevo_nada = 100;
            }
            modificarDatos("Entusiasmo", "NADA", nada.get(0), nada.get(1), nada.get(2), nuevo_nada, nada.get(4));
        }
        
        if(poco.get(0)==0){
            poco2 = poco.get(4)-poco.get(2);
            traslape2 = poco2*porcentaje/100;
            nuevo_poco = poco.get(2)-traslape2;
            nuevo_poco2 = poco.get(4)+traslape2;
            modificarDatos("Entusiasmo", "POCO", poco.get(0), poco.get(1), nuevo_poco, poco.get(3), nuevo_poco2);
        } else if(poco.get(0)==1){
            poco2 = poco.get(4)-poco.get(1);
            traslape2 = poco2*porcentaje/100;
            nuevo_poco = poco.get(1)-traslape2;
            nuevo_poco2 = poco.get(4)+traslape2;
            modificarDatos("Entusiasmo", "POCO", poco.get(0), nuevo_poco, poco.get(2), poco.get(3), nuevo_poco2);
        } else if(poco.get(0)==2){
            poco2 = poco.get(4)-poco.get(2);
            traslape2 = poco2*porcentaje/100;
            nuevo_poco = poco.get(1)-traslape2;
            nuevo_poco2 = poco.get(4)+traslape2;
            if(!(nuevo_poco>0)){
                nuevo_poco = 0;
            }
            modificarDatos("Entusiasmo", "POCO", poco.get(0), poco.get(1), nuevo_poco, poco.get(3), nuevo_poco2);
        } else if(poco.get(0)==3){
            poco2 = poco.get(3)-poco.get(1);
            traslape2 = poco2*porcentaje/100;
            nuevo_poco = poco.get(1)-traslape2;
            nuevo_poco2 = poco.get(4)+traslape2;
            if(!(nuevo_poco2<100)){
                nuevo_poco2 = 100;
            }
            modificarDatos("Entusiasmo", "POCO", poco.get(0), nuevo_poco, poco.get(2),nuevo_poco2, poco.get(4));
        }
        
        if(medio.get(0)==0){
            medio2 = medio.get(4)-medio.get(2);
            traslape2 = medio2*porcentaje/100;
            nuevo_medio = medio.get(2)-traslape2;
            nuevo_medio2 = medio.get(4)+traslape2;
            modificarDatos("Entusiasmo", "MEDIO", medio.get(0), medio.get(1), nuevo_medio, medio.get(3), nuevo_medio2);
        } else if(medio.get(0)==1){
            medio2 = medio.get(4)-medio.get(1);
            traslape2 = medio2*porcentaje/100;
            nuevo_medio = medio.get(1)-traslape2;
            nuevo_medio2 = medio.get(4)+traslape2;
            modificarDatos("Entusiasmo", "MEDIO", medio.get(0), nuevo_medio, medio.get(2), medio.get(3), nuevo_medio2);
        } else if(medio.get(0)==2){
            medio2 = medio.get(4)-medio.get(2);
            traslape2 = medio2*porcentaje/100;
            nuevo_medio = medio.get(1)-traslape2;
            nuevo_medio2 = medio.get(4)+traslape2;
            if(!(nuevo_medio>0)){
                nuevo_medio = 0;
            }
            modificarDatos("Entusiasmo", "MEDIO", medio.get(0), medio.get(1), nuevo_medio, medio.get(3), nuevo_medio2);
        } else if(medio.get(0)==3){
            medio2 = medio.get(3)-medio.get(1);
            traslape2 = medio2*porcentaje/100;
            nuevo_medio = medio.get(1)-traslape2;
            nuevo_medio2 = medio.get(4)+traslape2;
            if(!(nuevo_medio2<100)){
                nuevo_medio2 = 100;
            }
            modificarDatos("Entusiasmo", "MEDIO", medio.get(0), nuevo_medio, medio.get(2),nuevo_medio2, medio.get(4));
        }
        
        if(suficiente.get(0)==0){
            suficiente2 = suficiente.get(4)-suficiente.get(2);
            traslape2 = suficiente2*porcentaje/100;
            nuevo_suficiente = suficiente.get(2)-traslape2;
            nuevo_suficiente2 = suficiente.get(4)+traslape2;
            modificarDatos("Entusiasmo", "SUFICIENTE", suficiente.get(0), suficiente.get(1), nuevo_suficiente, suficiente.get(3), nuevo_suficiente2);
        } else if(suficiente.get(0)==1){
            suficiente2 = suficiente.get(4)-suficiente.get(1);
            traslape2 = suficiente2*porcentaje/100;
            nuevo_suficiente = suficiente.get(1)-traslape2;
            nuevo_suficiente2 = suficiente.get(4)+traslape2;
            modificarDatos("Entusiasmo", "SUFICIENTE", suficiente.get(0), nuevo_suficiente, suficiente.get(2), suficiente.get(3), nuevo_suficiente2);
        } else if(suficiente.get(0)==2){
            suficiente2 = suficiente.get(4)-suficiente.get(2);
            traslape2 = suficiente2*porcentaje/100;
            nuevo_suficiente = suficiente.get(1)-traslape2;
            nuevo_suficiente2 = suficiente.get(4)+traslape2;
            if(!(nuevo_suficiente>0)){
                nuevo_suficiente = 0;
            }
            modificarDatos("Entusiasmo", "SUFICIENTE", suficiente.get(0), suficiente.get(1), nuevo_suficiente, suficiente.get(3), nuevo_suficiente2);
        } else if(suficiente.get(0)==3){
            suficiente2 = suficiente.get(3)-suficiente.get(1);
            traslape2 = suficiente2*porcentaje/100;
            nuevo_suficiente = suficiente.get(1)-traslape2;
            nuevo_suficiente2 = suficiente.get(4)+traslape2;
            if(!(nuevo_suficiente2<100)){
                nuevo_suficiente2 = 100;
            }
            modificarDatos("Entusiasmo", "SUFICIENTE", suficiente.get(0), nuevo_suficiente, suficiente.get(2),nuevo_suficiente2, suficiente.get(4));
        }
        
        if(mucho.get(0)==0){
            mucho2 = mucho.get(4)-mucho.get(2);
            traslape2 = mucho2*porcentaje/100;
            nuevo_mucho = mucho.get(2)-traslape2;
            modificarDatos("Entusiasmo", "MUCHO", mucho.get(0), mucho.get(1), nuevo_mucho, mucho.get(3),  mucho.get(4));
        } else if(mucho.get(0)==1){
            mucho2 = mucho.get(4)-mucho.get(1);
            traslape2 = mucho2*porcentaje/100;
            nuevo_mucho = mucho.get(1)-traslape2;
            modificarDatos("Entusiasmo", "MUCHO", mucho.get(0), nuevo_mucho, mucho.get(2), mucho.get(3), mucho.get(4));
        } else if(mucho.get(0)==2){
            mucho2 = mucho.get(4)-mucho.get(2);
            traslape2 = mucho2*porcentaje/100;
            nuevo_mucho = mucho.get(1)-traslape2;
            if(!(nuevo_mucho>0)){
                nuevo_mucho = 0;
            }
            modificarDatos("Entusiasmo", "MUCHO", mucho.get(0), mucho.get(1), nuevo_mucho, mucho.get(3), mucho.get(4));
        } else if(mucho.get(0)==3){
            mucho2 = mucho.get(3)-mucho.get(1);
            traslape2 = mucho2*porcentaje/100;
            nuevo_mucho = mucho.get(1)-traslape2;
            modificarDatos("Entusiasmo", "MUCHO", mucho.get(0), nuevo_mucho, mucho.get(2),mucho.get(3), mucho.get(4));
        }
        
    }

}
