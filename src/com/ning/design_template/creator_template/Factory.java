package com.ning.design_template.creator_template;

import lombok.SneakyThrows;

public class Factory {
    public static void main(String[] args) {
        StaticFactory.test();
        TruckFactory.test();
        TankFactory.test();
        ItalianFoodFactory.test();
    }
}

class StaticFactory {
    public static void test() {
        Car tank = StaticFactory.createCar("tank");
        tank.drive();
        Car truck = StaticFactory.createCar("truck");
        truck.drive();
    }

    @SneakyThrows
    public static Car createCar(String type) {
        switch (type) {
            case "truck":
                return new Truck();
            case "tank":
                return new Tank();
            default:
                throw new ClassNotFoundException(String.format("不存在的车类型:[%s]", type));
        }
    }
}

interface CarFactory {
    Car createCar();
}

class TruckFactory implements CarFactory {

    public static void test() {
        CarFactory factory = new TruckFactory();
        Car car = factory.createCar();
        car.drive();
    }

    @Override
    public Car createCar() {
        return new Truck();
    }
}

class TankFactory implements CarFactory {

    public static void test() {
        CarFactory factory = new TankFactory();
        Car car = factory.createCar();
        car.drive();
    }

    @Override
    public Car createCar() {
        return new Tank();
    }
}

interface Car {
    void drive();
}

class Tank implements Car {
    @Override
    public void drive() {
        System.out.println("坦克车嘟嘟嘟");
    }
}

class Truck implements Car {
    @Override
    public void drive() {
        System.out.println("卡车嘟嘟嘟");
    }
}

interface Food {
    void eat();
}

interface Pizza extends Food {
    void cut();
}

interface Hamburger extends Food {
    void pack();
}

class CheesePizza implements Pizza {
    @Override
    public void eat() {
        System.out.println("吃披萨");
    }

    @Override
    public void cut() {
        System.out.println("切披萨");
    }
}

class MeetHamburger implements Hamburger {

    @Override
    public void eat() {
        System.out.println("吃汉堡");
    }

    @Override
    public void pack() {
        System.out.println("包装汉堡");
    }
}

interface FoodFactory {

    Pizza createPizza();

    Hamburger createHamburger();
}

class ItalianFoodFactory implements FoodFactory {

    public static void test() {
        ItalianFoodFactory factory = new ItalianFoodFactory();
        Pizza pizza = factory.createPizza();
        pizza.cut();
        pizza.eat();
        Hamburger hamburger = factory.createHamburger();
        hamburger.pack();
        hamburger.eat();
    }

    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }

    @Override
    public Hamburger createHamburger() {
        return new MeetHamburger();
    }
}
