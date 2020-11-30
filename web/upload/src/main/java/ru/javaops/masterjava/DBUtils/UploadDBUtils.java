package ru.javaops.masterjava.DBUtils;

import org.skife.jdbi.v2.Batch;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.model.User;

import java.util.List;

public class UploadDBUtils {
    private int chunkSize;
    private final String userSqlTemplate = "INSERT INTO users (full_name, email, flag) VALUES ('%s','%s','%s')";

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public UploadDBUtils(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void usersBatchInsert(List<User> entities){
        DBIProvider.initDBI();
        DBI dbi = DBIProvider.getDBI();
        Handle h = dbi.open();
        Batch batch = h.createBatch();

        for(User u : entities){
            String sql = String.format(userSqlTemplate,u.getFullName(),u.getEmail(),u.getFlag());
            batch.add(sql);
        }

        batch.execute();
        h.close();
    }
}
