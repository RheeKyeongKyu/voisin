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
  private SqlSession sqlSession; // MyBATIS 3 연결 객체
  
  public MemberDAO(){
    System.out.println("--> MemberDAO created.");
  }
  
  public MemberMapperInter mapper(){
    MemberMapperInter mapper = sqlSession.getMapper(MemberMapperInter.class);    
    
    return mapper;
  }

  /**
   * 중복 이메일 확인
   * @param email
   * @return 중복 갯수
   */
  public int checkEmail(String email) {
    return mapper().checkEmail(email);
  }
  
  /**
   * 관리자 확인
   * @param master
   * @return 관리자 수
   */
  public int checkMaster(String master) {
    return mapper().checkMaster(master);
  }
  
  /**
   * 계정 생성
   */
  public int create(MemberVO memberVO) {
    return mapper().create(memberVO);
  }
  
  /**
   * 관리자용 목록
   */
/*  public ArrayList<MemberVO> list() {
    return mapper().list();
  }*/
  
  /**
   * 관리자용 목록
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
   * 검색 갯수 
   */
  public int count(HashMap hashMap) {
    return mapper().count(hashMap);
  }
  
  /**
   * 로그인
   */
  public int login(MemberVO memberVO) {
    return mapper().login(memberVO);
  }
  
  /**
   * mno, master, confirm 추출
   */
  public MemberVO readByEmail(String email) {
    return mapper().readByEmail(email);
  }
  
  /**
   * 인증코드 확인
   */
  public int confirm(MemberVO memberVO) {
    return mapper().confirm(memberVO);
  }
  
  /**
   * 마일리지 적립
   */
  public int mileage(String email) {
    return mapper().mileage(email);
  }
  
  /**
   * 정보 조회
   */
  public MemberVO read(int mno) {
    return mapper().read(mno);
  }
  
  /**
   * 정보 수정
   */
  public int update(MemberVO memberVO) {
    return mapper().update(memberVO);
  }
  
  /**
   * 계정 삭제
   */
  public int delete(int mno) {
    return mapper().delete(mno);
  }
  
  /**
   * 계정 삭제(관리자 적용 부분)
   */
  public int delete_master(String email){
    return mapper().delete_master(email);
  }
  
  /**
   * 패스워드 변경
   */
  public int passwd_update(MemberVO memberVO) {
    return mapper().passwd_update(memberVO);
  }
  
  /**
   * 패스워드 일치 검사
   */
  public int passwdCheck(MemberVO memberVO){
    int mno = memberVO.getMno();
    String passwd = memberVO.getOld_passwd(); // 기존 패스워드
    
    HashMap map = new HashMap();
    map.put("mno", mno);
    map.put("passwd", passwd);
    
    return mapper().passwdCheck(map);
  }



  
 
  
  
  
  
  
  
}
