package game;

/**
 *
 * @author paolo
 */
public class Ground {

    private int[][] tavolo;

    public Ground() {
        this.initTavolo();
    }

    public void initTavolo() {
        this.tavolo = new int[4][4];
        int cont = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tavolo[i][j] = cont;
                cont++;
            }
        }
    }

    public int[][] toMatrix(){
        return this.tavolo;
    }

    public void shuffle() {
        boolean[][] control = new boolean[4][4];
        int[][] mischia = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                control[i][j] = false;
                mischia[i][j] = 0;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double random = Math.random() * 4;
                double random1 = Math.random() * 4;
                int rand = (int) random;
                int rand1 = (int) random1;
                while (control[rand][rand1]) {
                    random = Math.random() * 4;
                    random1 = Math.random() * 4;
                    rand = (int) random;
                    rand1 = (int) random1;
                }
                mischia[i][j] = this.tavolo[rand][rand1];
                control[rand][rand1] = true;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tavolo[i][j] = mischia[i][j];
            }
        }
    }

    public boolean isWinner(){
        boolean control=true;
        int cont=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                control=control&&this.tavolo[i][j]==(cont+1);
                cont++;
            }
        }
        return control;
    }

    public void sposta(int pI, int pJ) {
        if (this.check(pI, pJ)) {
            int spostaI = 0;
            int spostaJ = 0;
            for (int id = 0; id < 4; id++) {
                for (int jd = 0; jd < 4; jd++) {
                    if (this.tavolo[id][jd] == 16) {
                        spostaI = id;
                        spostaJ = jd;
                    }
                }
            }
            int swap = this.tavolo[pI][pJ];
            this.tavolo[pI][pJ] = this.tavolo[spostaI][spostaJ];
            this.tavolo[spostaI][spostaJ] = swap;
        }
    }

    private boolean check(int pI, int pJ) {
        int[] intorno = {0,0,0,0};
        if (pI - 1 >= 0) {
            intorno[0] = this.tavolo[pI - 1][pJ];
        }
        if (pJ - 1 >= 3) {
            intorno[1] = this.tavolo[pI][pJ - 1];
        }
        if (pI + 1 <= 3) {
            intorno[2] = this.tavolo[pI + 1][pJ];
        }
        if (pJ + 1 <= 3) {
            intorno[3] = this.tavolo[pI][pJ + 1];
        }
        for(int i=0; i<4; i++){
            if(intorno[i]==16){
                return true;
            }
        }
        return false;
    }
}
