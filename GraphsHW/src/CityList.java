import java.util.LinkedList;
import java.util.List;

public class CityList extends LinkedList<City>{
    private City city;
    private City prev;

    public CityList(City city){
        add(city);
    }

    public void setPrev(City prev){
        for (String n : prev.getNeighbors().keySet()){
            if (n.equals(city.getName()))
                addFirst(city);
        }
    }

    public void printPath(){
        List<String> pathList = new LinkedList();
        for (City c : this){
            pathList.add(c.getName());
        }
        System.out.println(String.join(",", pathList));
    }
}