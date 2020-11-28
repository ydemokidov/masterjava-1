package ru.javaops.masterjava.DBUtils;

public class UploadDBUtils {
    private int chunkSize;
    private final String userSqlTemplate = "insert into users(full_name)";

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

}
