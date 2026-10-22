/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Progra2prueba;

public class tipoCuenta {
    public static final tipoCuenta AHORRO = new tipoCuenta("AHORRO", 0.03);
    public static final tipoCuenta CHEQUES = new tipoCuenta("CHEQUES", 0.00);
    public static final tipoCuenta PLAZO = new tipoCuenta("PLAZO", 0.05);

    private String nombre;
    private double tasa;

    private tipoCuenta(String nombre, double tasa) {
        this.nombre = nombre;
        this.tasa = tasa;
    }

    public double tasa() {
        return this.tasa;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}