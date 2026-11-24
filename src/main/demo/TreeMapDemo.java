package demo;

import map.Map;
import map.TreeMap;

/** A demo of TreeMap: a website storing each username with its password. */
public final class TreeMapDemo {

  private TreeMapDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Map<String, String> passwords = new TreeMap<>();

    // The usernames arrive in sorted order, which would build a chain in a
    // plain binary search tree. Each put of a new key returns true.
    System.out.println("register alice: " + passwords.put("alice", "wonderland"));
    System.out.println("register bob: " + passwords.put("bob", "builder123"));
    System.out.println("register carol: " + passwords.put("carol", "swift99"));
    System.out.println("register dave: " + passwords.put("dave", "diver42"));
    System.out.println("register erin: " + passwords.put("erin", "orbit7"));

    // Expect 5.
    System.out.println("users registered: " + passwords.size());

    // Expect wonderland.
    System.out.println("alice's password: " + passwords.get("alice"));

    // A put on an existing key replaces the value and returns false.
    System.out.println("bob changes the password: " + passwords.put("bob", "fixerUpper"));

    // Expect fixerUpper, and still 5 users.
    System.out.println("bob's password: " + passwords.get("bob"));
    System.out.println("users registered: " + passwords.size());

    // Expect true.
    System.out.println("is carol registered? " + passwords.containsKey("carol"));

    // Expect true: carol was in the map.
    System.out.println("carol deletes the account: " + passwords.remove("carol"));

    // Expect alice bob dave erin.
    System.out.print("all usernames, in sorted order:");
    for (String username : passwords) {
      System.out.print(" " + username);
    }
    System.out.println();

    // Expect the same order, each username with its password.
    System.out.println("all usernames with passwords:");
    for (Map.Entry<String, String> entry : passwords.entries()) {
      System.out.println("  " + entry.key() + " -> " + entry.value());
    }
  }
}
