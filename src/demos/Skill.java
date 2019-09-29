package demos;

public class Skill implements Cloneable{
    private String skill1;
    private String skill2;

    public Skill(String skill1,String skill2){
        this.skill1=skill1;
        this.skill2=skill2;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "demos.Skill{" +
                "skill1='" + skill1 + '\'' +
                ", skill2='" + skill2 + '\'' +
                '}';
    }
}
