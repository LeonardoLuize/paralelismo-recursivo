import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int totalProcessadores = Runtime.getRuntime().availableProcessors();
        int[] vetor = new int[10];
        Random random = new Random();

        for (int i = 0; i < vetor.length; i++){
            vetor[i] = random.nextInt(50);
        }

        System.out.println(Arrays.toString(vetor));

        QuicksortParalelo quickSort = new QuicksortParalelo(vetor, 0, vetor.length, totalProcessadores, 0, 10);

        quickSort.start();

        try {
            quickSort.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Arrays.toString(vetor));
    }
}