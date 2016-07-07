package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
  /**회원 번호*/
  private int mno;
  /**이메일*/
  private String email;
  /**비밀 번호*/
  private String passwd;
  /**성명*/
  private String name;
  /**가입키*/
  private String auth;
  /**전화번호*/
  private String tel;
  /**우편번호*/
  private String zipcode;
  /**시 주소*/
  private String tAddress;
  /**구 주소(검색 시 사용)*/
  private String mAddress;
  /**상세 주소*/
  private String bAddress;
  /**취미*/
  private String hobby;
  /**생년월일*/
  private String birth;
  /**성별*/
  private String gen;
  /**푸쉬 알람 여부*/
  private String push;
  /**인증 확인 여부*/
  private String confirm;
  /**관리자 여부*/
  private String master;
  /**이미지*/
  private String file1;
  /**이미지 크기*/
  private long size1;
  /**가입일*/
  private String mdate;
  /**소음량*/
  private int sound;
  /**마일리지*/
  private int mi;
  /**지역 코드*/
  private String local_code;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file1MF;
  /** size1의 컴마 저장 출력용 변수 */
  private String size1Label; 
  
  /** 등록된 패스워드 */
  private String old_passwd;
  
  /**email 저장 여부*/
  private String email_save;
  
  /**passwd 저장 여부*/
  private String passwd_save;
  
  /**이동할 주소 저장*/
  private String url_address;
  
  
  public String getLocal_code() {
    return local_code;
  }
  public void setLocal_code(String local_code) {
    this.local_code = local_code;
  }
  public String getOld_passwd() {
    return old_passwd;
  }
  public void setOld_passwd(String old_passwd) {
    this.old_passwd = old_passwd;
  }
  public String getEmail_save() {
    return email_save;
  }
  public void setEmail_save(String email_save) {
    this.email_save = email_save;
  }
  public String getPasswd_save() {
    return passwd_save;
  }
  public void setPasswd_save(String passwd_save) {
    this.passwd_save = passwd_save;
  }
  public String getUrl_address() {
    return url_address;
  }
  public void setUrl_address(String url_address) {
    this.url_address = url_address;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public String getSize1Label() {
    return size1Label;
  }
  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAuth() {
    return auth;
  }
  public void setAuth(String auth) {
    this.auth = auth;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getZipcode() {
    return zipcode;
  }
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
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
  public String getbAddress() {
    return bAddress;
  }
  public void setbAddress(String bAddress) {
    this.bAddress = bAddress;
  }
  public String getHobby() {
    return hobby;
  }
  public void setHobby(String hobby) {
    this.hobby = hobby;
  }
  public String getBirth() {
    return birth;
  }
  public void setBirth(String birth) {
    this.birth = birth;
  }
  public String getGen() {
    return gen;
  }
  public void setGen(String gen) {
    this.gen = gen;
  }
  public String getPush() {
    return push;
  }
  public void setPush(String push) {
    this.push = push;
  }
  public String getConfirm() {
    return confirm;
  }
  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }
  public String getMaster() {
    return master;
  }
  public void setMaster(String master) {
    this.master = master;
  }
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public int getSound() {
    return sound;
  }
  public void setSound(int sound) {
    this.sound = sound;
  }
  public int getMi() {
    return mi;
  }
  public void setMi(int mi) {
    this.mi = mi;
  }
  
  
}
