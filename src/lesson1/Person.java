package lesson1;

public class Person {

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static class Builder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public Builder firstName(String val){
            firstName=val;
            return this;
        }
        public Builder lastName(String val){
            lastName=val;
            return this;
        }
        public Builder middleName(String val){
            middleName=val;
            return this;
        }
        public Builder country(String val){
            country = val;
            return this;
        }
        public Builder address(String val){
            address=val;
            return this;
        }
        public Builder phone(String val){
            phone=val;
            return this;
        }
        public Builder age(int val){
            age=val;
            return this;
        }
        public Builder gender(String val){
            gender = val;
            return this;
        }

        public Person buidl(){
            return new  Person(this);
        }
    }

    private Person(Builder builder){
        firstName=builder.firstName;
        lastName=builder.lastName;
        middleName=builder.middleName;
        country=builder.country;
        address=builder.address;
        phone=builder.phone;
        age= builder.age;
        gender=builder.gender;
    }
}

class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder().address("qw").buidl();
    }
}

