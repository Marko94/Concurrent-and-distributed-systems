package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Tabela extends JFrame {

    private int visina, sirina;
    private static boolean playerFrst=true;
    private String matrica[][];
    private String brPartija;
    int pogodakV=0,pogodakH=0,pogodakD1=0, pogodakD2=0;
    int pogodakVO=0,pogodakHO=0,pogodakD1O=0, pogodakD2O=0;

    public Tabela(int n, int visina) {
        this.sirina =n;
        this.visina = visina;
        matrica=new String[visina][n];
        initTable();
    }

    private void initTable(){
        setSize(sirina *10, visina *10);
        JPanel tabela=new JPanel();
        JPanel sacuvaj=new JPanel();
        JPanel panel=new JPanel();
        sacuvaj.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(1,1));
        tabela.setLayout(new GridLayout(visina, sirina));
        for(int i = 0; i< visina; i++){
            for(int j = 0; j< sirina; j++){
                JButton dugme=new JButton();
                dugme.setText("");
                matrica[i][j]=".";
                int k=i,r=j;
                dugme.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(dugme.getText().equals("")){
                            if(playerFrst==true){
                                //dugme.setText("X");
                                dugme.setBackground(Color.MAGENTA);
                                matrica[k][r]="X";
                                playerFrst=false;
                                if(pobeda(k, r)==true){
                                   // System.out.println("Pobednik je MAGENTA");
                                }
                            }else if(playerFrst==false){
                                //dugme.setText("O");
                                dugme.setBackground(Color.CYAN);
                                matrica[k][r]="O";
                                playerFrst=true;
                                if(pobeda(k, r)==true){
                                    //System.out.println("Pobednik je CYAN");
                                }
                            }
                        }
                        //System.out.println(k+"--"+r);
                    }
                });
                tabela.add(dugme);
            }
            System.out.println();
        }
        JButton igraj=new JButton("Igraj");
        JLabel partija=new JLabel("Broj partija");
        JTextField patrijaF=new JTextField();
        igraj.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                brPartija=patrijaF.getText();
                izigraj();

            }
        });
        panel.add(partija,0,0);
        panel.add(patrijaF,0,1);
        panel.add(igraj,0,2);
        sacuvaj.add(tabela,BorderLayout.CENTER);
        sacuvaj.add(panel,BorderLayout.SOUTH);
        //add(tabela);
        add(sacuvaj);
    }

    private boolean pobeda(int k, int r){

        for (int i=-4; i<=4;i++) {
            if ((k+i)>=0 && (k+i)<= visina -1) {
                if(matrica[k + i][r].equals("X")){
                    pogodakH++;
                }else {
                    pogodakH=0;
                }
            }
//            if ((k+i)<visina ) {
//                if(matrica[k + i][r].equals(igrac)){
//                    pogodakV++;
//                }else{
//                    pogodakV=1;
//                }
//            }
            if ((r+i)>=0 && (r+i)<= sirina -1) {
                if (matrica[k][r + i].equals("X")) {
                    pogodakV++;
                }else{
                    pogodakV=0;
                }
            }
//            if ((r+i)<sirina ) {
//                if (matrica[k][r + i].equals(igrac) ) {
//                    pogodakH++;
//                }else{
//                    pogodakH=1;
//                }
//            }
            if ((k+i)>=0 &&(k+i)<= visina - 1 && (r-i)<= sirina -1 && (r-i)>=0) {
                if (matrica[k + i][r - i].equals("X") ) {
                    pogodakD1++;
                }else{
                    pogodakD1=0;
                }
            }
//            if ((k+i)<=sirina && (k+i)>=0 && (r-i)>=0 && (r-i)<=visina) {
//                if (matrica[k + i][r - i].equals(igrac)) {
//                    pogodakD1++;
//                }else{
//                    pogodakD1=1;
//                }
//            }
            if ((k+i)>=0 && (k+i)<= visina -1 && (r+i)>=0 && (r+i)<= sirina -1) {
                System.out.println(matrica[k+i][r+i]);
//                System.out.println(igrac);
                if (matrica[k + i][r + i].equals("X")) {

                    pogodakD2++;
                }else{
                    pogodakD2=0;
                }
            }
//            if ((k+i)<visina && (r+i)<sirina ) {
//                if (matrica[k + i][r + i].equals(igrac)) {
//                    pogodakD2++;
//                }else{
//                    pogodakD2=1;
//                }
//            }
        }
        for (int i=-4; i<=4;i++) {
            if ((k+i)>=0 && (k+i)<= visina -1) {
                if(matrica[k + i][r].equals("O")){
                    pogodakHO++;
                }else {
                    pogodakHO=0;
                }
            }
//            if ((k+i)<visina ) {
//                if(matrica[k + i][r].equals(igrac)){
//                    pogodakV++;
//                }else{
//                    pogodakV=1;
//                }
//            }
            if ((r+i)>=0 && (r+i)<= sirina -1) {
                if (matrica[k][r + i].equals("O")) {
                    pogodakVO++;
                }else{
                    pogodakVO=0;
                }
            }
//            if ((r+i)<sirina ) {
//                if (matrica[k][r + i].equals(igrac) ) {
//                    pogodakH++;
//                }else{
//                    pogodakH=1;
//                }
//            }
            if ((k+i)>=0 &&(k+i)<= visina - 1 && (r-i)<= sirina -1 && (r-i)>=0) {
                if (matrica[k + i][r - i].equals("O") ) {
                    pogodakD1O++;
                }else{
                    pogodakD1O=0;
                }
            }
//            if ((k+i)<=sirina && (k+i)>=0 && (r-i)>=0 && (r-i)<=visina) {
//                if (matrica[k + i][r - i].equals(igrac)) {
//                    pogodakD1++;
//                }else{
//                    pogodakD1=1;
//                }
//            }
            if ((k+i)>=0 && (k+i)<= visina -1 && (r+i)>=0 && (r+i)<= sirina -1) {
                System.out.println(matrica[k+i][r+i]);
//                System.out.println(igrac);
                if (matrica[k + i][r + i].equals("O")) {

                    pogodakD2O++;
                }else{
                    pogodakD2O=0;
                }
            }
//            if ((k+i)<visina && (r+i)<sirina ) {
//                if (matrica[k + i][r + i].equals(igrac)) {
//                    pogodakD2++;
//                }else{
//                    pogodakD2=1;
//                }
//            }
        }

        //System.out.println("Dijagonala 1: " + pogodakD1);
        //System.out.println("Dijagonala 2: " + pogodakD2);
        //System.out.println("Horizontala: " + pogodakH);
        //System.out.println("Veryikala: " + pogodakV);
        if(pogodakV==5 || pogodakH==5 || pogodakD1==5 || pogodakD2==5){
        	System.out.println("Pobednik je MAGENTA");
            return true;
        }
        if(pogodakVO==5 || pogodakHO==5 || pogodakD1O==5 || pogodakD2O==5){
        	System.out.println("Pobednik je CYAN");
            return true;
        }
        return false;
    }

    public String getBrPartija() {
        return brPartija;
    }

    public String izigraj(){
        String matrica1[][]=new String[visina][sirina];
        for (int i=0;i<visina;i++){
            for (int j=0; j<sirina; j++){
                matrica1[i][j]=matrica[i][j];
            }
        }
        //podela
        return "";
    }

    public boolean podela(int novaVis, int novaSir, String matrica1[][]){
        int br=0;
        for (int i=0;i<visina;i++){
            for (int j=0;j<sirina;j++){
                if(matrica1[i][j].equals(".")){
                    br++;
                }
            }
        }
        Random rand=new Random();
        int x=rand.nextInt(novaVis);
        int y=rand.nextInt(novaSir);
        if (matrica1[x][y].equals(".")){
            if(playerFrst==true){
                matrica1[x][y]="X";
                playerFrst=false;
            }else{
                matrica1[x][y]="O";
                playerFrst=true;
            }
        }
        int novoI=rand.nextInt(visina);
        int novoJ=rand.nextInt(sirina);
        if(matrica1[novoI][novoJ].equals(".")) {
            if (playerFrst == true) {
                matrica1[novoI][novoJ] = "X";
                playerFrst = false;
            } else {
                matrica1[novoI][novoJ] = "O";
                playerFrst = true;
            }
        }
        int b=2;
        while (pobeda(novoI,novoJ)!=true || b<=br) {
            novoI = rand.nextInt(visina);
            novoJ = rand.nextInt(sirina);
            if (matrica1[novoI][novoJ].equals(".")) {
                b++;
                if (playerFrst == true) {
                    matrica1[novoI][novoJ] = "X";
                    playerFrst = false;
                } else {
                    matrica1[novoI][novoJ] = "O";
                    playerFrst = true;
                }
            }
        }
        return false;
    }
}

