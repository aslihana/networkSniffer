import jpcap.PacketReceiver;
import jpcap.packet.Packet;

public class sniffer_PacketContents implements PacketReceiver
{
    public void receivePacket(Packet packet)
    {
        sniffer_Interface.TA_OUTPUT.append(
                packet.toString() +
                        "\n" +
                        "---\n\n");
    }
}
