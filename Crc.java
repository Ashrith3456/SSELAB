import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.util.Arrays;

public class Crc {

    // Method to calculate CRC32 checksum of a byte array
    public static long calculateCRC32(byte[] data) {
        CRC32 crc = new CRC32();
        crc.update(data);
        return crc.getValue();
    }

    // Method to verify if the CRC32 checksum of the data matches the expected checksum
    public static boolean verifyCRC32(byte[] data, long expectedChecksum) {
        return calculateCRC32(data) == expectedChecksum;
    }

    public static void main(String[] args) {
        // Example data
        byte[] data = "Hello, world!".getBytes();
        
        // Calculate checksum
        long checksum = calculateCRC32(data);
        System.out.println("Calculated CRC32 Checksum: " + Long.toHexString(checksum));

        // Simulate data transmission by adding a checksum to the data
        byte[] dataWithChecksum = new byte[data.length + 8]; // Allocate space for checksum (8 bytes for long)
        System.arraycopy(data, 0, dataWithChecksum, 0, data.length);
        System.arraycopy(longToBytes(checksum), 0, dataWithChecksum, data.length, 8);

        // Extract the checksum from the transmitted data
        byte[] dataWithoutChecksum = Arrays.copyOf(dataWithChecksum, dataWithChecksum.length - 8);
        byte[] receivedChecksumBytes = Arrays.copyOfRange(dataWithChecksum, dataWithChecksum.length - 8, dataWithChecksum.length);
        long receivedChecksum = bytesToLong(receivedChecksumBytes);

        // Verify checksum
        boolean isValid = verifyCRC32(dataWithoutChecksum, receivedChecksum);
        System.out.println("Checksum Valid: " + isValid);
    }

    // Utility method to convert a long to a byte array
    private static byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (value >> (56 - i * 8));
        }
        return bytes;
    }

    // Utility method to convert a byte array to a long
    private static long bytesToLong(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value = (value << 8) | (bytes[i] & 0xFF);
        }
        return value;
    }
}
