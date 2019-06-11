package serializable.serialVersionUID;

import serializable.socket.User;

public class SerializableDemo {

    public static void main(String[] args) {

        ISerializable serializable = new JavaSerializableWithFile();

        User user = new User();
        user.setName("Mic");
        user.setAge(18);

//        serializable.serializable(user);

        User user1 = serializable.deSerializable(null, null);
        System.out.println(user1);


    }
}
