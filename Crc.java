import java.util.zip.CRC32;

public class CRC {
    public static void main(String[] args) {
        // Example data
        String data = "Hello, CRC!";
        
        // Calculate CRC32 checksum
        long checksum = calculateCRC32(data.getBytes());
        
        System.out.println("CRC32 Checksum: " + checksum);
        
        // Simulate a data verification scenario
        String receivedData = "Hello, CRC!";
        long receivedChecksum = calculateCRC32(receivedData.getBytes());
        
        if (checksum == receivedChecksum) {
            System.out.println("Data integrity verified.");
        } else {
            System.out.println("Data integrity verification failed.");
        }
    }
    
    // Method to calculate CRC32 checksum
    public static long calculateCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }
}
