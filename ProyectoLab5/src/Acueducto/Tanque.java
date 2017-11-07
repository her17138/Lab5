/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase padre que modela toda la informacion general de un tanque cualquiera
 * Tanque.java
 **/
package Acueducto;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Tanque {
    @Id protected ObjectId id;
    protected String numero;
    protected double capacidad;
    protected double porcentajeAguaDisponible;
    protected double cantAguaDisponible;
    protected String region; // region a la cual el tanque esta proveendo agua
    protected Valvula[] valvulas;
    
    public Tanque(){}
    public Tanque(String numero, String[] municipios, long[] habitantes, String region)
    {
        this.numero = numero;
        this.region=region;
        valvulas = new Valvula[10];
        for(int i=0; i<10; i++)
        {
            valvulas[i]= new Valvula(municipios[i], habitantes[i]);
        }
    }
    public void calcularPorcentaje()
    {
        double habitantes = 0;
        for(int i=0; i<10; i++)
        {
            habitantes += valvulas[i].getcant_Habitantes();
        } 
        cantAguaDisponible = capacidad - habitantes;
        if (cantAguaDisponible > 0.0)
        {
            porcentajeAguaDisponible = (cantAguaDisponible * 100.0)/capacidad;
        }
        else 
        {
            porcentajeAguaDisponible =0.0;
            cantAguaDisponible =0.0;
        }
       
    }
    public double llenarTanque()
    {
        porcentajeAguaDisponible = 100.0;
        cantAguaDisponible = capacidad;
        return cantAguaDisponible;
    }
    public String getID()
    {
        return numero;
    }
    public String getRegion()
    {
        return region;
    }
    public Valvula[] getValvulas()
    {
        return valvulas;
    }
    public double getPorcentaje()
    {
        return porcentajeAguaDisponible;
    }
    public double getCapacidad()
    {
        return capacidad;
    }
    public double getAguaRestante()
    {
        return cantAguaDisponible;
    }
    public String toString()
    {
        String hilo = "\n TANQUE NÚMERO "+ numero + "\n CANTIDAD DE AGUA RESTANTE DENTRO DEL DEL TANQUE: "+ cantAguaDisponible;
        hilo += "\n PORCENTAJE DE AGUA RESTANTE: "+ porcentajeAguaDisponible;
        hilo += "\n VÁLVULAS QUE DISPONE: ";
        for (Valvula valv : valvulas)
        {
            hilo += ("\n\t" +valv);
        }
        return hilo;
    }
}
