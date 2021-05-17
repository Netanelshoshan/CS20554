/*
* @ Netanel Shoshan @
* line in bill as requierd in the assignment.
*/
public class LineInBill {
    protected Product p;
    protected float amount;
    protected float total_price;

    public LineInBill(Product p, float amount) {
        this.p = p;
        this.amount = amount;
        this.total_price = p.getPrice() * amount;
    }



    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public float getTotal_price() {
        return total_price;
    }

    @Override
    public String toString() {
        return
                p.toString() +
                        ", amount=" + amount +
                        ", total=" + total_price;
    }
}




