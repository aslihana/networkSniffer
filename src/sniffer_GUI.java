import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jpcap.*;
import java.io.*;
import java.net.NetworkInterface;

public class sniffer_GUI {
    NetworkInterface[] NETWORK_INTERFACES;
    JpcapCaptor CAP;
    sniffer_CaptureThread CAPTAIN;
    int INDEX = 0;
    int COUNTER = 0;
    boolean CaptureState = false;

    Jframe MainWindow = new JFrame("sniffer v1");

    public static JTextArea TA_OUTPUT = new JTextArea();

}
