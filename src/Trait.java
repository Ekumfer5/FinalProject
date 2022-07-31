import java.util.jar.Attributes.Name;

public class Trait {
    public String Name;
    public String Description;
    public String Attack;

    public Trait(String name, String description)
    {
        Name = name;
        Description = description;
    }

    public void ToString()
    {
        System.out.println("    Name: " + Name);
        System.out.println("    Description: " + Description);
        if(Attack != null)
            System.out.println("    Attack: " + Attack);
    }
}
