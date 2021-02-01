package lesson1.error;

class LightWeightCar extends Car implements Moveable {

    @Override
    void open() {
        System.out.println("Car is open");
    } // Не отнаследовались от интерфейса Stopabel

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

}
