/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.pkg1m5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author ALUMNEDAM
 */
public class A11M5 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        servidor servidor = new servidor();
      
        List<Multiplicacio> llistaTasques = new ArrayList();
        
        for(int i=0;i<10;i++) {
            
            Multiplicacio calcula = new Multiplicacio((int)(Math.random()*10),(int)(Math.random()*10));
            llistaTasques.add(calcula);
        }

        List<Future<Integer>> llistaResultats = new ArrayList<>();
        
        for(int i=0;i<llistaTasques.size();i++){
            Future<Integer> future = servidor.ejecutaTarea(llistaTasques.get(i));
            while (!future.isDone()) {
                System.out.println("Esperando ");
                Thread.sleep(100);
            }
            
            llistaResultats.add(future);
            
        }
        for(int i = 0;i<llistaResultats.size();i++) {
            Future<Integer> resultats = llistaResultats.get(i);
            
            try{
                System.out.println("Resultat multiplicacio" + (i+1) + " es: " + resultats.get());
                
            }catch(InterruptedException | ExecutionException e) {
                
            }
        }

//        List<Callable<String>> callables = new ArrayList<>();
//        List<Future<String>> futures = executor.invokeAll(callables);
//
//        System.out.println(Thread.currentThread().getName() + ", creado: " + new Date());
//        try {
//            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
//        } catch (InterruptedException e) {
//        }
//        System.out.println(Thread.currentThread().getName() + ", terminado: " + new Date());
//
//        for (Future<String> future : futures) {
//            System.out.println("future.get = " + future.get());
        }
    

    public static class Multiplicacio implements Callable<Integer> {

        private int operador1;
        private int operador2;

        public Multiplicacio(int operador1, int operador2) {
            this.operador1 = operador1;
            this.operador2 = operador2;
        }

        @Override
        public Integer call() throws Exception {

               
          return operador1 * operador2;

        }
    }

    public static class servidor {

        private final ThreadPoolExecutor executor;

        public servidor() {

            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        }

        public Future<Integer> ejecutaTarea(Multiplicacio multiplicacio) {
//            executor.execute((Runnable) executor);
            Future<Integer> future = executor.submit(multiplicacio);
            return future;
        }

        public void terminaServidor() {

            executor.shutdown();
        }
    }

    class Tarea implements Callable<String> {

        String message;

        public Tarea(String message) {
            this.message = message;
        }

        @Override
        public String call() throws Exception {
            return message;
        }
    }
}
