package wrapper.Matrici;

public class ProvaMatrice {
    public static void main(String[] args) {
        double[][] a1 = {{1,3,0}, {2,1,1}, {0,2,1}};
        double[][] a2 = {{1.0/7,3.0/7,-3.0/7}, {+2.0/7, -1/7.0, 1.0/7}, {-4/7.0,+2.0/7,5.0/7}}; 
        double[][] a3 = {{3, 2, 0}, {2,-1,1}, {0,-2,1}};
        double[][] a4 = {{1, 2, 0}, {0,-1, 4}, {1,2,0}};

        Matrice m1 = new Matrice(a1);
        Matrice m2 = new Matrice(a2);
        Matrice m3 = new Matrice(a3);
        Matrice m4 = new Matrice(a4);
        
        System.out.println("Matrice 1:\n" + m1);
        System.out.println("Matrice 2:\n" + m2);
        if(m1.matriceInversaDi(m2)) {
            System.out.println("La matrice 1 è l'inversa della matrice 2");
        } else {
            System.out.println("La matrice 1 NON è l'inversa della matrice 2");
        }
        
        System.out.println("Determinante matrice 1: " + m1.getDeterminante());
        System.out.println("Determinante matrice 2: " + m2.getDeterminante());
        System.out.println("Determinante matrice 3: " + m3.getDeterminante());
        System.out.println("Determinante matrice 4: " + m4.getDeterminante());
        if(m4.isInvertibile()) {
            System.out.println("La matrice 4 è invertibile");
        } else {
            System.out.println("La matrice 4 NON è invertibile");
        }
        

        System.out.println("Prodotto Matrice 1 e Matrice 2:\n" + m1.prodotto(m2));
    }
}
