package soundweb.soundweb.Dao;

import org.apache.catalina.util.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import soundweb.soundweb.Dto.UserDto;
import soundweb.soundweb.Repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcInsert simpleJdbcInsert;


    @Override
    public List<UserDto> findAll() {

        List<UserDto> result = jdbcTemplate.query("select * from user", RowUSerDto());
        return result;
    }

    @Override
    public String addUser() {
        //simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert=new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.setTableName("user");

        Map<String,String> user=new HashMap<>();
        //샘플
        user.put("user_id","22");
        user.put("user_pwd","22");
        user.put("day_1","22");
        user.put("day_2","22");
        user.put("day_3","22");
        user.put("day_4","22");
        user.put("day_5","22");
        user.put("day_6","22");
        user.put("day_7","22");
        user.put("user_seting","22");

       // simpleJdbcInsert.execute(new MapSqlParameterSource(user));
        simpleJdbcInsert.execute(user);

        return "ok";
    }

    public RowMapper<UserDto> RowUSerDto(){
        return new RowMapper<UserDto>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto userDto=new UserDto();
                userDto.setUser_id(rs.getString("user_id"));
                userDto.setUser_pwd(rs.getString("user_pwd"));
                userDto.setDay_1(rs.getString("day_1"));
                userDto.setDay_2(rs.getString("day_2"));
                userDto.setDay_3(rs.getString("day_3"));
                userDto.setDay_4(rs.getString("day_4"));
                userDto.setDay_5(rs.getString("day_5"));
                userDto.setDay_6(rs.getString("day_6"));
                userDto.setDay_7(rs.getString("day_7"));
                userDto.setUser_seting(rs.getString("user_seting"));

                return userDto;
            }
        };
    }
}
