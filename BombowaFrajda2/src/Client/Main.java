/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author Tadeusz Golczyj
 */
import GUI.MenuC;
public class Main {
    public static void main(String[] args){
    	/*
    	 * W funkcji main program jako pierwszy uruchamia okno Menu z wyborem dalszych operacji
    	 */
        MenuC guiWindow = new MenuC();
        (guiWindow.kicker = new Thread(guiWindow)).start();
    }

}

