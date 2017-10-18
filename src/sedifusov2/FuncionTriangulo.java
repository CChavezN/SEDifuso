package sedifusov2;

/**
 *
 * @author carlos
 */
public class FuncionTriangulo implements Funcion{
    
    private  float a;
    private float b;
    private float c;
    private String nombre;
    
    public FuncionTriangulo(String nombre,int a,int b,int c){       
        this.nombre=nombre;
        this.a=a;
        this.b=b;
        this.c=c;
    }

    @Override
    public float getMembresiaX(float x0) {
        float miu = -1f;
        if(x0<=a){
            miu = 0f;            
        }else if(a<=x0 && x0<=b){
            miu = (x0-a)/(b-a);            
        }else if(b<=x0 && x0<=c){
            miu = (c-x0)/(c-b);             
        }else if(c<=x0){
            miu=0f;             
        }
        return miu;
    }

    @Override
    public String getTermino() {
        return this.nombre;
    }
    
}
