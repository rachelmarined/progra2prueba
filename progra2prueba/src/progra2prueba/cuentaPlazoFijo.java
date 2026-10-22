/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Progra2prueba;

import java.util.Calendar;

public class cuentaPlazoFijo extends cuentabancaria {
    private Calendar findePlazo;
    private double intereses;

    public cuentaPlazoFijo(int numero, String cliente, double tasa) {
        super(numero, cliente, tasa);
        this.intereses = 0.0;
        this.findePlazo = null;
    }

    public void setPlazo(int anio, int mes, int dia) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.set(anio, mes-1, dia);
            cal.getTime(); 
            this.findePlazo = cal;
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha de plazo invalida");
        }
    }

    public boolean estaVencida() {
        if (findePlazo == null) return false;
        Calendar hoy = Calendar.getInstance();
        return !hoy.before(findePlazo);
    }

    @Override
    public void registrarIntereses() {
        double ganancia = saldo*tasa;
        if (!estaVencida()) {
            intereses += ganancia;
            historial.add("INTERESES ACUMULADOS+"+ ganancia);
        } else {
            saldo += ganancia;
            historial.add("Intereses aplicados+"+ ganancia);
        }
    }

    @Override
    public boolean retirar(double monto) {
        if (monto<=0) throw new IllegalArgumentException("Monto inválido");
        
        if (!estaVencida()) {
            if (monto>intereses) return false;
            intereses-=monto;
            historial.add("RETIRO DE INTERESES-"+ monto);
            return true;
        } else {
            if (intereses > 0) {
                saldo+=intereses;
                intereses = 0;
            }
            
            if (saldo>=monto) {
                saldo-=monto;
                historial.add("Retiro-"+monto);
                return true;
            }
            historial.add("Intento de retiro fallido"+monto);
            return false;
        }
    }

    public void reintegrar(double monto) {
        if (!estaVencida()) {
            intereses+=monto;
            historial.add("REINTEGRO A INTERESES+"+monto);
        } else {
            saldo+=monto;
            historial.add("Reintegro+"+monto);
        }
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaPlazoFijo");
    }

    @Override
    public String toString() {
        return super.toString()+"Intereses acumulados"+intereses;
    }
}
