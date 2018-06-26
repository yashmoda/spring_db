package collegedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class DataDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Data getDatabyName(String name)
    {
        String query = "SELECT * FROM spring_test where name = ?";
        RowMapper<Data> mapper = new DataRowMapper();
        Data obj = jdbcTemplate.queryForObject(query, mapper, name);
        return obj;
    }

    public List<Data> getAllData()
    {
        String query = "SELECT * FROM spring_test";
        RowMapper<Data> mapper = new DataRowMapper();
        return jdbcTemplate.query(query, mapper);
    }

    public boolean check(String email, String phone)
    {
        String phone_query = "SELECT COUNT(*) FROM spring_test WHERE phone = ?";
        String email_query = "SELECT COUNT(*) FROM spring_test WHERE email = ?";
        int phone_count = jdbcTemplate.queryForObject(phone_query, Integer.class, phone);
        int email_count = jdbcTemplate.queryForObject(email_query, Integer.class, email);
        if (email_count != 0 || phone_count != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void insert(Data data)
    {
        boolean flag = check(data.getEmail(), data.getPhone());
        if (flag)
        {
            String query = "INSERT INTO spring_test (email, name, phone) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, data.getEmail(), data.getName(), data.getPhone());
        }
    }
}
