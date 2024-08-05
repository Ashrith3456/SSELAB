public class Checksum{

    // Method to calculate the XOR checksum of a byte array
    public static byte calculateChecksum(byte[] data) {
        byte checksum = 0;
        for (byte b : data) {
            checksum ^= b;  // XOR each byte of data with the checksum
        }
        return checksum;
    }

    // Method to verify if the checksum of the data matches the expected checksum
    public static boolean verifyChecksum(byte[] data, byte expectedChecksum) {
        return calculateChecksum(data) == expectedChecksum;
    }

    public static void main(String[] args) {
        // Example data
        byte[] data = "Hello, world!".getBytes();

        // Calculate checksum
        byte checksum = calculateChecksum(data);
        System.out.println("Calculated Checksum: " + String.format("0x%02X", checksum));

        // Simulate data transmission by adding a checksum to the data
        byte[] dataWithChecksum = new byte[data.length + 1];
        System.arraycopy(data, 0, dataWithChecksum, 0, data.length);
        dataWithChecksum[data.length] = checksum;

        // Verify checksum
        boolean isValid = verifyChecksum(data, dataWithChecksum[data.length]);
        System.out.println("Checksum Valid: " + isValid);
    }
}
