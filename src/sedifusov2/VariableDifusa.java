package sedifusov2;

import java.util.HashMap;

/**
 *
 * @author carlos
 */
public class VariableDifusa {
    
    private String nombre;
    private int range;
    private String[] terminos;  
    
    Funcion[] funciones;
            
    public VariableDifusa(String nombre,Funcion[] funciones){
        this.nombre=nombre;
        this.funciones=funciones;
        this.terminos = new String[funciones.length];
    }
            
    public String getNombre(){
        return this.nombre;
    }
    
    public String[] getTerminos(){                        
        for (int i = 0; i < funciones.length; i++) {            
            Funcion f = funciones[i];
            String termino = f.getTermino();
            //System.out.println(""+termino);
            terminos[i]=termino;
        }        
        return this.terminos;
    }
    
    public float defusificar(float x0,String conjuntoDifuso){
        //System.out.println("Voy a fusificar "+x0 +" con el conjunto difuso "+conjuntoDifuso);
        float miuMin = 999;
        for (int i = 0; i < funciones.length; i++) {            
            Funcion f = funciones[i];
            //System.out.println("*"+f.getTermino()+ " == "+conjuntoDifuso);
            if(conjuntoDifuso.equals(f.getTermino())){
                //System.out.println("valor "+f.getMembresiaX(x0));                
                if(f.getMembresiaX(x0)<miuMin){
                    miuMin = f.getMembresiaX(x0);
                }
            }
        }
        return miuMin;
    }
    
    
    
    
    
    
   
    
}
