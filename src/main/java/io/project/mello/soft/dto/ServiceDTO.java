package io.project.mello.soft.dto;

public class ServiceDTO {
    private String discription;
    private Double serviceCharge;
    private String orderId;
    private long warranty;

    public ServiceDTO() {
    }

    public ServiceDTO(String discription, Double serviceCharge, String orderId, long warranty) {
        this.discription = discription;
        this.serviceCharge = serviceCharge;
        this.orderId = orderId;
        this.warranty = warranty;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getWarranty() {
        return warranty;
    }

    public void setWarranty(long warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                "discription='" + discription + '\'' +
                ", serviceCharge=" + serviceCharge +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
