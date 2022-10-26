import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int totalProcessadores = Runtime.getRuntime().availableProcessors();
        Semaphore mutex = new Semaphore(1);

        testQuickSort(totalProcessadores, 10, mutex);

        testQuickSort(totalProcessadores, 15, mutex);
    }

    static void testQuickSort(int totalProcessadores, int tamanho_array, Semaphore mutex){
        System.out.println("\n");
        int[] vetor = generate_array_by_index(tamanho_array);

        System.out.println("Array desordenado: " + Arrays.toString(vetor));

        long t0 = System.currentTimeMillis();
        QuicksortParalelo quickSort = new QuicksortParalelo(vetor, 0, vetor.length - 1, totalProcessadores, 0, 10, mutex);

        quickSort.start();

        try {
            quickSort.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long t1 = System.currentTimeMillis();

        System.out.println("Array ordenado: " + Arrays.toString(vetor));
        System.out.println("Tempo: " + (t1 - t0) + "ms");
    }

    static int[] generate_array_by_index(int index){
        int[] vetor = new int[index];
        Random random = new Random();

        for (int i = 0; i < vetor.length; i++){
            vetor[i] = random.nextInt(100);
        }

        return vetor;
    }
}