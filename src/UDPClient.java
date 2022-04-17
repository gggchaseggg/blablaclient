import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient{
    //Порт сервера, к которому собирается подключиться клиентский сокет
    public final static int SERVICE_PORT = 50001;

    public static void main(String[] args) throws IOException{
        try{
            //Создайте экземпляр клиентского сокета. Нет необходимости в привязке к определенному порту
            DatagramSocket clientSocket = new DatagramSocket();

            // Получите IP-адрес сервера
            InetAddress IPAddress = InetAddress.getByName("localhost");

            while (true) {
                // Создайте соответствующие буферы
                byte[] sendingDataBuffer = new byte[1024];
                //#######################################
                //Пример тэга: Tag:00000449
                //#######################################
                Scanner console = new Scanner(System.in);
                System.out.print("<<:");
                String tag = console.next();
                // Преобразуйте данные в байты и разместите в буферах
                String sentence = "Disc:2000/01/01 02:03:04, Last:2000/01/01 02:03:04, Count:00001, Ant:01, Type:04, Tag:"+ tag;
                sendingDataBuffer = sentence.getBytes();


                // Создайте UDP-пакет
                DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, SERVICE_PORT);

                // Отправьте UDP-пакет серверу
                clientSocket.send(sendingPacket);


                // Закройте соединение с сервером через сокет
                if (false) {
                    clientSocket.close();
                }

            }
        }
        catch(SocketException e) {
            e.printStackTrace();
        }
    }
}