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
    
    public static void main(String args[]) throws IOException {
        //0 Triangular
        //1 Trapezoidal
        //2 TrapezoidalAIzq
        //3 TrapezoidalADer

        //escribirFuncion("Notas", "MALAS", 2, -999, 0, 20, 35);
        //escribirFuncion("Notas", "REGULARES", 0, 0, 30, 50, 65);
        //escribirFuncion("Notas", "BUENAS", 3, 60, 70, 100, 999);
        
        //escribirFuncion("Proyecto", "NOFUNCIONAL", 2, -999, 0, 50, 65);
        //escribirFuncion("Proyecto", "FUNCIONAL", 3, 60, 75, 100, 999);
        
        //escribirFuncion("Aprobacion", "NOAPRUEBA", 2, -999, 0, 30, 45);
        //escribirFuncion("Aprobacion", "CASIAPRUEBA", 0, 0, 35, 50, 70);
        //escribirFuncion("Aprobacion", "APRUEBA", 0, 0, 65, 75, 80);
        //escribirFuncion("Aprobacion", "APRUEBAEXCELENTE", 3, 75, 85, 100, 999);
        
        
        
    }
}
