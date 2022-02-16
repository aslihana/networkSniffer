import javax.swing.*;
import jpcap.*;
import java.io.*;
import java.net.NetworkInterface;

public class sniffer_Interface extends javax.swing.JFrame{
    NetworkInterface[] NETWORK_INTERFACES;
    JpcapCaptor CAP;
    sniffer_CaptureThread CAPTAIN;
    int INDEX = 0;
    int COUNTER = 0;
    boolean CaptureState = false;

    public void CapturePackets()
    {
        CAPTAIN = new sniffer_CaptureThread()
        {
            public Object construct()
            {
                TA_OUTPUT.setText("\nNOW CAPTURING on INTERFACE" + INDEX + "..." + "\n" + "\n\n");

                try
                {
                    CAP = JpcapCaptor.openDevice(NETWORK_INTERFACES[INDEX], 65535, false, 20);

                    while(CaptureState)
                    {
                        CAP.processPacket(1, new sniffer_PacketContents());
                    }
                    CAP.close();
                }
                catch(Exception X)
                {
                    System.out.print(X);
                }
                return 0;
            }
            CAP.close();

            public void finished()
            {
                this.interrupt();
            }
        };

        CAPTAIN.start();
    }

    public sniffer_Interface()
    {
        initComponents();
        this.setTitle("sniffer v1");
        this.setSize(765, 480);
        this.setLocation(200,200);
        DisableButtons();
    }

    public void DisableButtons()
    {
        B_CAPTURE.setEnabled(false);
        B_STOP.setEnabled(false);
        B_SELECT.setEnabled(false);
        B_FILTER.setEnabled(false);
        B_SAVE.setEnabled(false);
    }

    public void EnableButtons()
    {
        B_CAPTURE.setEnabled(true);
        B_STOP.setEnabled(true);
        B_SELECT.setEnabled(true);
        B_FILTER.setEnabled(true);
        B_SAVE.setEnabled(true);
        B_LOAD.setEnabled(true);
    }

    public void ListNetworkInterfaces()
    {
        NETWORK_INTERFACES = JpcapCaptor.getDeviceList();

        TA_OUTPUT.setText("");

        for(int i = 0; i < NETWORK_INTERFACES.length; i++)
        {
            TA_OUTPUT.append("\n\n Interface" + i +
                    "Info");
            TA_OUTPUT.append("\n Interface Number: " +i);
            TA_OUTPUT.append("\n Description:" +
                                NETWORK_INTERFACES[i]).name + "(" +
                                NETWORK_INTERFACES[i].description+")");
            TA_OUTPUT.append("\nDatalink Name: " +
                                NETWORK_INTERFACES[i].datalink_name + "(" +
                                NETWORK_INTERFACES[i].datalink_description+")");
            TA_OUTPUT.append("\n MAC ADDRESS");

            byte[] R = NETWORK_INTERFACES[i].mac_address;

            for(int A = 0; A <= NETWORK_INTERFACES[i].length; A++)
                TA_OUTPUT.append(Integer.toHexString(R[A] & 0xff) + "i");

            NetworkInterfaceAddress [] INT = NETWORK_INTERFACES[i].addresses;
            TA_OUTPUT.append("\n IP Address: " + INT[0].address);
            TA_OUTPUT.append("\n Subnet Mask: " + INT[0].subnet);
            TA_OUTPUT.append("\n Broadcast Address: " + INT[0].broadcast);

            COUNTER++;
        }
    }

    public void ChooseInterface()
    {
        int TEMP = Integer.parseInt(TF_SelectInterface.getText());

        if(TEMP > -1 && TEMP < COUNTER)
        {
            INDEX = TEMP;
            EnableButtons();
        }

        else
        {
            JOptionPane.showMessageDialog(
                    null,"Outside of RANGE. # interfaces = 0-" + (COUNTER-1) + ".");
            )
        }

        TF_SelectInterface.setText("");
    }

    public static void SaveCaptureData()
    {
        String CaptureData = TA_OUTPUT.getText();

        try
        {
            File DATA = new File("CaptureData.txt");

        }
    }
}
