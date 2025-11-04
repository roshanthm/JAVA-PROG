class SampleRunnable implements Runnable {
    int a;

    SampleRunnable(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Runnable " + a + " - for loop " + i);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class sample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new SampleRunnable(1));
        Thread t2 = new Thread(new SampleRunnable(2));
        Thread t3 = new Thread(new SampleRunnable(3));

        t1.start();
        t2.start();
        t3.start();
    }
}
