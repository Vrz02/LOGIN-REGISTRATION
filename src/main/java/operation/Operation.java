package operation;

import model.Pojo;



public interface Operation {
    String register_user(Pojo pojo);  // Ensure this matches Implementator.java
    boolean login_user(Pojo pojo);
}
