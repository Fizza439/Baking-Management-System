package classes_folder;

public class EraserThread implements Runnable {
    private boolean stop;

    public EraserThread(String prompt) {
        System.out.print(prompt);
    }


    public void run () {
        stop = true;
        while (stop) {
            try {
                Thread.currentThread();
                Thread.sleep(1);
                System.out.print("\010*");



            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * Instruct the thread to stop masking
     */
    public void stopMasking() {
        this.stop = false;
    }
}