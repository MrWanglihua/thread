package serializable.serialVersionUID;

import java.io.*;

public class JavaSerializableWithFile implements ISerializable{
    @Override
    public <T> byte[] serializable(T obj) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("user")));
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }

    @Override
    public <T> T deSerializable(byte[] data, Class<T> clazz) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("user")));
            return (T)ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
