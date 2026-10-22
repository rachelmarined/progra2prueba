/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Progra2prueba;

/*
rachel maradiaga 22551071
*/
import java.util.Scanner;

public class Progra2prueba {

    public static void main(String[] args) {
        //TODO code application logic here
        Scanner sc = new Scanner(System.in);
        banca miBanco = new banca();
        int op = 0;

        do {
            System.out.println("\n=== BANCO FINANZAS DEL VALLE ===");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Transferir");
            System.out.println("5. Aplicar intereses");
            System.out.println("6. Ver reporte y saldo total");
            System.out.println("7. Ver historial");
            System.out.println("8. Girar cheque");
            System.out.println("9. Salir");
            System.out.print("Elija una opción: ");

            try {
                op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1:
                        System.out.print("Tipo 1. Ahorro, 2. Cheques, 3. Plazo Fijo");
                        int t = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Numero de cuenta");
                        int num = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Cliente");
                        String cli = sc.nextLine();

                        if (t == 1) {
                            miBanco.agregarCuenta(new cuentaAhorro(num, cli, 0.03));
                            System.out.println("Ahorro creada");
                        } else if (t == 2) {
                            System.out.print("Limite de sobregiro");
                            double sob = sc.nextDouble();
                            sc.nextLine();
                            miBanco.agregarCuenta(new cuentaCheques(num, cli, 0.0, sob));
                            System.out.println("Cheques creada");
                        } else if (t == 3) {
                            cuentaPlazoFijo pf = new cuentaPlazoFijo(num, cli, 0.05);
                            System.out.print("Año vencimiento: ");
                            int anio = sc.nextInt();
                            System.out.print("Mes");
                            int mes = sc.nextInt();
                            System.out.print("Dia");
                            int dia = sc.nextInt();
                            sc.nextLine();
                            pf.setPlazo(anio, mes, dia);
                            miBanco.agregarCuenta(pf);
                            System.out.println("¡Plazo Fijo creada");
                        }
                        break;

                    case 2:
                        System.out.print("Numero de cuenta");
                        int nDep = sc.nextInt();
                        System.out.print("Monto a depositar");
                        double mDep = sc.nextDouble();
                        sc.nextLine();
                        miBanco.buscarPorNumero(nDep).depositar(mDep);
                        System.out.println("Depósito exitoso");
                        break;

                    case 3:
                        System.out.print("Número de cuenta");
                        int nRet = sc.nextInt();
                        System.out.print("Monto a retirar");
                        double mRet = sc.nextDouble();
                        sc.nextLine();
                        boolean ex = miBanco.buscarPorNumero(nRet).retirar(mRet);
                        if (ex) System.out.println("Retiro exitoso");
                        else System.out.println("Fondos insuficientes");
                        break;

                    case 4:
                        System.out.print("Origen");
                        int orig = sc.nextInt();
                        System.out.print("Destino");
                        int dest = sc.nextInt();
                        System.out.print("Monto");
                        double mt = sc.nextDouble();
                        sc.nextLine();
                        miBanco.transferir(orig, dest, mt);
                        System.out.println("Transferencia hecha");
                        break;

                    case 5:
                        miBanco.aplicarInteresesGlobal();
                        System.out.println("Intereses aplicados");
                        break;

                    case 6:
                        miBanco.generarReporte();
                        break;

                    case 7:
                        System.out.print("Numero de cuenta: ");
                        int nh = sc.nextInt();
                        sc.nextLine();
                        miBanco.buscarPorNumero(nh).mostrarHistorial();
                        break;

                    case 8:
                        System.out.print("Numero de cuenta de cheques");
                        int nCh = sc.nextInt();
                        System.out.print("Monto del cheque");
                        double mCh = sc.nextDouble();
                        sc.nextLine();
                        cuentabancaria cTemp = miBanco.buscarPorNumero(nCh);
                        if (cTemp instanceof cuentaCheques) {
                            ((cuentaCheques) cTemp).girarCheque(mCh);
                            System.out.println("Cheque girado");
                        } else {
                            System.out.println("Esa no es de cheques");
                        }
                        break;

                    case 9:
                        System.out.println("adios");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error"+e.getMessage());
            }

        } while (op!=9);

    }
}
