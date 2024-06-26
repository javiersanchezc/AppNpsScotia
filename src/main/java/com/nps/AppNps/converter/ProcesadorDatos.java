/*    */ package com.nps.AppNps.converter;


/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class ProcesadorDatos {
/*    */   public static void main(String[] args) {
/*  9 */     String rutaArchivo = "C:/data/callllbaackcabecera.txt";
/*    */     try {
/* 12 */       String datosEnUnaFila = procesarArchivo(rutaArchivo);
/* 13 */       System.out.println(datosEnUnaFila);
/* 14 */     } catch (IOException e) {
/* 15 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private static String procesarArchivo(String rutaArchivo) throws IOException {
/* 20 */     StringBuilder datosEnUnaFila = new StringBuilder();
/* 22 */     BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
/*    */     try {
/*    */       String linea;
/* 24 */       while ((linea = br.readLine()) != null) {
/* 26 */         String[] tokens = linea.split(",");
/* 29 */         for (String token : tokens)
/* 30 */           datosEnUnaFila.append(token.trim()).append(","); 
/*    */       } 
/* 33 */       br.close();
/*    */     } catch (Throwable throwable) {
/*    */       try {
/*    */         br.close();
/*    */       } catch (Throwable throwable1) {
/*    */         throwable.addSuppressed(throwable1);
/*    */       } 
/*    */       throw throwable;
/*    */     } 
/* 36 */     if (datosEnUnaFila.length() > 0)
/* 37 */       datosEnUnaFila.deleteCharAt(datosEnUnaFila.length() - 1); 
/* 40 */     return datosEnUnaFila.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\javie\OneDrive\Desktop\final4\AppNps-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\nps\AppNps\converter\ProcesadorDatos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */