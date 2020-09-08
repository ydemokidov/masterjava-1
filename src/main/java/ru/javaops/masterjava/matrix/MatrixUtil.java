package ru.javaops.masterjava.matrix;

import ru.javaops.masterjava.service.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {
    // TODO implement parallel multiplication matrixA*matrixB
    public static int[][] concurrentMultiply(int[][] matrixA, int[][] matrixB, ExecutorService executor) throws InterruptedException, ExecutionException {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        CountDownLatch latch = new CountDownLatch(matrixSize);

        for (int j = 0; j<matrixSize; j++) {
            final int col = j;
            final int[] columnB =new int[matrixSize];
            for (int k = 0; k < matrixSize; k++) {
                columnB[k] = matrixB[k][col];
            }
            executor.execute(()-> {
                //print("columnB",columnB,col);
                final int[] columnC = new int[matrixSize];
                for (int row = 0; row < matrixSize; row++) {
                    final int[] rowA = matrixA[row];
                    //print("rowA",rowA,col);
                    int sum = 0;
                    for (int a = 0; a < matrixSize; a++) {
                        sum += rowA[a] * columnB[a];
                    }
                    columnC[row] = sum;
                }
                //print("columnC",columnC,col);
                for(int c=0; c<matrixSize;c++){
                    //System.out.println("matrixC["+c+"]["+col+"] = " +columnC[c]);
                    matrixC[c][col] = columnC[c];
                }

                latch.countDown();
                //System.out.println("Worker "+col+" finished");
            });
        }
        latch.await();
        //executor.shutdown();
        return matrixC;
    }

    private static void print(String name,int[] arr,int col){
        StringBuffer sb = new StringBuffer();
        sb.append(col+" : ");
        sb.append(name+" : ");
        for(int el : arr){
            sb.append(el);
            sb.append(" ");
        }
        sb.append(";");
        System.out.println(sb.toString());
    }

    // TODO optimize by https://habrahabr.ru/post/114797/
    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        int[] thatColumn = new int[matrixSize];

        try {
            for (int j = 0; ; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    thatColumn[k] = matrixB[k][j];
                }
                //print("columnB",thatColumn,j);
                for (int i = 0; i < matrixSize; i++) {
                    int[] thisRow = matrixA[i];
                    //print("rowA",thisRow,j);
                    int sum = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        sum += thisRow[k] * thatColumn[k];
                    }
                    //System.out.println("matrixC["+i+"]["+j+"] = "+sum);
                    matrixC[i][j] = sum;
                }
            }
        }
        catch(IndexOutOfBoundsException ignored){
            //do nothing
        }
        return matrixC;
    }

    public static int[][] create(int size) {
        int[][] matrix = new int[size][size];
        Random rn = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean compare(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
