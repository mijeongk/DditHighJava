package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
   
   public static void main(String[] args) {
      //서버 소켓을 만들고, 클라이언트가 접속해오면
      // 소켓을 만들어서 메시지를 받는 쓰레드와 메시지를 보내는 쓰레드에
      // 이 소켓을 주입한 후 쓰레드들을 실행하면 된다.
      
      try {
         ServerSocket server = new ServerSocket(7777);
         System.out.println("서버가 준비중입니다...");
         
         Socket socket = server.accept(); // client 기다리는 것 
         // 여러번 실행시키면 여러명과 할 수 있다. 근데 단순하게 여러번하면
         // 첫번째 접속하겠지~ 또 소켓이 저장되갰지. 결과적으로 맨 나중에 한사람만 저장된다. (덮어지는거임)
         // 앞사람 기억하려면? 
         // 배열에 있는 소켓 이용해서 보내고싶다? outputstream 만들어서 각각의 사람에게 데이터 보낼 수 있음
         // 서버 한사람 메시지 보내면 여러명에게 분배하믄댐 
         
         Sender sender = new Sender(socket);
         Receiver receiver = new Receiver(socket);
         
         sender.start();
         receiver.start();
         
      } catch (IOException e) {
         // TODO: handle exception
      }
   }
}