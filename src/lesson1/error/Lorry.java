package lesson1.error;

class Lorry extends Car, Moveable, Stopable { // Множественное наследование запрещено, интерфейсы надо implements

    public void move() { // Нет преопределения(@Override)
        System.out.println("Car is moving");
    }

    public void stop() { // Не переопределения(@Override)
        System.out.println("Car is stop");
    }
}
