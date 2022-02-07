package simulation;

public interface Simulation {
    /***
     * Metoda care se ocupa cu simularea unei singure runde, realizeaza
     * toate operatiile specifice unei runde.
     */
    void justExec();

    /***
     * Metoda care aplica update-urile specifice unui annualChange si care
     * apeleaza metoda justExec pentru a simula runda dupa modificarile
     * realizate.
     */
    void updateAndExec();
}
