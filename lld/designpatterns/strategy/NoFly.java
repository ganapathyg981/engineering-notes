package strategy;

public class NoFly implements FlyBehaviour {

    @Override
    public void fly() {
        System.out.println("No shit");
    }
}
