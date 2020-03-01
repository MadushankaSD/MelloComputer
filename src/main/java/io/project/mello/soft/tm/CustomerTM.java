package io.project.mello.soft.tm;

public class CustomerTM {
    private String customerId;
    private String name;
    private String address;
    private long telephonNo;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String name, String address, long telephonNo) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.telephonNo = telephonNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTelephonNo() {
        return telephonNo;
    }

    public void setTelephonNo(long telephonNo) {
        this.telephonNo = telephonNo;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephonNo=" + telephonNo +
                '}';
    }
}
