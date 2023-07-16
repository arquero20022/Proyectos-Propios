package clientev6;




import java.io.Serializable;

public class FileData implements Serializable {
    private byte[] content;
    private String hash;

    public FileData(byte[] content, String hash) {
        this.content = content;
        this.hash = hash;
    }

    public byte[] getContent() {
        return content;
    }

    public String getHash() {
        return hash;
    }
}

