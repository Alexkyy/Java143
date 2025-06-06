package ItemList;

public abstract class SpecialtyItem extends Item {

    public SpecialtyItem(long idNum, String model, double cost) {
        super(idNum, model, cost);
    }

    public abstract double calcAddedExpense();

    @Override
    public double calcPrice() {
        return getCost() + calcAddedExpense();
    }
}
