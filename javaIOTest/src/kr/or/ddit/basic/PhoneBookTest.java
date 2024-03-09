package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
   처리속도 제일 빠른 것 CPU / 제일 느린 것
   속도가 느린 장치와 속도 빠른 장치 있다 그러면 중간에 빠르고 느린속도 사이의 중간속도 내는 기억장치를 만든다.
   중간에 있는 것을 거쳐서 작업
   예) 입력 
   버퍼 임시 기억장소 속도가 CPU보다 느리지만 입력장치보다 빠름
   버퍼한테 입력해라 입력값 있으면 나한테 줘 그럼 버퍼가 자기가 가지고 있는 데이터 중 한개를 CPU 줌
   만약 CPU가 달라했는데 버퍼에 데이터 없어 그러면 입력장치에서 데이터 가져와야함
   입력장치에서 데이터 가져오는 역할 버퍼가 함 CPU는 명령 내리기만 하면됨 그 시간만 걸림 이후 자기 할일함
   이렇게하면서 속도차이 보완해줌
   (버퍼, 캐쉬, .. 중간 속도 빠르게 하기 위한 임시 기억 장치이다.)
   입출력 관련된 임시기억장치를 버퍼라고 함
   입력과 관련된 것 -> 입력버퍼, 출력 관련된 것 -> 출력 버퍼
   
   키보드
   
   입력 버퍼
   
   CPU
   (next 뒤에 타입 )
   - scan.next()/nextint()nextfloat() ...  | (결다른 것) nextLine()
     -> 이것들은 입력버퍼에 명령 데이터 가져오라고
     그러고 입력버퍼에 없으면 키보드에 명령?
     
     키보드에서 입력한 것이 버퍼에 저장되는데 
    1 2 3 enter 했다. 그러면 버퍼에 저장 -> cpu 주는데 cpu는 그 값들을 구별한다.
    뭘로? 사이띄기(공백), tab키, enter키로 구분해서 구분된 데이터를 보낸다.(버퍼가 cpu에 보낼때)
    
   버퍼에 데이터 이미 있으면 빠르게 cpu에 갈 수 있지.
   
   예)
   키보드: 대전시 중구 입력
   
   입력버퍼: 
   이름, 전화번호 데이터넘어갔는데
   주소 '대전시 중구' 저장 왜 오류 났을까?
   next(string타입) 은 데이터 '대전시'만 보내졌음 (중구 버퍼에 남아있음)
   insert 메서드 끝나면 호출했던 곳으로 돌아가겠지 더이상 처리할것없으니 break만나지
   그러면 switch문 나가겠지. 이후 반복문 만나니 true 또 실행해
   displayMenu()
   retrun scan.nextint() -> 이거 받는데 '중구' 데이터 받는다. 
   숫자로 바꿀 수 없으니까.. 그래서 에러가 났음
   
   그럴때는 ? scan.nextLine();
   -> 한줄 전체를 모두 가져간다. (엔터키도 포함)
     (반환할때는 엔터키를 제외하고 반환)
   
   전화번호 입력하고 next 엔터키 남아있지 그런데 nextLine
   엔터키 남아있어 -> 반환은 엔터키 빼고하니 반환할거 없어 ㅇ.ㅇ? 
   (등록할때 주소는 nextLine enter 가져와서-> 공백반환됨 -> 주소 못적음..)
   
   이거 해결하려면? 엔터 없애야지
   실제 주소 받기 전에 nextLine 한번 더 실행하면된다. enter 가져간건 쓸모없음 그거안쓰면 돼
   
   전화번호 >> scan.next();찌꺼기를 nextLine 사용 전에 없애주기
   scan.nextLine(); 입력 버퍼 지우기

 */

/*
문제) 이름, 전화번호, 주소를 멤버로 갖는 Phone클래스를 작성한다.
   Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
   
   (Map의 구조는 key값으로 '사람의 이름'을 사용하고 value값으로는 'Phone클래스의 인스턴스'로 한다.)
   HashMap<String, Phone> 변수명;
   
   아래의 메뉴의 기능을 구현하시오.(삭제, 검색 기능에서는 '이름'을 입력 받아 처리한다.)
- 추가 조건) 
   1. '6. 전화번호 저장 ' 메뉴를 추가하고 구현
      (저장 파일명은 'phoneBookData.dat'로 한다)
   2. 프로그램이 시작될때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅
   3. 프로그램을 종료할때 Map의 데이터가 변경되거나 추가 또는 삭제되면 저장한 후 종료되도록 한다
실행예시)

   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   --------------------
   번호 입력 >> 1
   
   새롭게 등록할 전화번호 정보를 입력하세요.
   이 름 >> 홍길동
   전화번호 >> 010-1234-5678
   주 소 >> 대전시 중구 오류동
   
  '홍길동'씨의 전화번호 등록이 완료되었습니다..

   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   --------------------
   번호 입력 >> 1
   
   새롭게 등록할 전화번호 정보를 입력하세요.
   이 름 >> 홍길동
      
  '홍길동'씨는 이미 등록된 사람 입니다...
  
   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   --------------------
   번호 입력 >> 5
   
  ---------------------------------------
    번호   이 름        전화번호          주   소     
  ---------------------------------------
   1    홍길동     010-1234-5678    대전시 중구 오류동
   ~~~
  ---------------------------------------
    출력완료 ~~
  
   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   --------------------
   번호 입력 >> 0
   
  프로그램을 종료합니다...
    
    
*/

public class PhoneBookTest {
   // 꺾쇠괄호에 제네릭
   private HashMap<String, Phone> phoneBookMap;
   private Scanner scan;
   private String fileName = "d:/d_other/phoneBookData.dat";
   // 데이터가 변경되었는지 여부를 나타내는 변수 선언
   // 데이터가 변경되면 이 변수는 true값이 된다.
   private boolean dataChange;

   // 생성자 (여기서 파일에서 가져온 데이터 셋팅해주는 것이 좋음)
   public PhoneBookTest() {
//      phoneBookMap = new HashMap<String, Phone>();
      phoneBookMap = load(); 
      scan = new Scanner(System.in);
   }

   public static void main(String[] args) {
//      PhoneBookTest test = new PhoneBookTest();
//      test.phoneBookStart();

      new PhoneBookTest().phoneBookStart();
   }

   // 시작 메서드
   public void phoneBookStart() {
   
      System.out.println("+++++++++++++++++++++++++++");
      System.out.println("   전 화 번 호 관 리 프 로 그 램");
      System.out.println("+++++++++++++++++++++++++++");
      System.out.println();

      while (true) {
         int choice = displayMenu();
         switch (choice) {
         case 1: // 등록
            insert();
            break;
         case 2: // 수정
            update();
            break;
         case 3: // 삭제
            delete();
            break;
         case 4: // 검색
            research();
            break;
         case 5: // 전체 출력
            printPhoneBook();
            break;
         case 6: // 저장
            save();
            break;
         case 0: // 종료
            System.out.println();
            // 데이터가 변경되었는지 확인 후 저장하기
            if(dataChange) save();
            
            System.out.println("프로그램을 종료합니다...");
            return;
         default:
            System.out.println();
            System.out.println("작업 번호를 잘못 입력 했습니다.");
            System.out.println("다시 선택하세요...");
         }
      }
   }
   
//파일에 저장된 전화번호 정보를 읽어오는 메서드
   private HashMap<String, Phone> load(){
      HashMap<String, Phone> pMap = null; //읽어온 데이터가 저장될 변수
      
      //읽어올 파일이 있는지 검사
      File file = new File(fileName);
      if (!file.exists()) { //저장된 파일이 없을때 
         return new HashMap<String, Phone>(); //빈 Map객체를 생성해서 반환
      }
      //저장된 파일이 있을 때 처리되는 곳
      ObjectInputStream oin = null;
      try {
         //입력용 스트림 객체 생성
         oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
         
         //파일에 저장된 자료를 읽어와 Map에 저장하기 
         
         // save메서드에서 '방법1' 로 저장 했을때.
         // pMap = (HashMap<String, Phone>)oin.readObject();
         
         //------------
         
         // 2) save메서드에서 '방법2'로 저장 햇을때.
         pMap = new HashMap<String, Phone>();
         Object obj = null;
         
         while ((obj = oin.readObject()) != null) {
            //읽어온 자료를 Map에 추가하기 
            Phone p = (Phone) obj;
            pMap.put(p.getName(), p); // 폰 객체에서 이름을 가져오고 벨류 값은 p
         }
      } catch (IOException e) {
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } finally {
         if(oin!=null) try {oin.close();}catch(IOException e){}
      }
      
      return pMap;
   }
   
   // 자료를 저장하는 메서드
   private void save() {
      ObjectOutputStream oout = null;
      try {
         // 출력용 스트림 객체 생성
         oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
         // Map 에 저장된 데이터 파일로 저장하기

         // 방법1 ==> Map 객체 자체를 저장하기
         // oout.writeObject(phoneBookMap);

         // 방법2 ==> Map 객체 자체를 저장하기
         for (String key : phoneBookMap.keySet()) {
            Phone p = phoneBookMap.get(key);
            oout.writeObject(p);
         }
         oout.writeObject(null);
         System.out.println("저장이 완료 되었습니다.");
         
         dataChange = false;

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         // 사용했던 스트림 닫기
         if (oout != null)
            try {
               oout.close();
            } catch (IOException e) {
            }
      }

   }

   // 자료를 검색하는 메서드
   private void research() {
      System.out.println();
      System.out.println("검색할 전화번호 정보를 입력하세요...");
      System.out.println("이 름 >> ");
      String name = scan.next();

      // 등록 여부 검사
      if (!phoneBookMap.containsKey(name)) {
         System.out.println("'" + name + "'씨는 등록되지 않은 사람입니다...");
         System.out.println("삭제 작업 종료...");
         return;
      }

      Phone p = phoneBookMap.get(name);

      System.out.println(name + "씨 전화번호 정보");
      System.out.println("======================");
      System.out.println("이      름 : " + p.getName());
      System.out.println("전화번호 : " + p.getTel());
      System.out.println("주      소 : " + p.getAddr());
      System.out.println("======================");
      System.out.println();
   }

   // 자료를 삭제하는 메서드
   private void delete() {
      System.out.println();
      System.out.println("삭제할 전화번호 정보를 입력하세요...");
      System.out.println("이 름 >> ");
      String name = scan.next();

      // 등록 여부 검사
      if (!phoneBookMap.containsKey(name)) {
         System.out.println("'" + name + "'씨는 등록되지 않은 사람입니다...");
         System.out.println("삭제 작업 종료...");
         return;
      }

      phoneBookMap.remove(name);

      System.out.println(name + "씨의 전화번호 정보를 삭제했습니다.");
      dataChange = true;
   }

   // 자료를 수정하는 메서드
   private void update() {
      System.out.println();
      System.out.println("수정할 전화번호 정보를 입력하세요...");
      System.out.print("이 름 >> ");
      String name = scan.next();

//      if(phoneBookMap.containsKey(name)==false) {
      if (!phoneBookMap.containsKey(name)) { // 해당 이름이 없으면 수정 작업을 못한다.
         System.out.println("'" + name + "'씨의 전화번호 정보가 없습니다.");
         System.out.println("수정 작업 종료...");
         return;
      }

      System.out.print("새로운 전화번호 >> ");
      String newTel = scan.next();

      scan.nextLine(); // 입력 버퍼 비우기

      System.out.print("새로운 주소 >> ");
      String newAddr = scan.nextLine();

      // 같은 key값에 새로운 전화번호 정보를 저장한다. ==> 수정 작업
      phoneBookMap.put(name, new Phone(name, newTel, newAddr));

      System.out.println(name + "씨의 전화번호 정보를 변경했습니다...");
      dataChange = true;
   }

   // 전체 자료를 출력하는 메서드
   private void printPhoneBook() {
      System.out.println();

      Set<String> phoneKeySet = phoneBookMap.keySet();

      System.out.println();
      System.out.println("------------------------------------------------");
      System.out.println("  번호      이  름       전화번호       주 소");
      System.out.println("------------------------------------------------");

      if (phoneKeySet.size() == 0) {
         System.out.println("       등록된 사람이 하나도 없습니다...");
      } else {
         int num = 0; // 번호를 나타내는 번수
         for (String key : phoneKeySet) { // key값 개수만큼 반복...
            num++;
            Phone p = phoneBookMap.get(key); // key값을 이용하여 Phone객체 구하기

            System.out.println("  " + num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
         }
      }
      System.out.println("------------------------------------------------");
      System.out.println("   출력 끝...");

   }

   // 새로운 전화번호 정보를 등록하는 메서드
   private void insert() {
      System.out.println();
      System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
      System.out.print("이 름 >> ");
      String name = scan.next();

      // 이미 등록된 사람인지 검사하기 ==> key값에 입력 받은 이름이 있는지 검사...
      if (phoneBookMap.containsKey(name)) {
         System.out.println("'" + name + "' 씨는 이미 등록된 사람입니다...");
         return;
      }

      System.out.print("전화번호 >> ");
      String tel = scan.next();

      scan.nextLine(); // 입력 버퍼 비우기

      System.out.print("주 소 >> ");
      String addr = scan.nextLine();

//      Phone p = new Phone(name, tel, addr);
//      phoneBookMap.put(name, p);

      phoneBookMap.put(name, new Phone(name, tel, addr));

      System.out.println(name + " 전화번호 등록 완료!!!");
      dataChange = true;
      
   }

   // 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
   private int displayMenu() {
      System.out.println();
      System.out.println("--------------------");
      System.out.println(" 1. 전화번호 등록");
      System.out.println(" 2. 전화번호 수정");
      System.out.println(" 3. 전화번호 삭제");
      System.out.println(" 4. 전화번호 검색");
      System.out.println(" 5. 전화번호 전체 출력");
      System.out.println(" 6. 전화번호 저장");
      System.out.println(" 0. 프로그램 종료");
      System.out.println("--------------------");
      System.out.print("번호 입력 >> ");
      return scan.nextInt();
   }

}

// 이름, 전화번호, 주소를 멤버로 갖는 Phone클래스를 작성한다.
class Phone implements Serializable{
   private String name;
   private String tel;
   private String addr;

   public Phone(String name, String tel, String addr) {
      super();
      this.name = name;
      this.tel = tel;
      this.addr = addr;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   @Override
   public String toString() {
      return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
   }

}