package zhw.designpattern.visitor.papercuprum;

import java.util.ArrayList;
import java.util.Iterator;

public class SetMaterial {
    private ArrayList<Material> list = new ArrayList<>();

    public String accept(Company visitor) {
        String result = "";
        Iterator<Material> iterator = list.iterator();
        while (iterator.hasNext()) {
            Material material = iterator.next();

            result += material.accept(visitor) + " ";
        }
        return result;
    }

    public void add(Material element) {
        list.add(element);
    }

    public void remove(Material element) {
        list.remove(element);
    }


}
