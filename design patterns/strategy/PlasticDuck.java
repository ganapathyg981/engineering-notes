package strategy;

public class PlasticDuck extends Duck {
    PlasticDuck(){
        flyBehaviour = new NoFly();
    }
}
