/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase principal que realiza los calculos y guarda los datos relevantes
 * Acueducto.java
 **/
package Acueducto;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
public class Acueducto 
{
    @Id private ObjectId id;
    private ArrayList<Tanque> contenedores;
    
    public Acueducto()
    {
        contenedores = new ArrayList<>();
    }
    /**
     * Método para crear un nuevo tanque cilindrico.
     * @param ID: identificacion del tanque
     * @param municipios: todos los municipios a lso que alimenta el tanque
     * @param habitantes: todos los habitantes (o el número en promedio) de un municipio
     * @param region: region a la que alimenta el tanque
     * @param radio: radio del tanque
     * @param altura: altura del tanque
     * 
     */
    public void nuevoCilindrico(String ID, String[] municipios, long[] habitantes, String region, double radio, double altura)
    {
        Tanque nuevo = new Cilindrico(ID, municipios, habitantes, region, radio, altura);
        contenedores.add(nuevo);
    }
    /**
     * Método para crear un nuevo tanque cubico
     * @param ID identificacion del tanque
     * @param municipios: todos los municipios a lso que alimenta el tanque
     * @param habitantes: todos los habitantes (o el número en promedio) de un municipio
     * @param region: region a la que alimenta el tanque
     * @param lado: en el caso sea un tanque cubico
     * 
     */
    public void nuevoCubico(String ID, String[] municipios, long[] habitantes, String region, double lado)
    {
        Tanque nuevo = new Cubico(ID, municipios, habitantes, region, lado);
        contenedores.add(nuevo);   
    }
    /**
     * Método para crear un nuevo tanque ortogonal
     * @param ID identificacion del tanque
     * @param municipios: todos los municipios a lso que alimenta el tanque
     * @param habitantes: todos los habitantes (o el número en promedio) de un municipio
     * @param region: region a la que alimenta el tanque
     * @param ancho: ancho del tanque
     * @param alto alto 
     * @param largo largo
     * 
     */
    public void nuevoOrtogonal(String ID, String[] municipios, long[] habitantes, String region, double largo, double ancho, double alto)
    {
        Tanque nuevo = new Ortogonal(ID, municipios, habitantes, region, largo, ancho, alto);
        contenedores.add(nuevo);   
    }
    /**
     * Método para abrir la válvula de algún tanque
     * @param IDtanque : id del tanque
     * @param municipio municipio al que alimenta el tanque
     * @param fecha: en la que se está abriendo la valvula
     * @param contenedores: array que contiene la información de todos los tanques. 
     * @return Valvula[] array de todas las valvulas de un tanque
     * 
     */
    public Valvula[] abrirValvula(String IDtanque, String municipio, String fecha, ArrayList<Tanque> contenedores)
    {
        Valvula[] valvulas = null;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(IDtanque))
            {
                for(int i=0; i<10; i++)
                {
                    if (tanque.getValvulas()[i].getMunicipio().equals(municipio))   
                    {
                        tanque.getValvulas()[i].setEstado(true);
                        tanque.getValvulas()[i].fechaAbierto(fecha);
                    }
                }
                valvulas = tanque.getValvulas();
            }
        }
        return valvulas;
    }
    /**
     * Método para cerrar la válvula de algún tanque
     * @param IDtanque: id del tanque
     * @param municipio: municipio al que alimenta el tanque
     * @param fecha: en la que se está errando la valvula
     * @param contenedores: array que contiene la información de todos los tanques. 
     * @return Valvula[] array de todas las valvulas de un tanque
     * 
     */
    public Valvula[] cerrarValvula(String IDtanque, String municipio, String fecha, ArrayList<Tanque> contenedores)
    {
        Valvula[] valvulas = null;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(IDtanque))
            {
                for(int i=0; i<10; i++)
                {
                    if (tanque.getValvulas()[i].getMunicipio().equals(municipio))   
                    {
                        tanque.getValvulas()[i].setEstado(false);
                        tanque.getValvulas()[i].fechaCerrado(fecha);
                    }
                }
            }
            valvulas = tanque.getValvulas();
        }
        return valvulas;
    }
    /**
     * Método para llenar un tanque con agua
     * @param ID identificacion del tanque
     * @param contenedores array que contiene la información de todos los tanques. 
     * @return double capacidad del tanque
     */
    public double llenarTanque(String ID, ArrayList<Tanque> contenedores)
    {
        double capacidadTanque = 0;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(ID))
            {
                capacidadTanque = tanque.llenarTanque();
            }
        }
        return capacidadTanque;
    }
}
