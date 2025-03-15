package strategy;

public interface FlyBehaviour {
    void fly();

    default void fl() {
        System.out.println();
    }

}
