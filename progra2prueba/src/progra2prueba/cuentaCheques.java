/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Progra2prueba;

public class cuentaCheques extends cuentabancaria {
    private double limiteSobregiro;

    public cuentaCheques(int numero, String cliente, double tasa, double limiteSobregiro) {
        super(numero, cliente, tasa);
        if (limiteSobregiro < 0) {
            throw new IllegalArgumentException("El sobregiro no puede ser negativo");
        }
        this.limiteSobregiro = limiteSobregiro;
    }

    public void girarCheque(double monto) {
        if (monto<=0) {
            throw new IllegalArgumentException("Monto del cheque invalido");
        }
        if (monto > saldo + limiteSobregiro) {
            throw new IllegalStateException("Fondos insuficientes para girar el cheque");
        }
        saldo -= monto;
        historial.add("CHEQUE: -" + monto);
    }

    @Override
    public void registrarIntereses() {
       
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaCheques");
    }

    @Override
    public String toString() {
        return super.toString() + " Sobregiro "+limiteSobregiro;
    }
}
