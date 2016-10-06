/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.pkg1m5;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ALUMNEDAM
 */
public class Tarea implements Runnable {

     
    private String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        
        System.out.println(Thread.currentThread().getName() + ", creado: " + new Date());
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ", terminado: " + new Date());
}
}
