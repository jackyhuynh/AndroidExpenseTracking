package androidbootcamp.net.myapplication.Model;


public class Expense {
    public int Id;
    public String Item, Amount;

    public Expense(int Id, String Item, String Amount, String Date){
        this.Id=Id;
        this.Item=Item;
        this.Amount=Amount;
    }

    public Expense()
    {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

}
