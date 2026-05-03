public class Acrobatic extends Person {

    public Acrobatic(String name, int age, int yearsWorked, String job, String imagePath) {
        super(name, age, yearsWorked, job, imagePath);
    }

    @Override
    public String toString() {
        return "Acrobatic - " + super.toString();
    }
}
