package sedifusov2;
public class FuncionTrapecio implements Funcion{
    //Puntos que conforman un trapecio
    private float a;
    private float b;
    private float c;
    private float d;
    private String nombre;
    
    public FuncionTrapecio(String nombre,float a,float b,float c, float d){        
        this.nombre=nombre;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }
    

    @Override
    public float getMembresiaX(float x0) {
       float miu=-1f;
        if(x0<=a){            
            miu = 0f;
        }else if(a<=x0 && x0<=b){            
            miu= (x0-a)/(b-a);
        }else if(b<=x0 && x0<=c){            
            miu=1;
        }else if(c<=x0 && x0<=d){                      
            miu=(d-x0)/(d-c);                        
        }else if(d<=x0){           
            miu =0f;
        }else{
            miu = -1;
        }
        return miu;
    }

    

    @Override
    public String getTermino() {
        return this.nombre;
    }
    
}
