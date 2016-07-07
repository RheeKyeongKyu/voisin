package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MemberMapperInter {
  
  /**
   * 계정 등록
   * @param memberVO 계정 등록 정보
   * @return 
   * <insert id="create" parameterType="MemberVO">
   */
  public int create(MemberVO memberVO);
  
  /**
   * 중복 메일 확인
   * @param mail 검사할 메일 
   * @return
   * <select id='checkEmail' resultType='int' parameterType='String'>
   */
  public int checkEmail(String email);
  
  /**
   * 관리자 검사
   * @param master
   * @return
   * <select id="checkMaster" resultType='int' parameterType='String'>
   */
  public int checkMaster(String master);
  
  /**
   * 관리자용 목록
   * @return
   * <select id="list" resultType="MemberVO">
   */
  /*public ArrayList<MemberVO> list();*/
  /**
   * 관리자용 목록(검색 부분 추가)
   * <select id="list" resultType="MemberVO" parameterType="HashMap" >
   */
  public ArrayList<MemberVO> list(HashMap hashMap);
  
  /**
   * 검색된 레코드 갯수
   * @param hashMap
   * @return
   */
  //<select id="count" resultType="int" parameterType="HashMap" >
  public int count(HashMap hashMap);
  
  /**
   * 로그인
   * <select id="login" resultType="int" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
 public int login(MemberVO memberVO);
 
 /**
  * 이메일로 mno, master, confirm검출
  * @param email
  * @return
  * <select id="readByEmail" resultType="MemberVO" parameterType="String">
  */
 public MemberVO readByEmail(String email); 
 
 /**
  * 인증코드 확인
  * @param memberVO
  * @return
  *  <update id="confirm" parameterType="MemberVO">
  */
 public int confirm(MemberVO memberVO);
 
 /**
  * 마일리지 적립
  * @param memberVO
  * @return
  * <update id="mileage" parameterType="String">
  */
 public int mileage(String email);
 
 /**
  * 회원 정보 조회
  * <select id="read" resultType="MemberVO" parameterType="int">
  * @param mno
  * @return
  */
 public MemberVO read(int mno); 
 
 /**
  * 회원 정보 수정
  * @param memberVO
  * @return
  */
 public int update(MemberVO memberVO);
 
 /**
  * 회원 삭제
  * @param mno
  * @return
  * <update id="delete" parameterType="int">
  */
 public int delete(int mno);
 
 /**
  * 회원 삭제 (관리자 적용 부분)
  * @param email
  * @return
  *  <select id="delete_master" resultType="int" parameterType="String">
  */
 public int delete_master(String email);
 
 /**
  * 패스워드 변경
  * @param memberVO
  * @return
  * <update id="passwd_update" parameterType="MemberVO">
  */
 public int passwd_update(MemberVO memberVO);
 
 /**
  * 패스워드 일치 검사
  * <select id="passwdCheck" resultType="int" parameterType="Map">
  * @param map
  * @return 일치하는 레코드 갯수
  */
public int passwdCheck(HashMap map);
}
