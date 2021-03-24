/*
GDCuyasen
http://gmac.2600tech.com/
*/
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class serialPort {

    SerialPort activePort;
    SerialPort[] ports = SerialPort.getCommPorts();

    public void showAllPort() {
        int i = 0;
        for(SerialPort port : ports) {
            System.out.print(i + ". " + port.getDescriptivePortName() + " ");
            System.out.println(port.getPortDescription());
            i++;
        }
    }

    public void setPort(int portIndex) {
        activePort = ports[portIndex];

        if (activePort.openPort())
            System.out.println(activePort.getPortDescription() + " port opened.");

        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public void serialEvent(SerialPortEvent event) {
                int size = event.getSerialPort().bytesAvailable();
                byte[] buffer = new byte[size];
                event.getSerialPort().readBytes(buffer, size);
                for(byte b:buffer)
                    System.out.print((char)b);
            }
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
        });
    }

    public void start() {
        showAllPort();
        Scanner reader = new Scanner(System.in);
        System.out.print("Port? ");
        int p = reader.nextInt();
        setPort(p);
        reader.close();
    }

    public static void main(String[] args) {
        serialPort mainClass = new serialPort();
        mainClass.start();
    }

}

//package de.mschoeffler.arduino.serialcomm.example01;
//
//        import java.io.IOException;
//        import com.fazecast.jSerialComm.SerialPort;
//
///**
// * Simple application that is part of an tutorial.
// * The tutorial shows how to establish a serial connection between a Java and Arduino program.
// * @author Michael Schoeffler (www.mschoeffler.de)
// *
// */
//public class Startup {
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        SerialPort sp = SerialPort.getCommPort("/dev/ttyACM1"); // device name TODO: must be changed
//        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
//        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
//
//        if (sp.openPort()) {
//            System.out.println("Port is open :)");
//        } else {
//            System.out.println("Failed to open port :(");
//            return;
//        }
//
//        for (Integer i = 0; i < 5; ++i) {
//            sp.getOutputStream().write(i.byteValue());
//            sp.getOutputStream().flush();
//            System.out.println("Sent number: " + i);
//            Thread.sleep(1000);
//        }
//
//        if (sp.closePort()) {
//            System.out.println("Port is closed :)");
//        } else {
//            System.out.println("Failed to close port :(");
//            return;
//        }
//
//
//    }
//
//}
