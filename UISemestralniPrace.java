/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semestralni.prace;

import java.util.Scanner;

/** <h1>UI pro výběr ze dvou úloh</h1>
 * Program slouží k výberu ze dvou uloh zadaných pro predmět ALG1
 * <p>
 * Na výběr jsou 1. vánocní úloha, ktéra pomocí symbolů nakreslí sněhuláka a 2.
 * určení matice vektrů zdali je ortogonální
 * <p>
 * @author vyhli
 */
public class UISemestralniPrace {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int konec = 0;
        while (konec == 0) {
            System.out.println("Vyber si co chces spustit 1-vanocni uloha, 2-semestralni prace, 3-konec");
            int uloha = sc.nextInt();
            switch (uloha) {
                case 1 ->
                    christmasJanVyhlidko();
                case 2 ->
                    isOrthogonal();
                case 3 ->
                    konec = 1;
            }
        }

    }
/**
 * tato metoda ma za ukol načíst nejprve pocet vektorů a pak samotný
 * prvky vektrů, ze kterých pak zjistí zdali tvoří spolu ortogonalní
 * matici a když ano udělá z matice normalizovanou.
 */
    static void isOrthogonal() {       
        int pocetVektoru;
        System.out.println("Pocet vektoru");
        while ((pocetVektoru = sc.nextInt()) > 0) {
            System.out.println("Zadej vektory");
            boolean ortogonalni = true;
            double vychylka = 0.000001;
            double skalarniSoucin = 0;
            double[] maticeDelek = new double[pocetVektoru];
            double[][] maticeVektoru = new double[pocetVektoru][pocetVektoru];
            double delkaVektoru = 0;
            //nacteni vektoru do dvourozmerneho pole a zaroven kontrola jestli delky vektoru nejsou nulove
            for (int i = 0; i < pocetVektoru; i++) {
                for (int j = 0; j < pocetVektoru; j++) {
                    maticeVektoru[i][j] = sc.nextDouble();
                    delkaVektoru = delkaVektoru + Math.pow(maticeVektoru[i][j], 2);
                }
                maticeDelek[i] = Math.sqrt(delkaVektoru);
                if (maticeDelek[i] < vychylka) {
                    ortogonalni = false;
                }
                delkaVektoru = 0;
            }
            //kontrola skalarnich soucinu vektoru 
            for (int i = 0; i < maticeVektoru.length - 1 && ortogonalni; i++) {
                for (int j = i + 1; j < maticeVektoru.length && ortogonalni; j++) {
                    for (int k = 0; k < maticeVektoru[i].length && ortogonalni; k++) {
                        skalarniSoucin = skalarniSoucin + (maticeVektoru[i][k] * maticeVektoru[j][k]);
                    }
                    if (Math.abs(skalarniSoucin) > vychylka) {
                        ortogonalni = false;
                    }
                    skalarniSoucin = 0;
                }
            }
            //output
            if (ortogonalni) {
                System.out.println("System je ortogonalni");
                System.out.println("Normalizovany system");
                for (int i = 0; i < maticeVektoru.length; i++) {
                    for (int j = 0; j < maticeVektoru[i].length; j++) {
                        maticeVektoru[i][j] = maticeVektoru[i][j] / maticeDelek[i];
                        //System.out.print(" "+maticeVektoru[i][j]);
                        System.out.format("%.3f ", maticeVektoru[i][j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("System neni ortogonalni");
            }
        }
    }

    static void christmasJanVyhlidko() {

        int polomer = 8;
        int sirka = 8;
        int pomer1; //int pomer2;
        int pozice;
        int navic = 0;
        if (sirka >= 15 && polomer >= 15) {
            navic++;
        }
        int horniPozice = sirka / 2 + sirka % 2;
        int pomocna = sirka - horniPozice;

        pomocna = pomocna / 2;
        int mezera = pomocna;
        int pocitani;
        int n = 0;
        int rozdil = 0;

        pomer1 = 1;

        /*if (sirka > polomer) {
            pomer2 = sirka / polomer;
        } else {
            pomer2 = 1;
        }*/
        while (n < 3) {
            pozice = mezera;
            pomocna = mezera;
            pocitani = 1;
            if (n > 0) {
                sirka = sirka + 2; //(pomer*2);
                rozdil = rozdil + 2; //(pomer*2);
                polomer = polomer + pomer1;
            }
            for (int i = 1; i < polomer + 1; i++) {
                /* if (i == polomer /2 && pomer2>1)
                {
                  
                    for (int l = 1; l <= pomer2; l++)
                    {
                        for (int j = 1; j < sirka; j++)
                            System.out.print("* ");
                    }
                }*/
                for (int j = -sirka + (rozdil * 2); j < sirka + 1; j++) {
                    if (j < 1) {
                        for (int k = 1; k <= pomer1; k++) {
                            System.out.print(" ");
                        }
                    }
                    if (j >= 1) {
                        if (pozice > 0) {
                            for (int k = 1; k <= pomer1; k++) {
                                System.out.print("  ");     // prvnihorni mezery
                            }
                            pozice = pozice - 1;
                        } else if (j > (sirka - pomocna)) {
                            for (int k = 1; k <= pomer1; k++) {
                                System.out.print("  ");
                            }
                        } else if (j == mezera + 1 && i == polomer / 3 + polomer % 2 && n == 0 || j == sirka - mezera && i == polomer / 3 + polomer % 2 && n == 0) {
                            for (int k = 1; k <= pomer1; k++) {
                                System.out.print("\u2599");
                                if (sirka % 2 == 0) {
                                    System.out.print(" ");
                                }
                            }

                        } else if (n == 0 && i == polomer / 2 && j >= sirka / 2 && j <= (sirka / 2) + sirka % 2 + 1) {
                            for (int k = 1; k <= pomer1; k++) {
                                System.out.print("@ ");
                            }
                        } else if (n == 0 && i == 2 * polomer / 3 && j > (sirka / 4) && j <= (sirka - mezera)) {
                            for (int k = 1; k <= pomer1; k++) {
                                System.out.print("_ ");
                            }
                        } else if (n == 0 && i == 2 * polomer / 3 && j == (sirka / 4)) {
                            System.out.print("\\ ");
                        } else if (n == 0 && i == 2 * polomer / 3 && j == (sirka - mezera) + 1) {
                            System.out.print("/ ");
                        } else if (n >= 1 && (i >= 2 * polomer / 3 && i <= 2 * polomer / 3 + navic || i >= polomer / 3 && i <= polomer / 3 + navic) && j >= sirka / 2 - navic && j <= (sirka / 2) + sirka % 2 + 1 + navic) {
                            System.out.print("8 ");
                        } else if (i > (polomer - mezera) && j < pocitani + 1 || i > (polomer - mezera) && j > sirka - pocitani) {
                            for (int k = 1; k <= pomer1; k++) {
                                System.out.print("  ");
                            }
                        } else {
                            if (i <= mezera - 1 + navic && n == 0) {
                                System.out.print("^ ");
                            } else//System.out.print("\u2588");
                            {
                                for (int k = 1; k <= pomer1; k++) {
                                    System.out.print("* ");
                                }
                            }
                        }
                    }
                }
                if (i > (polomer - mezera)) {
                    pocitani++;
                }
                pomocna = pomocna - 1;
                pozice = pomocna;

                System.out.println();
            }
            n++;
        }
    }
}
