import TADs.hashcerrado.HashCerrado;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashCerrado hash = new HashCerrado();

        System.out.println(hash.hash(5));
        System.out.println(hash.hash(7));
        System.out.println(hash.hash(10));
    }
}