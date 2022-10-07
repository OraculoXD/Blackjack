import java.util.Random;
import java.util.Scanner;

public class Blackack {
    public static void mostrarCartas(final String participante, 
    final int[] cs, final int tcs) {
        System.out.printf("%s%n", participante);
        for (int i = 1; i < cs.length; i++){
            System.out.printf("Carta %d: %s%n", i, face(cs[i]));
        }
        System.out.printf("Total  : %d%n", tcs);
    }
    
    public static int gerarCarta(final Random r) {
        return 1 + r.nextInt(13);
    }
    
    public static final String[] naipes = {"X", "♠", "♥", "♦", "♣"};
    
    public static String gerarNaipe(final Random r){
        int n = r.nextInt(4);
        return naipes[n];
    }
    
    public static final String[] faces = {"X", "A", 
                                          "2", "3", "4",
                                          "5", "6", "7",
                                          "8", "9", "10",
                                          "J", "Q", "K"};
    
    public static String face(final int c) {
        return faces[c];
    }
    
    public static int valor(final int c) {
        return (c > 10)? 10 : c;
    }
    
    public static int somarCartas(final int[] cartas) {
        int s = 0;
        for (int i = 1; i < cartas.length; i++){
            s += valor(cartas[i]);
        }
        return s;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int[] c = new int[4];
        int[] j = new int[3];
        int tc, tj;
        String resposta;
        boolean hitme;
        
        for (int i = 1; i < c.length; i++) {
            c[i] = gerarCarta(r);            
        }
        
        for (int i = 1; i < j.length; i++) {
            j[i] = gerarCarta(r);
        }
        
        System.out.printf("COMPUTADOR%n");
        System.out.printf("Carta 1: %s%n", face(c[1]));
        
        System.out.printf("JOGADOR%n");
        System.out.printf("Carta 1: %s%n", face(j[1]));
        System.out.printf("Carta 2: %s%n", face(j[2]));
        
        System.out.printf("%nDeseja uma terceira carta? (s/n)%n");
        resposta = sc.nextLine();
        
        if ("s".equalsIgnoreCase(resposta)){
            hitme = true;
        } else
            hitme = false;
            
        if (hitme) {
            int[] antigo = j;
            j = new int[antigo.length + 1];
            j[1] = antigo[1];
            j[2] = antigo[2];
            j[3] = gerarCarta(r);
        }
        
        tc = somarCartas(c); 
        tj = somarCartas(j);
        
        mostrarCartas("COMPUTADOR", c, tc);
        mostrarCartas("JOGADOR", j, tj);
        
        if (tj > 21) {
            System.out.println("Perdeu! (Excedeu 21)");
        } else {
            if (tc > 21) {
                System.out.println("Ganhou! (Oponente excedeu 21)");
            } else {
                if (tj > tc) {
                    System.out.println("Ganhou! (Mais perto de 21)");
                } else {
                    if (tj < tc) {
                        System.out.println("Perdeu! (Oponente mais perto de 21)");
                    } else {
                        System.out.println("Empate! (Mesmo valor)");
                    }
                }
            } 
        }
        sc.close();
    }
}