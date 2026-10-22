/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Progra2prueba;

public class banca {
    private cuentabancaria[] cuentas = new cuentabancaria[20];
    private int cantidadCuentas = 0;

    public void agregarCuenta(cuentabancaria c) {
        if (cantidadCuentas >= cuentas.length) {
            throw new IllegalStateException("Banco lleno");
        }
        try {
            buscarPorNumero(c.getNumero());
            throw new IllegalArgumentException("La cuenta ya existe");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("La cuenta ya existe")) {
                throw e;
            }
        }
        cuentas[cantidadCuentas] = c;
        cantidadCuentas++;
    }

    
    public cuentabancaria buscarPorNumero(int numero) {
        return buscarAux(numero, 0);
    }

    private cuentabancaria buscarAux(int numero, int i) {
        if (i >= cantidadCuentas) {
            throw new IllegalArgumentException("No se encontr la cuenta " + numero);
        }
        if (cuentas[i].getNumero() == numero) {
            return cuentas[i];
        }
        return buscarAux(numero, i + 1);
    }

    
    public void ordenarPorSaldo() {
        if (cantidadCuentas > 1) {
            ordenarRec(cantidadCuentas - 1);
        }
    }

    private void ordenarRec(int n) {
        if (n == 0) return;
        for (int i = 0; i < n; i++) {
            if (cuentas[i].getSaldo() > cuentas[i+1].getSaldo()) {
                cuentabancaria aux = cuentas[i];
                cuentas[i] = cuentas[i+1];
                cuentas[i+1] = aux;
            }
        }
        ordenarRec(n - 1);
    }

    public void transferir(int origenNum, int destinoNum, double monto) {
        if (origenNum == destinoNum) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }
        cuentabancaria origen = buscarPorNumero(origenNum);
        cuentabancaria destino = buscarPorNumero(destinoNum);

        if (!origen.retirar(monto)) {
            throw new IllegalStateException("Fondos insuficientes para transferir");
        }

        try {
            destino.depositar(monto);
        } catch (Exception e) {
            origen.reintegrar(monto);
            throw new RuntimeException("Fallo el deposito, se devolvio el dinero");
        }
    }

    public void aplicarInteresesGlobal() {
        for (int i = 0; i < cantidadCuentas; i++) {
            try {
                cuentas[i].registrarIntereses();
            } catch (Exception e) {
                System.out.println("Error en cuenta" + e.getMessage());
            }
        }
    }

 
    public double saldoTotalDelBanco() {
        return sumaSaldoAux(0);
    }

    private double sumaSaldoAux(int i) {
        if (i >= cantidadCuentas) return 0.0;
        return cuentas[i].getSaldo() + sumaSaldoAux(i + 1);
    }

    public void generarReporte() {
        ordenarPorSaldo();
        System.out.println("\n--- REPORTE DEL BANCO ---");
        for (int i = 0; i < cantidadCuentas; i++) {
            cuentas[i].quienSoy();
            System.out.println(cuentas[i].toString());
            System.out.println("-------------------");
        }
        System.out.println("SALDO TOTAL: " + saldoTotalDelBanco());
    }
}
