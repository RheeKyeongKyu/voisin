package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.member.MemberDAO")
public class MemberDAO {
  @Autowired
  private SqlSession sqlSession; // MyBATIS 3 ���� ��ü
  
  public MemberDAO(){
    System.out.println("--> MemberDAO created.");
  }
  
  public MemberMapperInter mapper(){
    MemberMapperInter mapper = sqlSession.getMapper(MemberMapperInter.class);    
    
    return mapper;
  }

  /**
   * �ߺ� �̸��� Ȯ��
   * @param email
   * @return �ߺ� ����
   */
  public int checkEmail(String email) {
    return mapper().checkEmail(email);
  }
  
  /**
   * ������ Ȯ��
   * @param master
   * @return ������ ��
   */
  public int checkMaster(String master) {
    return mapper().checkMaster(master);
  }
  
  /**
   * ���� ����
   */
  public int create(MemberVO memberVO) {
    return mapper().create(memberVO);
  }
  
  /**
   * �����ڿ� ���
   */
/*  public ArrayList<MemberVO> list() {
    return mapper().list();
  }*/
  
  /**
   * �����ڿ� ���
   */
  public ArrayList<MemberVO> list(HashMap hashMap) {
    System.out.println("MemberDAO");
    System.out.println("col: " + hashMap.get("col"));
    System.out.println("word: " + hashMap.get("word"));
    System.out.println("startNum: " + hashMap.get("startNum"));
    System.out.println("endNum: " + hashMap.get("endNum"));
    
    return mapper().list(hashMap);
  }
  
  /**
   * �˻� ���� 
   */
  public int count(HashMap hashMap) {
    return mapper().count(hashMap);
  }
  
  /**
   * �α���
   */
  public int login(MemberVO memberVO) {
    return mapper().login(memberVO);
  }
  
  /**
   * mno, master, confirm ����
   */
  public MemberVO readByEmail(String email) {
    return mapper().readByEmail(email);
  }
  
  /**
   * �����ڵ� Ȯ��
   */
  public int confirm(MemberVO memberVO) {
    return mapper().confirm(memberVO);
  }
  
  /**
   * ���ϸ��� ����
   */
  public int mileage(String email) {
    return mapper().mileage(email);
  }
  
  /**
   * ���� ��ȸ
   */
  public MemberVO read(int mno) {
    return mapper().read(mno);
  }
  
  /**
   * ���� ����
   */
  public int update(MemberVO memberVO) {
    return mapper().update(memberVO);
  }
  
  /**
   * ���� ����
   */
  public int delete(int mno) {
    return mapper().delete(mno);
  }
  
  /**
   * ���� ����(������ ���� �κ�)
   */
  public int delete_master(String email){
    return mapper().delete_master(email);
  }
  
  /**
   * �н����� ����
   */
  public int passwd_update(MemberVO memberVO) {
    return mapper().passwd_update(memberVO);
  }
  
  /**
   * �н����� ��ġ �˻�
   */
  public int passwdCheck(MemberVO memberVO){
    int mno = memberVO.getMno();
    String passwd = memberVO.getOld_passwd(); // ���� �н�����
    
    HashMap map = new HashMap();
    map.put("mno", mno);
    map.put("passwd", passwd);
    
    return mapper().passwdCheck(map);
  }



  
 
  
  
  
  
  
  
}
