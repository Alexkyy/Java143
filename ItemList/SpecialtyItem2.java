package ItemList;

class SpecialtyItem2 extends SpecialtyItem {

    public SpecialtyItem2(long idNum, String model, double cost) {
        super(idNum, model, cost);
    }

    public double calcAddedExpense() {
        return 5.;
    }

}