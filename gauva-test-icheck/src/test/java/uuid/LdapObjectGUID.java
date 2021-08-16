package uuid;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static guavagooglelibrary.GuavaCollectionsTest.print;

public class LdapObjectGUID {

    @Test
    public void getBigAndLittleEndianUUID(){
        byte[] byteArrayObjectGUID = Base64.getDecoder().decode("TzdveoQ/1EuOQlxd7LtYxw==");
        List<byte[]> bytesAsList = Arrays.asList(byteArrayObjectGUID);
        reverseByteArray(byteArrayObjectGUID);
        byte[] byteReverseArrayObjectGUIDfromListOfBytes = getArrayBytesFromListOfBytes(bytesAsList);
        UUID uuidv1 = getUuidv1or4AndPrint(byteArrayObjectGUID);
        UUID uuidv1R = getUuidv1or4AndPrint(reverseByteArray(byteArrayObjectGUID));
        UUID uuidv3 = UUID.nameUUIDFromBytes(byteArrayObjectGUID);
        UUID uuidv3R = UUID.nameUUIDFromBytes(reverseByteArray(byteReverseArrayObjectGUIDfromListOfBytes));
        print(uuidv1 + " это цифра версии " + uuidv1.version() + " , а вариант UUID:  " + uuidv1.variant()); // c758bbec-5d5c-428e-c758-bbec5d5c428e
        print(uuidv1R + " это цифра версии " + uuidv1R.version() + " , а вариант UUID:  " + uuidv1R.variant()); // 4f376f7a-843f-d44b-c758-bbec5d5c428e (old: 4f376f7a-843f-d44b-4f37-6f7a843fd44b)
        print(uuidv3 + " это цифра версии " + uuidv3.version() + " , а вариант UUID:  " + uuidv3.variant()); // 1df3d253-cda3-3a4c-998e-db082b384ca1
        print(uuidv3R + " это цифра версии " + uuidv3R.version() + " , а вариант UUID:  " + uuidv3R.variant()); // 49cdf5bb-787a-3af0-a790-25f0e6c87dd7
    }

    @Test
    public void getUUIDofLDAP(){
        byte [] bytes30getValueFstringValue = new byte[]  {79, 0, 55, 0, 111, 0, 122, 0, -3, -1, 63, 0, -3, -1, 75, 0, -3, -1, 66, 0, 92, 0, 93, 0, -3, -1, 88, 0, -3, -1};
        byte [] bytes25getValueGetBytes = new byte[]  {79, 55, 111, 122, -17, -65, -67, 63, -17, -65, -67, 75, -17, -65, -67, 66, 92, 93, -17, -65, -67, 88, -17, -65, -67};
        byte[] byteArrayOfgetValueByteArray = new byte [] {79, 55, 111, 122, -124, 63, -44, 75, -114, 66, 92, 93, -20, -69, 88, -57};
        byte[] userObjectGUID = new byte [] {84, 122, 100, 118, 101, 111, 81, 47, 49, 69, 117, 79, 81, 108, 120, 100, 55, 76, 116, 89, 120, 119, 61, 61};
        //byte[] byteArrayObjectGUID23 = new byte []{4F, 37, 6F, 7A, 84, 3F, D4, 4B, 8E, 42, 5C, 5D, EC, BB, 58, C7};
        // 4F 37 6F 7A 84 3F D4 4B 8E 42 5C 5D EC BB 58 C7
        getUuidv1or4AndPrint(bytes30getValueFstringValue);
        getUuidv1or4AndPrint(bytes25getValueGetBytes);
        // HexUtils.byteArrayToHex();
        getUuidv1or4AndPrint(byteArrayOfgetValueByteArray);
        getUuidv1or4AndPrint(reverseByteArray(bytes30getValueFstringValue));
        getUuidv1or4AndPrint(reverseByteArray(bytes25getValueGetBytes));
        getUuidv1or4AndPrint(reverseByteArray(byteArrayOfgetValueByteArray));
        print(encodeHexString(bytes30getValueFstringValue));
        getUuidv1or4AndPrint(reverseByteArray(userObjectGUID));
        print("========================двоичное кодирование GUID в java: ");
        getUuidv1or4AndPrint(byteArrayOfgetValueByteArray);
        print("========================двоичное кодирование GUID в .NET содержит байты в первых трех группах, расположенных в малоэндианном (обратном) порядке: ");
        print(toMSLdapUUID(byteArrayOfgetValueByteArray)); // ecbb58c7-5c5d-8e42-4bd4-3f847a6f374f - need to be reverse
        print(toMSLdapUUID(reverseByteArray(byteArrayOfgetValueByteArray))); // 7a6f374f-3f84-4bd4-8e42-5c5decbb58c7 - WIN!

    }





    private UUID getUuidv1or4AndPrint(byte[] byteArrayObjectGUID) {
        UUID curUUID = new UUID(ByteBuffer.wrap(byteArrayObjectGUID).getLong(), ByteBuffer.wrap(byteArrayObjectGUID).getLong());
        print(curUUID + " это цифра версии " + curUUID.version() + " , а вариант UUID:  " + curUUID.variant());
        return curUUID;
    }



    public UUID toMSLdapUUID(byte[] binaryEncoding) {
        ByteBuffer source = ByteBuffer.wrap(binaryEncoding);
        ByteBuffer target = ByteBuffer.allocate(16).
                order(ByteOrder.LITTLE_ENDIAN).
                putInt(source.getInt()).
                putShort(source.getShort()).
                putShort(source.getShort()).
                order(ByteOrder.BIG_ENDIAN).
                putLong(source.getLong());
        target.rewind();
        //return new UUID(target.getLong(), target.getLong());
        UUID curUUID = new UUID(target.getLong(), target.getLong());
        print(curUUID + " это цифра версии " + curUUID.version() + " , а вариант UUID:  " + curUUID.variant());
        return curUUID;
    }


    public String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }


    public String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    private byte[] getArrayBytesFromListOfBytes(List<byte[]> anyListOfBytes) {
        return anyListOfBytes.stream()
                .collect(
                        () -> new ByteArrayOutputStream(),
                        (b, e) -> {
                            try {
                                b.write(e);
                            } catch (IOException e1) {
                                throw new RuntimeException(e1);
                            }
                        },
                        (a, b) -> {}).toByteArray();
    }

    public static byte[] reverseByteArray(byte[] array) {
        if (array == null) {
            return array;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
        return array;
    }

}
