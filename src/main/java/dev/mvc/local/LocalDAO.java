package dev.mvc.local;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.local.LocalDAO")
public class LocalDAO{
  @Autowired
  private SqlSession sqlSession; // MyBATIS 3 ¿¬°á °´Ã¼
  
  public LocalDAO(){
    System.out.println("--> LocalDAO created.");
  }
  
  public LocalMepperInter mapper(){
    LocalMepperInter mapper = sqlSession.getMapper(LocalMepperInter.class);    
    
    return mapper;
  }
  
  public LocalVO read(String mAddress) {
    return mapper().read(mAddress);
  }
  
}
