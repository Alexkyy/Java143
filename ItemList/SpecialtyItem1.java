package ItemList;

class SpecialtyItem1 extends SpecialtyItem {

    public SpecialtyItem1(long idNum, String model, double cost) {
        super(idNum, model, cost);
    }

    public double calcAddedExpense() {
        return 6.;
    }
}