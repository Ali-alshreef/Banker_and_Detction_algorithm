package com.company;

import java.util.Scanner;

public class Main {
    Scanner i = new Scanner(System.in);
    private int need[][], allocate[][],max[][], avail[][], np, nr, ali[][],  r1, r2, r3,v1,v2,v3;

    private void input() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Bank Algorithm ...... : ");
        System.out.print("Enter no. of processes : ");
        np = sc.nextInt();
        System.out.print("Enter no. of recours : ");
        nr = sc.nextInt();


        System.out.print("Enter no. of R1 : ");
        r1 = sc.nextInt();

        System.out.print("Enter no. of R2 : ");
        r2 = sc.nextInt();

        System.out.print("Enter no. of R3 : ");
        r3 = sc.nextInt();


        need = new int[np][nr];
        max = new int[np][nr];
        allocate = new int[np][nr];
        avail = new int[np][nr];
        ali = new int[np][nr];


        System.out.println("Enter Max matrix :-");
        for (int i = 0; i < np; i++) {
            System.out.println("p" + i + "=");
            for (int j = 0; j < nr; j++) {
                if (j == 0) {
                    System.out.print("X= ");
                    max[i][j] = sc.nextInt();
                    if (max[i][0] > r1) {
                        System.out.print("p" + i + " The Max X Biger from Recorse X Avalibal in System Then : ");
                        System.out.println(max[i][0] - r1 + " Recourse Not Allocated ");
                    }
                }
                if (j == 1) {
                    System.out.print("Y= ");
                    max[i][j] = sc.nextInt();
                    if (max[i][1] > r2) {
                        System.out.print("p" + i + " The Max Y Biger from Recorse Y Avalibal in System Then : ");
                        System.out.println(max[i][1] - r2 + " Recourse Not Allocated ");
                    }
                }
                if (j == 2) {
                    System.out.print("Z= ");
                    max[i][j] = sc.nextInt();
                    if (max[i][2] > r3) {
                        System.out.print("p" + i + " The Max Z Biger from Recorse Z Avalibal in System Then : ");
                        System.out.println(max[i][2] - r3 + " Recourse Not Allocated ");
                    }
                }


            }
        }


        System.out.println("Enter Allocation matrix :-");

        for (int i = 0; i < np; i++) {
            System.out.println("p" + i + "=");
            for (int j = 0; j < nr; j++) {
                if ( j==0){
                    System.out.print("X= ");
                    allocate[i][j] = sc.nextInt();
                    if (  allocate[i][j] > max[i][j]  ) {
                        System.out.println(" The Alocated 'X' Biger of Max 'X' The Need 'X' is Negative : Soory please Enter again : ");
                        allocate[i][j] = sc.nextInt();
                    }
                }
                if ( j==1){
                    System.out.print("Y= ");
                    allocate[i][j] = sc.nextInt();
                    if (allocate[i][j] > max[i][j]) {
                        System.out.println(" The Alocated 'Y' Biger of Max 'Y' The Need 'Y' is Negative : Soory please Enter again : ");
                        allocate[i][j] = sc.nextInt();
                    }
                }
                if ( j==2){
                    System.out.print("Z= ");
                    allocate[i][j] = sc.nextInt();
                    if (allocate[i][j] > max[i][j]) {
                        System.out.println(" The Alocated 'Z' Biger of Max 'Z' The Need 'Z' is Negative : Soory please Enter again : ");
                        allocate[i][j] = sc.nextInt();
                    }
                }

            }
        }
        for (int n = 0; n < np; n++) {
            v1+= allocate[n][0] ;
            v2+= allocate[n][1] ;
            v3+= allocate[n][2] ;
        }
        v1 = r1 - v1;
        v2= r2 - v2;
        v3 = r3 - v3;

        if (v1<0 || v2 <0 || v3 <0){
            rebetinput();
        } else {
            Choose();
        }


    }

    void  rebetinput(){
        System.out.println(" One Or more Avalibal <0 !!  NO Recourse  in System Now ");
        System.out.println(" Repeat The Input Enter '8' Else  Exit !! ");
        int x=i.nextInt();
        if (x==8){
            v1= 0;
            v2=0;
            v3=0;
            input();
        }
    }

    void calc_avail(){

        avail[0][0] = v1;
        avail[0][1] = v2;
        avail[0][2] = v3;
        System.out.println();
        System.out.println(" The Avalibol is : ");
        System.out.println(" X= " + avail[0][0] + " ,   Y= " + avail[0][1] + " ,   Z= " + avail[0][2]);
        System.out.println();

    }

    public int[][] calc_need() {
        for (int i = 0; i < np; i++)
            for (int j = 0; j < nr; j++) {
                need[i][j] = max[i][j] - allocate[i][j];
            }
        return need;
    }


    private boolean check(int i) {
        for (int j = 0; j < nr; j++)
            if (avail[0][j] < need[i][j])
                return false;

        return true;
    }


    public void isSafe() {
        calc_avail();
        calc_need();
        boolean done[] = new boolean[np];
        int j = 0;
        while (j < np) {
            boolean allocated = false;
            for (int i = 0; i < np; i++)
                if (!done[i] && check(i)) {  //trying to allocate
                    for (int k = 0; k < nr; k++) {
                        avail[0][k] = avail[0][k] + allocate[i][k];
                          ali[i][k] = avail[0][k];
                    }
                    System.out.print("---> " + "P" + i);
                    allocated = done[i] = true;
                    j++;
                }

            if (!allocated) break;
        }
        if (j == np) {
            System.out.print("  IS Safe State");
            System.out.println("\n  The System Is Safe State  ^_^");
            result();


        } else {
            System.out.println();
            System.out.println(" The System Is UNSafe State  ن_ن");
            result();

        }

        rebet();
}

void rebet(){
    System.out.println(" Use Banker algorithm :: 1");
    System.out.println(" Use Detection algorithm :: 2");
    System.out.println(" Exit Enter ::0 ");
    int in = i.nextInt() ;
    if ( in==1){

        isSafe();

    } else if (in==2){
        algorithm_indetection();


    }else  if ( in==0){

        System.out.println(" Thank you !! ");

    }
}
    void Choose(){
        Scanner i = new Scanner(System.in);
        System.out.println(" Now you can Choose :: ( Banker Enter ' 1 ') OR (Detection Enter ' 2 ' :: ");
        int choose = i.nextInt();
        if (choose == 1) {
            isSafe();
        } else if (choose == 2) {
            algorithm_indetection();
        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        m.input();

    }

    //////////////  Detection Algorithm ////////////////



    public void algorithm_indetection() {
       calc_avail();
        calc_need();
        boolean done[] = new boolean[np];
        int j = 0;
        while (j < np) {  //until all process allocated
            boolean allocated = false;
            for (int i = 0; i < np; i++)
                if (!done[i] && check(i)) {  //trying to allocate
                    for (int k = 0; k < nr; k++) {
                        avail[0][k] = avail[0][k] + allocate[i][k];
                        ali[i][k] = avail[0][k];
                    }
                    System.out.print("---> " + "P" + i);
                    allocated = done[i] = true;
                    j++;
                }

            if (!allocated) break;
        }
        if (j== np) {
            System.out.print("   IS Safe State");
            System.out.println("\n  The System Is Safe State  ^_^");
            result ();

        } else {

            result ();
            System.out.println(" The System Is UNSafe State  ن_ن");
            System.out.println();
            for (int i = 0; i < np; i++) {
                if (done[i] == false) {
                    System.out.println("p"+i+" in Deadlock ::");
                    if (need[i][0] > avail[0][0]) {
                        System.out.println(" Becouse Recorc ( X =" + need[i][0] + " ) Bigar from Avalibal  (X =" + avail[0][0] + " )");
                        System.out.print(" Number of instnce from Recourse  X= ");
                        System.out.print(need[i][0] - avail[0][0] );
                        System.out.println("  Not Allocated");
                    }
                    if (need[i][1] > avail[0][1]) {
                        System.out.println(" Becouse Recorc ( Y =" + need[i][1] + " ) Bigar from Avalibal  (Y =" + avail[0][1] + " )");
                        System.out.print(" Number of instnce from Recourse  Y= ");
                        System.out.print(need[i][1] - avail[0][1] );
                        System.out.println("  Not Allocated");

                    }
                        if (need[i][2] > avail[0][2]) {
                            System.out.println(" Becouse Recorse ( Z =" + need[i][2] + " ) Bigar from Avalibal  (Z=" + avail[0][2] + " )");
                            System.out.print(" Number of instnce from Recourse  Z= ");
                            System.out.print(need[i][2] - avail[0][2] );
                            System.out.println("  Not Allocated");

                        }

              }
            }
        }

        rebet();
    }

        public void result ()
        {
            System.out.println();
            System.out.println("           Max matrix                       allocate matrix                   need matrix                      avail matrix       ");
            System.out.println("      |   X   |   Y  |   Z  |          |   X   |   Y  |   Z  |          |   X   |   Y  |   Z  |          |   X   |   Y  |   Z  |  ");
            System.out.println("     ------------------------          -----------------------          -----------------------          ------------------------      ");

            for (int s = 0; s < np; s++) {
                System.out.print("p" + s + "=  ");

                for (int k = 0; k < nr; k++) {
                    System.out.print(" |   " + max[s][k] + " ");
                }
                System.out.print(" |          ");

                for (int k = 0; k < nr; k++) {
                    System.out.print(" |   " + allocate[s][k] + " ");
                }
                System.out.print(" |          ");

                for (int k = 0; k < nr; k++) {
                    System.out.print(" |   " + need[s][k] + " ");
                }
                System.out.print(" |          ");

                for (int k = 0; k < nr; k++) {
                    System.out.print(" |   " + ali[s][k] + " ");
                }
                System.out.println(" |          ");

                System.out.println("     ------------------------          -----------------------          -----------------------          ------------------------      ");
            }
        }
}
