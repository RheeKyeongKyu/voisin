package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MemberMapperInter {
  
  /**
   * ���� ���
   * @param memberVO ���� ��� ����
   * @return 
   * <insert id="create" parameterType="MemberVO">
   */
  public int create(MemberVO memberVO);
  
  /**
   * �ߺ� ���� Ȯ��
   * @param mail �˻��� ���� 
   * @return
   * <select id='checkEmail' resultType='int' parameterType='String'>
   */
  public int checkEmail(String email);
  
  /**
   * ������ �˻�
   * @param master
   * @return
   * <select id="checkMaster" resultType='int' parameterType='String'>
   */
  public int checkMaster(String master);
  
  /**
   * �����ڿ� ���
   * @return
   * <select id="list" resultType="MemberVO">
   */
  /*public ArrayList<MemberVO> list();*/
  /**
   * �����ڿ� ���(�˻� �κ� �߰�)
   * <select id="list" resultType="MemberVO" parameterType="HashMap" >
   */
  public ArrayList<MemberVO> list(HashMap hashMap);
  
  /**
   * �˻��� ���ڵ� ����
   * @param hashMap
   * @return
   */
  //<select id="count" resultType="int" parameterType="HashMap" >
  public int count(HashMap hashMap);
  
  /**
   * �α���
   * <select id="login" resultType="int" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
 public int login(MemberVO memberVO);
 
 /**
  * �̸��Ϸ� mno, master, confirm����
  * @param email
  * @return
  * <select id="readByEmail" resultType="MemberVO" parameterType="String">
  */
 public MemberVO readByEmail(String email); 
 
 /**
  * �����ڵ� Ȯ��
  * @param memberVO
  * @return
  *  <update id="confirm" parameterType="MemberVO">
  */
 public int confirm(MemberVO memberVO);
 
 /**
  * ���ϸ��� ����
  * @param memberVO
  * @return
  * <update id="mileage" parameterType="String">
  */
 public int mileage(String email);
 
 /**
  * ȸ�� ���� ��ȸ
  * <select id="read" resultType="MemberVO" parameterType="int">
  * @param mno
  * @return
  */
 public MemberVO read(int mno); 
 
 /**
  * ȸ�� ���� ����
  * @param memberVO
  * @return
  */
 public int update(MemberVO memberVO);
 
 /**
  * ȸ�� ����
  * @param mno
  * @return
  * <update id="delete" parameterType="int">
  */
 public int delete(int mno);
 
 /**
  * ȸ�� ���� (������ ���� �κ�)
  * @param email
  * @return
  *  <select id="delete_master" resultType="int" parameterType="String">
  */
 public int delete_master(String email);
 
 /**
  * �н����� ����
  * @param memberVO
  * @return
  * <update id="passwd_update" parameterType="MemberVO">
  */
 public int passwd_update(MemberVO memberVO);
 
 /**
  * �н����� ��ġ �˻�
  * <select id="passwdCheck" resultType="int" parameterType="Map">
  * @param map
  * @return ��ġ�ϴ� ���ڵ� ����
  */
public int passwdCheck(HashMap map);
}
