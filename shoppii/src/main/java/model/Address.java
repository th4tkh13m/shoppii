package model;

public class Address {
    private int addressId;
    private int userId;
    private String receiverAddress, receiverName,
            receiverPhone, province, ward, district;
    private boolean isDefault;

    

    public Address(int addressId, int userId, String receiverAddress, String receiverName,
            String receiverPhone, String province, String ward, String district,
            boolean isDefault) {
        this.addressId = addressId;
        this.userId = userId;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.province = province;
        this.ward = ward;
        this.district = district;
        this.isDefault = isDefault;
    }
    

    public Address(int addressId, String receiverAddress, String receiverName, String receiverPhone,
            String province, String ward, String district, boolean isDefault) {
        this.addressId = addressId;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.province = province;
        this.ward = ward;
        this.district = district;
        this.isDefault = isDefault;
    }


    public Address(int addressId, int userId, String receiverAddress, String receiverName, String receiverPhone) {
        this.addressId = addressId;
        this.userId = userId;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;

    }

    public int getUserId() {
        return userId;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public int getAddressId() {
        return addressId;
    }

}
