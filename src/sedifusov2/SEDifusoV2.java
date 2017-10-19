package sedifusov2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author carlos
 */
public class SEDifusoV2 extends JFrame{
    
    private ArrayList<Regla> reglas;//Almacena el conjunto de reglas
    private ArrayList<VariableDifusa> variablesDifusas;//Almacena las variables difusas
    private VariableDifusa conjuntoSalida;//Representa el conjunto de salida del SED
    //Variables difusas
    private VariableDifusa entusiasmo;
    private VariableDifusa notas;
    private VariableDifusa examen;
    private VariableDifusa proyecto;
    private VariableDifusa aprobacion;
    
    private float corteNA=0;
    private float corteCA=0;
    private float corteA=0;
    private float corteAE=0;
    
    Funcion aprobacionNoAprueba;
    Funcion aprobacionCasiAprueba;
    Funcion aprobacionAprueba;
    Funcion aprobacionApruebaExcelente;
    
    JDesktopPane desktop;
    JInternalFrame jIAprobacion;
    Plano graficaAprobacion;
    public SEDifusoV2() throws IOException {
        super("Sistema Experto Difuso");
        initGUI();                        
        reglas = new ArrayList<>();
        variablesDifusas = new ArrayList<>();
        
        //Comienza dibujando los subconjuntos
        
//DESDE AQUI MODIFIQUE
        Archivos a = new Archivos();
        ArrayList <Integer> numeros = new ArrayList();
        //_________ ENTUSIASMO ____________
        Plano graficaEntusiasmo = new Plano();
        
        try {
            numeros = a.obtenerDatos("Entusiasmo","NADA");
        } catch(Exception e){}
        Funcion entusiasmoNada;
        if(numeros.get(0)==0){
            entusiasmoNada = new FuncionTriangulo("NADA", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaEntusiasmo.addFigura("NADA",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            entusiasmoNada = new FuncionTrapecio("NADA", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaEntusiasmo.addFigura("NADA",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaEntusiasmo.addFigura("NADA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaEntusiasmo.addFigura("NADA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Entusiasmo","POCO");
        } catch(Exception e){}
        Funcion entusiasmoPoco;
        if(numeros.get(0)==0){
            entusiasmoPoco = new FuncionTriangulo("POCO", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaEntusiasmo.addFigura("POCO",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            entusiasmoPoco = new FuncionTrapecio("POCO", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaEntusiasmo.addFigura("POCO",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaEntusiasmo.addFigura("POCO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaEntusiasmo.addFigura("POCO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Entusiasmo","MEDIO");
        } catch(Exception e){}
        Funcion entusiasmoMedio;
        if(numeros.get(0)==0){
            entusiasmoMedio = new FuncionTriangulo("MEDIO", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaEntusiasmo.addFigura("MEDIO",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            entusiasmoMedio = new FuncionTrapecio("MEDIO", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaEntusiasmo.addFigura("MEDIO",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaEntusiasmo.addFigura("MEDIO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaEntusiasmo.addFigura("MEDIO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Entusiasmo","SUFICIENTE");
        } catch(Exception e){}
        Funcion entusiasmoSuficiente;
        if(numeros.get(0)==0){
            entusiasmoSuficiente = new FuncionTriangulo("SUFICIENTE", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaEntusiasmo.addFigura("SUFICIENTE",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            entusiasmoSuficiente = new FuncionTrapecio("SUFICIENTE", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaEntusiasmo.addFigura("SUFICIENTE",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaEntusiasmo.addFigura("SUFICIENTE",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaEntusiasmo.addFigura("SUFICIENTE",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Entusiasmo","MUCHO");
        } catch(Exception e){}
        Funcion entusiasmoMucho;
        if(numeros.get(0)==0){
            entusiasmoMucho = new FuncionTriangulo("MUCHO", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaEntusiasmo.addFigura("MUCHO",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            entusiasmoMucho = new FuncionTrapecio("MUCHO", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaEntusiasmo.addFigura("MUCHO",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaEntusiasmo.addFigura("MUCHO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaEntusiasmo.addFigura("MUCHO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
            graficaEntusiasmo.setPreferredSize(new Dimension(540, 280));
            //Cortes
            
            
        JInternalFrame jIentusiasmo = new JInternalFrame("Entusiasmo", true, false,false,true);
            jIentusiasmo.add(graficaEntusiasmo);
            //jIentusiasmo.setIconifiable(true);
            jIentusiasmo.setLocation(0, 0);
            jIentusiasmo.pack();
        desktop.add(jIentusiasmo);
            jIentusiasmo.setVisible(true);

        entusiasmo = new VariableDifusa("ENTUSIASMO", new Funcion[]{
            entusiasmoNada,entusiasmoPoco,entusiasmoMedio,entusiasmoSuficiente,entusiasmoMucho
        });        
        variablesDifusas.add(entusiasmo);
        //___________________________________
        //_________ NOTAS _____________
        Plano graficaNotas = new Plano();
        try {
            numeros = a.obtenerDatos("Notas","MALAS");
        } catch(Exception e){}
        Funcion notasMalas;
        if(numeros.get(0)==0){
            notasMalas = new FuncionTriangulo("MALAS", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaNotas.addFigura("MALAS",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            notasMalas = new FuncionTrapecio("MALAS", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaNotas.addFigura("MALAS",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaNotas.addFigura("MALAS",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaNotas.addFigura("MALAS",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Notas","REGULARES");
        } catch(Exception e){}
        Funcion notasRegulares;
        if(numeros.get(0)==0){
            notasRegulares = new FuncionTriangulo("REGULARES", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaNotas.addFigura("REGULARES",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            notasRegulares = new FuncionTrapecio("REGULARES", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaNotas.addFigura("REGULARES",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaNotas.addFigura("REGULARES",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaNotas.addFigura("REGULARES",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Notas","BUENAS");
        } catch(Exception e){}
        Funcion notasBuenas;
        if(numeros.get(0)==0){
            notasBuenas = new FuncionTriangulo("BUENAS", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaNotas.addFigura("BUENAS",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            notasBuenas = new FuncionTrapecio("BUENAS", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaNotas.addFigura("BUENAS",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaNotas.addFigura("BUENAS",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaNotas.addFigura("BUENAS",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        graficaNotas.setPreferredSize(new Dimension(540, 280));
        JInternalFrame jINotas = new JInternalFrame("Notas", true, false,false,true);
            jINotas.add(graficaNotas);
            jINotas.setLocation(540, 0);
            jINotas.pack();
        desktop.add(jINotas);
            jINotas.setVisible(true);
        
        notas = new VariableDifusa("NOTAS", new Funcion[]{notasMalas,
            notasRegulares,notasBuenas});
        variablesDifusas.add(notas);
        //___________________________________________________________________________
        //_________ EXAMEN ______________________
        Plano graficaExamen = new Plano();
        
        try {
            numeros = a.obtenerDatos("Examen","MALO");
        } catch(Exception e){}
        Funcion examenMalo;
        if(numeros.get(0)==0){
            examenMalo = new FuncionTriangulo("MALO", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaExamen.addFigura("MALO",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            examenMalo = new FuncionTrapecio("MALO", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaExamen.addFigura("MALO",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaExamen.addFigura("MALO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaExamen.addFigura("MALO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Examen","REGULAR");
        } catch(Exception e){}
        Funcion examenRegular;
        if(numeros.get(0)==0){
            examenRegular = new FuncionTriangulo("REGULAR", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaExamen.addFigura("REGULAR",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            examenRegular = new FuncionTrapecio("REGULAR", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaExamen.addFigura("REGULAR",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaExamen.addFigura("REGULAR",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaExamen.addFigura("REGULAR",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Examen","BUENO");
        } catch(Exception e){}
        Funcion examenBueno;
        if(numeros.get(0)==0){
            examenBueno = new FuncionTriangulo("BUENO", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaExamen.addFigura("BUENO",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            examenBueno = new FuncionTrapecio("BUENO", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaExamen.addFigura("BUENO",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaExamen.addFigura("BUENO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaExamen.addFigura("BUENO",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
           
            graficaExamen.setPreferredSize(new Dimension(540, 280));
        JInternalFrame jIExamen = new JInternalFrame("Examen", true, false,false,true);
            jIExamen.add(graficaExamen);
            jIExamen.pack();
            jIExamen.setLocation(0, 320);
        desktop.add(jIExamen);
            jIExamen.setVisible(true);
        
        examen = new VariableDifusa("EXAMEN", new Funcion[]{examenMalo,
            examenRegular,examenBueno});
        variablesDifusas.add(examen);
        //__________________________________________
        //________ PROYECTO ________________________
        Plano graficaProyecto = new Plano();
        
        try {
            numeros = a.obtenerDatos("Proyecto","NOFUNCIONAL");
        } catch(Exception e){}
        Funcion proyectoNoFuncional;
        if(numeros.get(0)==0){
            proyectoNoFuncional = new FuncionTriangulo("NOFUNCIONAL", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaProyecto.addFigura("NOFUNCIONAL",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            proyectoNoFuncional = new FuncionTrapecio("NOFUNCIONAL", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaProyecto.addFigura("NOFUNCIONAL",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaProyecto.addFigura("NOFUNCIONAL",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaProyecto.addFigura("NOFUNCIONAL",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Proyecto","FUNCIONAL");
        } catch(Exception e){}
        Funcion proyectoFucional;
        if(numeros.get(0)==0){
            proyectoFucional = new FuncionTriangulo("FUNCIONAL", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaProyecto.addFigura("FUNCIONAL",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            proyectoFucional = new FuncionTrapecio("FUNCIONAL", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaProyecto.addFigura("FUNCIONAL",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaProyecto.addFigura("FUNCIONAL",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaProyecto.addFigura("FUNCIONAL",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
            graficaProyecto.setPreferredSize(new Dimension(540, 280));
        JInternalFrame jIProyecto = new JInternalFrame("Proyecto", true, false,false,true);
            jIProyecto.add(graficaProyecto);
            jIProyecto.pack();
            jIProyecto.setLocation(540, 320);
        desktop.add(jIProyecto);
            jIProyecto.setVisible(true);
        
        proyecto = new VariableDifusa("PROYECTO", new Funcion[]{
            proyectoNoFuncional,proyectoFucional
        });
        variablesDifusas.add(proyecto);
        //__________________________________________
        //_____________ APROBACION _________________
        graficaAprobacion = new Plano();
        
        try {
            numeros = a.obtenerDatos("Aprobacion","NOAPRUEBA");
        } catch(Exception e){}
        if(numeros.get(0)==0){
            aprobacionNoAprueba = new FuncionTriangulo("NOAPRUEBA", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaAprobacion.addFigura("NOAPRUEBA",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            aprobacionNoAprueba = new FuncionTrapecio("NOAPRUEBA", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaAprobacion.addFigura("NOAPRUEBA",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaAprobacion.addFigura("NOAPRUEBA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaAprobacion.addFigura("NOAPRUEBA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Aprobacion","CASIAPRUEBA");
        } catch(Exception e){}
        if(numeros.get(0)==0){
            aprobacionCasiAprueba = new FuncionTriangulo("CASIAPRUEBA", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaAprobacion.addFigura("CASIAPRUEBA",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            aprobacionCasiAprueba = new FuncionTrapecio("CASIAPRUEBA", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaAprobacion.addFigura("CASIAPRUEBA",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaAprobacion.addFigura("CASIAPRUEBA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaAprobacion.addFigura("CASIAPRUEBA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Aprobacion","APRUEBA");
        } catch(Exception e){}
        if(numeros.get(0)==0){
            aprobacionAprueba = new FuncionTriangulo("APRUEBA", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaAprobacion.addFigura("APRUEBA",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            aprobacionAprueba = new FuncionTrapecio("APRUEBA", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaAprobacion.addFigura("APRUEBA",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaAprobacion.addFigura("APRUEBA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaAprobacion.addFigura("APRUEBA",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
        try {
            numeros = a.obtenerDatos("Aprobacion","APRUEBAEXCELENTE");
        } catch(Exception e){}
        if(numeros.get(0)==0){
            aprobacionApruebaExcelente = new FuncionTriangulo("APRUEBAEXCELENTE", numeros.get(2), numeros.get(3), numeros.get(4));
            graficaAprobacion.addFigura("APRUEBAEXCELENTE",new Integer[]{numeros.get(2), numeros.get(3), numeros.get(4)});
        } else {
            aprobacionApruebaExcelente = new FuncionTrapecio("APRUEBAEXCELENTE", numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4));
            if(numeros.get(1)==-999){
                graficaAprobacion.addFigura("APRUEBAEXCELENTE",new Integer[]{0, numeros.get(2), numeros.get(3), numeros.get(4)});
            } else if(numeros.get(4)==999){
                graficaAprobacion.addFigura("APRUEBAEXCELENTE",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), 100});
            } else{
                graficaAprobacion.addFigura("APRUEBAEXCELENTE",new Integer[]{numeros.get(1), numeros.get(2), numeros.get(3), numeros.get(4)});
            }
        }
        numeros.clear();
        
            graficaAprobacion.setPreferredSize(new Dimension(540, 280));
            jIAprobacion = new JInternalFrame("Aprobacion", true, false,false,true);
            jIAprobacion.add(graficaAprobacion);
            jIAprobacion.pack();
            jIAprobacion.setLocation(300, 300);
        desktop.add(jIAprobacion);
            jIAprobacion.setVisible(false);
        
        aprobacion = new VariableDifusa("APROBACION", new Funcion[]{
            aprobacionNoAprueba,aprobacionCasiAprueba,aprobacionAprueba,aprobacionApruebaExcelente
        });
        //____________________________________________Comienza la inferencia
        llenar_indice();
        generarReglas(1,2);
        difusificar();
        getPuntosDeCorte();
        desdifusificar();
        for (int i = 0; i < reglas.size(); i++) {
            String txtRegla="IF ";
            Regla r = reglas.get(i);
            ArrayList<Premisa> antecedentes = r.getAntecedentes();
            for (int j = 0; j < antecedentes.size(); j++) {     
                Premisa p = antecedentes.get(j);
                
                txtRegla+=" "+p.getVariableLinguistica()+" ES "+p.getTerminoLinguistico()+" ("+p.getPeso()+") AND ";
            }
            txtRegla+=" -> "+r.getConsecuenteName();
            System.out.println(""+txtRegla); 
            System.out.println("Pesor R"+i+": "+r.getPeso());
        }
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                dispose();
                InterfazPrincipal ip=new InterfazPrincipal();
                ip.setVisible(true);
            }
        });
        revalidate();
    }
    
    
    private void initGUI(){
        desktop = new JDesktopPane();
        this.getContentPane().add(desktop);
        this.setSize(1024,600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    private void difusificar(){
        int[] input = new int[variablesDifusas.size()];
        for (int i = 0; i < variablesDifusas.size(); i++) {  
            VariableDifusa v = variablesDifusas.get(i);
            input[i]=(Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor para "+v.getNombre())));
        }                
        for (int i = 0; i < reglas.size(); i++) {
            Regla r = reglas.get(i);
            System.out.println("Peso R"+(i+1)+": "+r.getPesoRegla(input));                                                         
            System.out.println("----------------");
        }        
    }
    String [] ind, subconjuntos;
    int cont=-1;
    
        public long obtener_treg(String nombre_archi) throws IOException
    {
        RandomAccessFile archivo=new RandomAccessFile("bh/"+nombre_archi, "r");
        long resultado=0;
        
        for(int i=0; i<15; i++)
        {
            archivo.readChar();
        }
        archivo.readInt();
        archivo.readInt();
        for(int i=0; i<20; i++)
        {
            archivo.readChar();
        }
        resultado=archivo.getFilePointer();
        archivo.close();
        return resultado;
    }
    public void llenar_indice() throws FileNotFoundException, IOException
    {
                RandomAccessFile indice=new RandomAccessFile("bh/indice", "r");
                for(int i=0; i<10;i++)
                {
                    indice.readChar();
                }
                long treg=indice.getFilePointer();
                int nreg=(int) (indice.length()/treg);
                System.out.println("Numero de registros del indice :"+nreg);
                this.ind=new String[nreg];
                this.subconjuntos= new String[nreg];
                String index;
                indice.seek(0);
                char temp;
                int c=0;
                while(indice.getFilePointer()<indice.length())
                {
                    index="";
                    for(int i=0; i<10;i++)
                    {
                        temp=indice.readChar();
                        if(temp!=0)
                        {
                        index+=temp;
                        }
                    }
                    this.ind[c]=index;
                    c++;
                }
                indice.close();
    }
    private void generarReglas(int min, int max) throws IOException{
         cont++;
        StringBuffer buffer=null;
        RandomAccessFile archivo=new RandomAccessFile("bh/"+ind[cont], "rw");
        long treg=obtener_treg(ind[cont]);
        archivo.seek((min-1)*treg);
        long dmax=max*treg;
        char temp;
        while(archivo.getFilePointer()<dmax)
        {
            String sub="";
            for(int i=0; i<15; i++)
            {
                temp=archivo.readChar();
                if(temp!=0)
                {
                    sub+=temp;
                }
            }
            subconjuntos[cont]=sub;
            min=archivo.readInt();
            max=archivo.readInt();
            if(min==0 && max==0)
            {
                //Debe hacer la pregunta para llenar 
                Regla r=new Regla();
                for(int i=0; i<ind.length; i++)
                {
                    switch(ind[i])
                    {
                        case "entusiasmo":
                            r.setAntecedente(new Premisa(entusiasmo,subconjuntos[i]));
                            break;
                            
                            case "examen":
                            r.setAntecedente(new Premisa(examen,subconjuntos[i]));
                            break;
                            
                            case "notas":
                            r.setAntecedente(new Premisa(notas,subconjuntos[i]));
                            break;
                            
                            case "proyecto":
                            r.setAntecedente(new Premisa(proyecto,subconjuntos[i]));
                            break;
                    }
                }
                String te="";
                char tm;
                for(int x=0; x<20;x++)
                {
                    tm=archivo.readChar();
                    if(tm!=0)
                    {
                        te+=tm;
                    }
                }
                r.setConsecuente(new Premisa(aprobacion, te));
                reglas.add(r);
            }
            else
            {
                generarReglas(min, max);
                for(int i=0; i<20; i++)
                {
                    archivo.readChar();
                }
                cont--;
            }
        }   
        
    }
    
    
    
    public void getPuntosDeCorte(){
        for (int i = 0; i < reglas.size(); i++) {
            Regla regla = reglas.get(i);
            System.out.println(""+regla.getConsecuenteName());
            System.out.println(""+regla.getPeso());
            switch(regla.getConsecuenteName()){
                case "NOAPRUEBA":
                    if(regla.getPeso()>corteNA)
                        corteNA = regla.getPeso();
                    break;
                case "CASIAPRUEBA":
                    if(regla.getPeso()>corteCA)
                        corteCA = regla.getPeso();
                    break;
                case "APRUEBA":
                    if(regla.getPeso()>corteA)
                        corteA = regla.getPeso();
                    break;
                case "APRUEBAEXCELENTE":
                    if(regla.getPeso()>corteAE)
                        corteAE = regla.getPeso();
                    break;
            }            
        }
        System.out.println("Corte no aprueba "+corteNA);
        System.out.println("Corte casi aprueba "+corteCA);
        System.out.println("Corte aprueba "+corteA);
        System.out.println("Corte aprueba excelente "+corteAE);
        graficaAprobacion.c1=(int)((corteNA*10)*20);
        graficaAprobacion.c2=(int)((corteCA*10)*20);
        graficaAprobacion.c3=(int)((corteA*10)*20);
        graficaAprobacion.c4=(int)((corteAE*10)*20);
        graficaAprobacion.setIsSalida(true);
        jIAprobacion.setVisible(true);
    }
    
    private void desdifusificar(){        
        float vMembresia=0;
        float dividendo = 0;
        float divisor = 0;
        
        for (int i = 0; i < 100; i++) {
           vMembresia=aprobacionNoAprueba.getMembresiaX(i);
           if(vMembresia>corteNA){
               vMembresia=corteNA;
           }
           
           divisor += (vMembresia*i);
           dividendo += vMembresia;
           
        }
        
        for (int i = 0; i < 100; i++) {
           vMembresia=aprobacionCasiAprueba.getMembresiaX(i);
           if(vMembresia>corteCA){
               vMembresia=corteCA;
           }
           
           
           
           divisor += (vMembresia*i);
           dividendo += vMembresia;
           
            
        }
        
        for (int i = 0; i < 100; i++) {
           vMembresia=aprobacionAprueba.getMembresiaX(i);
           if(vMembresia>corteA){
               vMembresia=corteA;
           }
            
           divisor += (vMembresia*i);
           dividendo += vMembresia;
           
        }
                
        for (int i = 0; i < 100; i++) {
            vMembresia=aprobacionApruebaExcelente.getMembresiaX(i);
           if(vMembresia>corteAE){
               vMembresia=corteAE;
           }
           divisor += (vMembresia*i);
           dividendo += vMembresia;
           
        }
        System.out.println(""+divisor+"/"+dividendo);
        JOptionPane.showMessageDialog(this, "Resultado:"+(divisor/dividendo));
    }
    
    
    
    
    public static void main(String[] args) throws IOException {
        new SEDifusoV2();
    }
    
    
    
}
