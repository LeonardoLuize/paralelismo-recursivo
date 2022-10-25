public class QuicksortSequencial {
    public QuicksortSequencial() {
    }

    static void quickSort(int[] v, int inicio, int fim) {
        if(fim > inicio) {
            int indexPivo = dividi_em_tres(v, inicio, fim);

            int menorPivo = indexPivo - 1;
            int maiorPivo = indexPivo + 1;

            quickSort(v, inicio, menorPivo);
            quickSort(v, maiorPivo, fim);
        }
    }

    static int dividi_em_tres(int[] vetor, int inicio, int fim) {
        int pivo, pontEsq, pontDir = fim;
        pontEsq = inicio + 1;
        pivo = vetor[inicio];

        while(pontEsq <= pontDir) {
            while(pontEsq <= pontDir && vetor[pontEsq] <= pivo) {
                pontEsq++;
            }

            while(pontDir >= pontEsq && vetor[pontDir] > pivo) {
                pontDir--;
            }

            if(pontEsq < pontDir) {
                trocar(vetor, pontDir, pontEsq);
                pontEsq++;
                pontDir--;
            }
        }

        trocar(vetor, inicio, pontDir);
        return pontDir;
    }
    static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

}
