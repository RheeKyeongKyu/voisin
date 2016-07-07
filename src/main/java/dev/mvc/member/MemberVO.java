package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
  /**ȸ�� ��ȣ*/
  private int mno;
  /**�̸���*/
  private String email;
  /**��� ��ȣ*/
  private String passwd;
  /**����*/
  private String name;
  /**����Ű*/
  private String auth;
  /**��ȭ��ȣ*/
  private String tel;
  /**�����ȣ*/
  private String zipcode;
  /**�� �ּ�*/
  private String tAddress;
  /**�� �ּ�(�˻� �� ���)*/
  private String mAddress;
  /**�� �ּ�*/
  private String bAddress;
  /**���*/
  private String hobby;
  /**�������*/
  private String birth;
  /**����*/
  private String gen;
  /**Ǫ�� �˶� ����*/
  private String push;
  /**���� Ȯ�� ����*/
  private String confirm;
  /**������ ����*/
  private String master;
  /**�̹���*/
  private String file1;
  /**�̹��� ũ��*/
  private long size1;
  /**������*/
  private String mdate;
  /**������*/
  private int sound;
  /**���ϸ���*/
  private int mi;
  /**���� �ڵ�*/
  private String local_code;
  
  /** Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü */  
  private MultipartFile file1MF;
  /** size1�� �ĸ� ���� ��¿� ���� */
  private String size1Label; 
  
  /** ��ϵ� �н����� */
  private String old_passwd;
  
  /**email ���� ����*/
  private String email_save;
  
  /**passwd ���� ����*/
  private String passwd_save;
  
  /**�̵��� �ּ� ����*/
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
