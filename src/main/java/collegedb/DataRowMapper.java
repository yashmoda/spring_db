package collegedb;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRowMapper implements RowMapper<Data> {
    @Override
    public Data mapRow(ResultSet row, int rowNum) throws SQLException
    {
        Data obj = new Data();
        obj.setEmail(row.getString("email"));
        obj.setName(row.getString("name"));
        obj.setPhone(row.getString("phone"));
        return obj;
    }
}
