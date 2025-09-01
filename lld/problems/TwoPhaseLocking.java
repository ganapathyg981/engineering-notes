import java.util.concurrent.locks.ReentrantLock;

public class TwoPhaseLocking {
    private final ReentrantLock lock = new ReentrantLock();
    private int data; // Shared data object

    // Scenario 1: Transaction A Reads, and Transaction B Wants to Write
    public void scenario1ReadAWriteB() {
        // Transaction A (Read)
        lock.lock();
        try {
            // Reading the data
            System.out.println("Transaction A acquires the lock and reads data: " + data);
            // Simulate some processing time
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Release lock
            System.out.println("Transaction A releases the lock");
        }

        // Introduce a delay to simulate B attempting to write while A is reading
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Transaction B (Write)
        lock.lock();
        try {
            // Writing to the data
            data += 10; // Simulating a write operation
            System.out.println("Transaction B acquires the lock and writes data: " + data);
        } finally {
            lock.unlock(); // Release lock
            System.out.println("Transaction B releases the lock");
        }
    }

    // Scenario 2: Transaction A Writes, and Transaction B Wants to Read
    public void scenario2WriteAReadB() {
        // Transaction A (Write)
        lock.lock();
        try {
            // Writing to the data
            data += 20; // Simulating a write operation
            System.out.println("Transaction A acquires the lock and writes data: " + data);
            // Simulate some processing time
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Release lock
            System.out.println("Transaction A releases the lock");
        }

        // Introduce a delay to simulate B attempting to read while A is writing
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Transaction B (Read)
        lock.lock();
        try {
            // Reading the data (ensuring it's the most up-to-date version)
            System.out.println("Transaction B acquires the lock and reads data: " + data);
        } finally {
            lock.unlock(); // Release lock
            System.out.println("Transaction B releases the lock");
        }
    }

    public static void main(String[] args) {
        TwoPhaseLocking database = new TwoPhaseLocking();

        // Scenario 1: Transaction A Reads, and Transaction B Wants to Write
        Thread transactionA = new Thread(database::scenario1ReadAWriteB);
        Thread transactionB = new Thread(database::scenario1ReadAWriteB);

        transactionA.start();
        transactionB.start();

        try {
            transactionA.join();
            transactionB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Scenario 1 completed.\n");

        // Reset data for Scenario 2
        database.data = 0;

        // Scenario 2: Transaction A Writes, and Transaction B Wants to Read
        Thread transactionC = new Thread(database::scenario2WriteAReadB);
        Thread transactionD = new Thread(database::scenario2WriteAReadB);

        transactionC.start();
        transactionD.start();

        try {
            transactionC.join();
            transactionD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Scenario 2 completed.");
    }
}
