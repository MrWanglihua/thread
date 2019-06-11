package serializable.serialVersionUID;

import java.io.*;

public class JavaSerializable implements ISerializable{
    @Override
    public <T> byte[] serializable(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(obj);

            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }

    @Override
    public <T> T deSerializable(byte[] data, Class<T> clazz) {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            return (T)ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
