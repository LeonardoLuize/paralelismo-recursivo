import java.util.Arrays;

public class QuicksortParalelo extends Thread {
    private int[] v;
    private int inicio, fim, max_threads, nivel, min_numeros;
    public QuicksortParalelo(int[] v,
                             int inicio,
                             int fim,
                             int max_threads,
                             int nivel,
                             int min_numeros) {
        this.inicio = inicio;
        this.fim = fim;
        this.max_threads = max_threads;
        this.nivel = nivel;
        this.min_numeros = min_numeros;
    }

    public void run() {
        this.quickSort(this.v, this.inicio, this.fim);
    }

    private void quickSort(int[] v, int i, int fim) {
        if(this.fim > inicio && this.nivel <= this.max_threads) {
            int indexPivo = dividi_em_tres(this.getV(), this.inicio, this.fim);

            int menorPivo = indexPivo - 1;
            int maiorPivo = indexPivo + 1;

            QuicksortParalelo quickSortMenor = new QuicksortParalelo(this.v, this.inicio, menorPivo, this.max_threads, this.nivel + 1, this.min_numeros);
            QuicksortParalelo quickSortMaior = new QuicksortParalelo(this.v, maiorPivo, this.fim, this.max_threads, this.nivel + 1, this.min_numeros);

            quickSortMenor.start();
            quickSortMaior.start();

            try {
                quickSortMenor.join();
                quickSortMaior.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            QuicksortSequencial.quickSort(v, this.inicio, this.fim);
        }
    }

    private int dividi_em_tres(int[] vetor, int inicio, int fim) {
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
    private void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }


    public int[] getV() {
        return v;
    }

    public void setV(int[] v) {
        this.v = v;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public int getMax_threads() {
        return max_threads;
    }

    public void setMax_threads(int max_threads) {
        this.max_threads = max_threads;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getMin_numeros() {
        return min_numeros;
    }

    public void setMin_numeros(int min_numeros) {
        this.min_numeros = min_numeros;
    }

}
