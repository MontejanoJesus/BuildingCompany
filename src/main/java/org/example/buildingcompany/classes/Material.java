package org.example.buildingcompany.classes;

public class Material {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer cost;
    private Supplier supplier;

    public Material() {}

    public Material(String name, String description, Integer quantity, Integer cost, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", supplier=" + supplier.getName() +
                '}';
    }
}
