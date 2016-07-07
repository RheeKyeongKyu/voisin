package dev.mvc.local;

public class LocalVO {

  private String tAddress;
  private String mAddress;
  private String local_code;
  private int grpcnt;
  public String gettAddress() {
    return tAddress;
  }
  public void settAddress(String tAddress) {
    this.tAddress = tAddress;
  }
  public String getmAddress() {
    return mAddress;
  }
  public void setmAddress(String mAddress) {
    this.mAddress = mAddress;
  }
  public String getLocal_code() {
    return local_code;
  }
  public void setLocal_code(String local_code) {
    this.local_code = local_code;
  }
  public int getGrpcnt() {
    return grpcnt;
  }
  public void setGrpcnt(int grpcnt) {
    this.grpcnt = grpcnt;
  }
  
  
}
