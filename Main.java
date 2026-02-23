import java.util.Scanner;
import java.io.*;

public class Main {

    static void afficherMenu() {
        System.out.println("1. Ajouter un nouveau produit");
        System.out.println("2. Afficher l'inventaire complet");
        System.out.println("3. Rechercher un produit (par nom)");
        System.out.println("4. Réaliser une vente (avec mise à jour stock)");
        System.out.println("5. Afficher les alertes (stock bas < 5)");
        System.out.println("6. Sauvegarder les données");
        System.out.println("7. Quitter");
    }


    static int saisirEntier(String message) {
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        boolean saisieValide = false;

        while (!saisieValide) {
            System.out.print(message);

            if (sc.hasNextInt() ) {
                choix = sc.nextInt();

                if (choix >= 1 && choix <= 7) {
                    saisieValide = true;
                } else {
                    System.out.println("Erreur : veuillez entrer un nombre entre 1 et 7.");
                }

            } else {
                System.out.println("Erreur : veuillez entrer un nombre valide.");
                sc.next();
            }
        }

        return choix;
    }

    static void ajouterProduit(int[] id, String[] noms, float[] prix, int[] quantite) {
        int counter = 0;
        for (int i : id) {
            if (i != 0) {
                counter++;
            }
        }

        if (counter >= 100) {
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer le nom de produit : ");
        noms[counter] = sc.nextLine();

        System.out.println("Entrer le prix de produit : ");
        prix[counter] = sc.nextFloat();

        System.out.println("Entrer la quantite de produit : ");
        quantite[counter] = sc.nextInt();

        id[counter] = counter;

    }

    static int rechercherProduit(String nom, String[] noms) {
        for (int i = 0; i < noms.length; i++) {
            if (nom.equals(noms[i])) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws FileNotFoundException {

        String message = "Votre choix est : ";

        String noms[] = new String[100];
        int id[] = new int[100];
        float prix[] = new float[100];
        int quantite[] = new int[100];


        File f = new File("src/stock.txt");

        try (Scanner sc = new Scanner(f)) {
            int i = 0;

            while (sc.hasNextLine() && i < 100) {
                String ligne = sc.nextLine();

                String[] elements = ligne.split(";");

                if (elements.length == 3) {
                    id[i] = i+1;
                    noms[i] = elements[0];
                    prix[i] = Float.parseFloat(elements[1]);
                    quantite[i] = Integer.parseInt(elements[2]);
                    i++;
                }
            }

        }
    }
}
