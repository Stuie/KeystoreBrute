package se.bes.br;

import java.security.KeyStore;

/**
 * A Java {@link KeyStore} brute-force password finder.
 *
 * With my Intel Core2Quad machine, I can test about 140k passwords/second,
 * using a setting of 10 threads (Linux).
 *
 * Please experiment with the settings best suited for your machine.
 *
 * Why did I write this program? Because I lost my keystore password and
 * I was naïve enough to think I could actually restore it =)
 *
 * With my machine, I can cover all 6 letter passwords (a-z,A-Z,0-9) in
 * 4.7 days. I can cover all 7 letter passwords in 291 days and all 8
 * letter passwords in about 49.5 years.
 *
 * You can get much better performance if you reduce the character set
 * e.g. if you know roughly which letters were used, but will of course
 * perform worse if you decide to add characters (many people use special
 * characters like !, @ or # in their passwords).
 *
 * Good luck.
 *
 * @author Erik Z: dev@bes.se
 */
public class BruteMain {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3 || args.length >= 5) {
            System.out.println("Usage: java -jar Breaker.jar <keystore file> <startdepth> <number of threads> [prefix string]");
            System.out.println("Or: java se.bes.br.BruteMain <keystore file> <startdepth> <number of threads> [prefix string]");
            return;
        }
        System.out.println("Breaking: " + args[0]);

        String file = args[0];
        int startDepth = 6;
        int threads = 10;
        try {
            startDepth = Integer.parseInt(args[1]);
            threads = Integer.parseInt(args[2]);
        } catch (Throwable t) {}

        String prefix = null;
        if(args.length == 4) {
            prefix = args[3];
        }

        Breaker breaker = new Breaker(file, startDepth, threads, prefix);

        String passphrase = breaker.getPassphrase();

        System.out.println();
        System.out.println();
        System.out.println("Passphrase found: " + passphrase);
        System.out.println();
    }
}
