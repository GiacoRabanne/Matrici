package wrapper.Matrici;

public class Matrice {
    private double[][] matrice;

    public Matrice(double[][] m) {
        this.matrice = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                this.matrice[i][j] = m[i][j];
            }
        }
    }

    public int getNumRows() {
        return this.matrice.length;
    }
    
    public int getNumCols() {
        return this.matrice[0].length;
    }

    public double casella(int i, int j) {
        return this.matrice[i][j];
    }

    public double getDeterminante() {
        return getDeterminante(this);
    }

    public static Matrice getMatriceUnita(int n) {
        double[][] m = new double[n][n];

        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                if(i == j) {
                    m[i][j] = 1;
                } else {
                    m[i][j] = 0;
                }
            }
        }

        return new Matrice(m);
    }

    private double getDeterminante(Matrice m) {
        double determinante;

        // Se la matrice non è quadrata, il determinante è indeterminato
        if(m.getNumRows() != m.getNumCols()) {
            determinante = Double.NaN;
        } 
        // Se la matrice è 1x1, il determinante è la cella stessa
        else if(m.getNumRows() == 1) {
            determinante = m.casella(0, 0);
        } 
        // Caso base: se la matrice è 2x2, l'algoritmo prevede di moltiplicare i valori della diagonale principale e sottrarre il prodotto dei valori della diagonale speculare
        else if(m.getNumRows() == 2) {
            double diagPrincipale = m.casella(0, 0)*m.casella(1,1);
            double diagSpeculare = m.casella(1, 0)*m.casella(0,1);
            determinante = diagPrincipale - diagSpeculare;
        } 
        // Caso ricorsivo (da n > 2): Algoritmo: LaPlace.
        else {
            determinante = 0;
            int n = m.getNumRows();

            for(int i = 0; i < n; i++) {
                // Creo la sottomatrice:
                double[][] sottoArray = new double[n - 1][n - 1];

                int iS = 0, jS = 0; // Sono gli indici che scandiscono la sottomatrice;
                boolean riempita = false;
                for(int r = 0; r < n && !riempita; r++) {
                    for(int c = 0; c < n && !riempita; c++) {
                        if(r != i && c != 0) {
                            sottoArray[iS][jS] = m.casella(r, c);
                            jS++;
                            if(jS == sottoArray[0].length) {
                                jS = 0;
                                iS++;
                                if(iS == sottoArray.length) {
                                    riempita = true;
                                }
                            }
                        }
                    }
                }

                // Dopo aver creato la sottomatrice con gli elementi corretti, creo l'oggetto matrice passandogli la sottomatrice appena creata.
                Matrice sottoMatrice = new Matrice(sottoArray);

                if(i%2 == 0) {
                    determinante += m.casella(i, 0)*getDeterminante(sottoMatrice);
                } else {
                    determinante -= m.casella(i, 0)*getDeterminante(sottoMatrice);
                }
            }  
        }

        return arrotonda(determinante);
    }

    private static double arrotonda(double d) {
        return Math.round(d*1000)/1000.0;
    } 

    public boolean isInvertibile() {
        return this.getDeterminante() != 0;
    }

    public boolean stesseDimensioni(Matrice m) {
        return this.getNumRows() == m.getNumRows() && this.getNumCols() == m.getNumCols();
    }

    public boolean matriceInversaDi(Matrice m) {
        boolean inversa;
        if(this.prodotto(m).equals(Matrice.getMatriceUnita(m.getNumCols()))) {
            inversa = true;
        } else {
            inversa = false;
        }
        return inversa;
    }

    public Matrice somma(Matrice m) {
        double[][] somma = new double[this.matrice.length][this.matrice[0].length]; //posto che m e this abbiano le stesse dimensioni

        for(int i = 0; i < somma.length; i++) {
            for(int j = 0; j < somma[i].length; j++) {
                somma[i][j] = arrotonda(matrice[i][j] + m.casella(i, j));
            }
        }
        return new Matrice(somma);
    }

    public Matrice prodotto(Matrice m) {
        double[][] prodotto = new double[this.matrice.length][m.matrice[0].length];
        double somma;
        for(int i = 0; i < prodotto.length; i++) {
            for(int j = 0; j < prodotto[i].length; j++) {
                somma = 0;
                for(int k = 0; k < this.matrice[0].length; k++) {
                    somma += this.matrice[i][k] * m.casella(k, j);
                }
                prodotto[i][j] = arrotonda(somma);
            }
        }
        return new Matrice(prodotto);
    }

    public boolean equals(Matrice m) {
        boolean uguali = this.stesseDimensioni(m);

        for(int i = 0; i < m.getNumRows() && uguali; i++) {
            for(int j = 0; j < m.getNumCols() && uguali; j++) {
                if(this.casella(i, j) != m.casella(i, j)) {
                    uguali = false;
                }
            }
        }

        return uguali;
    }

    public String toString() {
        String s = "";

        for(int i = 0; i < this.matrice.length; i++) {
            for(int j = 0; j < this.matrice[0].length; j++) {
                s += arrotonda(this.matrice[i][j]) + " ";
            }
            s += "\n";
        }

        return s;
    }
}
