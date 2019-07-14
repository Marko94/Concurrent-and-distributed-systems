package other;

public class Connection {
    private String id;
    private String address;
    private int port;
    private boolean initiated;

    public Connection(String id, String address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
        this.initiated = false;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String uid) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public boolean isInitiated() {
        return initiated;
    }

    public void setInitiated(boolean initiated) {
        this.initiated = initiated;
    }
}
