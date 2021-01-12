import java.io.*;
import java.util.*;

class Gifters {
  class Pair {
    String giver;
    String receiver;
    
    Pair(String giver, String receiver) {
      this.giver = giver;
      this.receiver = receiver;
    }
  }
  
  private ArrayList<ArrayList<String>> families = new ArrayList<ArrayList<String>>();
  private ArrayList<Pair> result = new ArrayList<Pair>();
  
  private ArrayList<String> givers = new ArrayList<String>();
  private ArrayList<String> receivers = new ArrayList<String>();
  
  public void clear() {
    families.clear();
    givers.clear();
    receivers.clear();
    result.clear();
  }
  
  // create a family. Members of Family can not gift each other
  public void createFamily(String ...names) {
    ArrayList<String> family = new ArrayList<>();
    for (String name: names) {
      family.add(name);
      givers.add(name);
      receivers.add(name);
    }
    families.add(family);
  }
  
  // create a list of individuals, no restrictions on who can gift who
  public void createIndividuals(String ...names) {
    for (String name: names) {
      createFamily(name);
    }
  }
  
  // check to see if the two names are from the same family
  private boolean isSameFamily(String name1, String name2) {
    for(ArrayList<String> family: families) {
      if(family.contains(name1) && family.contains(name2)) {
        return true;
      }
    }
    return false;
  }
  
  // find a pair of gift giver and receiver and update the result
  private void updatePair() {
    String giver = givers.get(0);
    System.out.println("giver: " + giver);
    givers.remove(0);
    
    String receiver = "";
    int max = receivers.size();
    for (int i = 0; i < max; i++) {
      receiver = receivers.get(i);
      if (!isSameFamily(giver, receiver)) {
        System.out.println("found receiver: " + receiver);
        receivers.remove(i);
        break;
      } else {
        receiver = "No Match";
      }       
    
    result.add(new Pair(giver, receiver));    
  }
  
  public void generateGifters() {
    System.out.println("\n\nFamilies:");
    for(ArrayList<String> family: families) {
      System.out.println(family);
    }
    
    // try making the orders of two lists random
    Collections.reverse(givers);
    Collections.shuffle(givers);
    Collections.shuffle(receivers);
    
    while (givers.size() > 0) {
      updatePair();
    }
    
    for (Pair pair: result) {
      System.out.println(pair.giver + " -> " + pair.receiver);
    }
  }
  
  public static void main(String[] args) {
    
    Gifters gifters = new Gifters();
    
    // test 1 list of individuals no families
    gifters.createIndividuals("John", "James", "David", "Katherine", "Tom", "Scott", "Jude"); 
    gifters.generateGifters();
    
    // test 2
    gifters.clear();
    gifters.createFamily("John", "James", "David", "Katherine");
    gifters.createFamily("Tom", "Dick", "Harry");
    gifters.createFamily("Scott", "Molly");
    gifters.createFamily("Jude");  
    gifters.generateGifters();
    
    // test 3
    gifters.clear();
    gifters.createFamily("name11", "name12", "name13", "name14", "name15", "name16", "name17", "name18");
    gifters.createFamily("name21", "name22", "name23");
    gifters.createFamily("name31", "name32");
    gifters.createFamily("name41", "name42");
    gifters.createFamily("name51", "name52", "name53", "name54");
    gifters.generateGifters();

    // test 4: single family
    gifters.clear();
    gifters.createFamily("name1", "name2", "name3", "name4", "name5", "name6", "name7", "name8");
    gifters.generateGifters();
  }
}
