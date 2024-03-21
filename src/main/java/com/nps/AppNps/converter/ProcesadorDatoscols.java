package com.nps.AppNps.converter;


/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class ProcesadorDatoscols {
/*    */   public static void main(String[] args) {
/*  9 */     String rutaArchivo = "C:/data/cols.txt";
/*    */     try {
/* 11 */       String datosEnUnaColumna = procesarArchivo(rutaArchivo);
/* 12 */       System.out.println(datosEnUnaColumna);
/* 13 */     } catch (IOException e) {
/* 14 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private static String procesarArchivo(String rutaArchivo) throws IOException {
/* 19 */     StringBuilder datosEnUnaColumna = new StringBuilder();
/* 21 */     BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
/*    */     try {
/*    */       String linea;
/* 23 */       if ((linea = br.readLine()) != null) {
/* 25 */         String[] tokens = linea.split(",");
/* 28 */         for (String token : tokens)
/* 29 */           datosEnUnaColumna.append(token.trim()).append("\n"); 
/*    */       } 
/* 32 */       br.close();
/*    */     } catch (Throwable throwable) {
/*    */       try {
/*    */         br.close();
/*    */       } catch (Throwable throwable1) {
/*    */         throwable.addSuppressed(throwable1);
/*    */       } 
/*    */       throw throwable;
/*    */     } 
/* 34 */     return datosEnUnaColumna.toString().trim();
/*    */   }
}

