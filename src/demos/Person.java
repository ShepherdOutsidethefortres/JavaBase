package demos;

public class Person implements Cloneable {
    private String name;
    private String sex;
    private Skill skill;

    public Person(String name, String sex, Skill skill) {
        this.name = name;
        this.sex = sex;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person newPer = (Person) super.clone();
        newPer.skill = (Skill) newPer.getSkill().clone();
        return newPer;
    }

    @Override
    public String toString() {
        return "demos.Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", skill=" + skill +
                '}';
    }
}
