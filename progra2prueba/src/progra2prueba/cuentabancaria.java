/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Progra2prueba;

/*
rachel Maradiaga 22551071
*/
import java.util.ArrayList;
import java.util.Calendar;
      
public class cuentabancaria {
    protected int numero;
    protected String cliente;
    protected double saldo;
    protected double tasa;
    protected Calendar creacion;
    protected ArrayList<String> historial;

    public cuentabancaria(int numero, String cliente, double tasa){  
        this.numero = numero;
        this.cliente = cliente;
        this.tasa = tasa; 
        this.saldo = 500;
        this.creacion = Calendar.getInstance(); 
        this.historial = new ArrayList<>(); 
        this.historial.add("Cuenta creada con saldo inicial de 500 Lps.");
    }
    
    public void depositar(double monto){
        try{
            if(monto <= 0){
                System.out.println("saldo insuficiente");
            }else{
                saldo += monto;
                historial.add("Deposito" + monto);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean retirar(double monto){  
        if(monto > 0 && saldo >= monto){ 
            saldo -= monto; 
            historial.add("Retiro-" + monto);
            return true;
        }
        historial.add("Intento de retiro fallido" + monto); 
        return false; 
    }

   
    public void reintegrar(double monto) {
        saldo += monto;
        historial.add("Reintegro/Reversa+" + monto);
    }

   
    public void registrarIntereses() {
        double intereses = saldo * tasa;
        if (intereses > 0) {
            saldo += intereses;
            historial.add("Intereses aplicados+" + intereses);
        }
    }

    public void quienSoy() {
        System.out.println("CuentaBancaria (clase base)");
    }

    public void mostrarHistorial(){ 
         System.out.println(" ===== HISTORIAL DE " + cliente.toUpperCase() + " ====== ");
         for(String registro : historial) {
             System.out.println(registro);
         }
    } 

    public int getNumero() {
        return numero;
    }

    public double getSaldo(){
        return saldo;
    }

    public String toString(){
        return String.format("Cuenta #%d , %s , $%.2f", numero, cliente, saldo);
    }

    public void transferir(double monto, cuentabancaria destino){
        if(this.retirar(monto)){
            destino.depositar(monto);
            this.historial.add("Transferencia enviada a #" + destino.numero + monto);
        }
        else{
            System.out.println("transferencia fallida!");
        }
    }
}
