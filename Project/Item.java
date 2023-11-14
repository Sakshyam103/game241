package Game241.Project;

public class Item {
    private String name;
    private String description;
    public ItemActions[] itemActions = new ItemActions[3];
    private Boolean itemBrokenStatus = false;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
   public Boolean getItemStatus(){
        return itemBrokenStatus;
   }

   public void breakItem(){
        itemBrokenStatus = true;
   }
   public void addAction(ItemActions action) {
        for (int i = 0; i < itemActions.length; i++) {
            if (itemActions[i]== action) {
                break;
            } else if (itemActions[i] == null) {
                itemActions[i] = action;
                break;
            }
        }
    }

    public boolean ActionSupport(ItemActions act) {
        for (ItemActions a : itemActions) {
            if (act == a) {
                return true;
            }
        }
        return false;
            }

            public void displayActions(){
        for(ItemActions a: itemActions){
            System.out.println(a);
        }
            }

    public String toString(){

        return "Name: " + name + "  Description: " + description;
    }
    public String getItemName(){
        return name;
    }

  }