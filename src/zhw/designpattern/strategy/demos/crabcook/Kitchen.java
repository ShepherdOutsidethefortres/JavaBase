package zhw.designpattern.strategy.demos.crabcook;

/***
 * 厨房
 */
public class Kitchen {
    private CrabCooking crabCooking;

    public CrabCooking getCrabCooking() {
        return crabCooking;
    }

    public void setCrabCooking(CrabCooking crabCooking) {
        this.crabCooking = crabCooking;
    }

    public void cookingMethod(){
        crabCooking.cookingMethod();
    }
}
