package sc2002_scmb.hms.entity;

public class PrescriptionEntity {
    
    private String name;
    private int quantity;

    public PrescriptionEntity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }



}
