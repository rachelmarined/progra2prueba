/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Progra2prueba;

/*
Rachel zoe Maradiaga 22551071
*/
import java.util.Calendar;
public class cuentaAhorro extends cuentabancaria{
private Calendar ultimaModif;
private int retirosRealizados =0;
private static final int max_retiros_mes = 3;
    public cuentaAhorro(int numero, String cliente, double tasa) {
        super(numero, cliente, tasa);
        this.ultimaModif = Calendar.getInstance();
    }
    
    public boolean status(){
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.add(Calendar.MONTH,-6);
        return ultimaModif.after(fechaActual);
    }
    public void aplicarIntereses(){
        if(status()){
            double intereses=saldo*tasa;
            depositar(intereses);
            historial.add("intereses aplicados*"+intereses);
        }
    }
}




