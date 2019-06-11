package serializable.serialVersionUID;

public interface ISerializable {

    <T> byte[] serializable(T obj);

    <T> T deSerializable(byte[] data,Class<T> clazz);

}
