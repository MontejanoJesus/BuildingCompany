package org.example.buildingcompany.classes;

public class Equipment {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer cost;
    private Supplier supplier;

    public Equipment(){}
    public Equipment(Integer id, String name, String description, Integer quantity, Integer cost, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", supplier=" + supplier.getName() +
                '}';
    }
}
