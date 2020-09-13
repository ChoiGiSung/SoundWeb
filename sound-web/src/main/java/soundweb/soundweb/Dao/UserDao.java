package soundweb.soundweb.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import soundweb.soundweb.Dto.UpdateUserDay;
import soundweb.soundweb.Dto.UserEntity;
import soundweb.soundweb.Repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao implements UserRepository {

    private Logger logger= LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    private DataSource dataSource;

    private SimpleJdbcInsert simpleJdbcInsert;


    @Override
    public List<UserEntity> findAll() {

        List<UserEntity> result = jdbcTemplate.query("select * from user", RowUSerDto());
        return result;
    }

    @Override
    public String addUser() {
        simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
        //simpleJdbcInsert=new SimpleJdbcInsert(dataSource);
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

    @Override
    public List<UserEntity> login(String User_id,String User_pwd) {
        List<UserEntity> findUser = jdbcTemplate.query("select * from user where user_id=? and user_pwd=?", RowUSerDto(), User_id,User_pwd);
        return findUser;
    }

    @Override
    public List<UserEntity> findData(String User_id) {
        List<UserEntity> findSeting = jdbcTemplate.query("select * from user where user_id=?",RowUSerDto(),User_id);

        //select user_seting from user where user_id=? 하면 not found라고 오류남 ?왜
        return findSeting;
    }

    //day업데이트
    @Override
    public String updateUserDay(String user_id,UpdateUserDay updateUserDay) {
        jdbcTemplate.update("update user set day_1=?,day_2=?,day_3=?,day_4=?,day_5=?,day_6=?,day_7=? where user_id =?"
                ,updateUserDay.getDay_1()
                ,updateUserDay.getDay_2()
                ,updateUserDay.getDay_3()
                ,updateUserDay.getDay_4()
                ,updateUserDay.getDay_5()
                ,updateUserDay.getDay_6()
                ,updateUserDay.getDay_7()
                ,user_id);

        return null;
    }

    //setting 업데이트
    @Override
    public String updateUserSetting(String user_id, String setting) {
        logger.info("세팅업데이트",setting);
        jdbcTemplate.update("update user set user_seting=? where user_id=?",setting,user_id);
        return null;
    }

    //회원 가입
    @Override
    public String join(String user_id, String user_pwd) {
        //보내온 아이디로 검색하고 있으면 유저있음
        //없으면 회원 가입 가능
        List<UserEntity> query = jdbcTemplate.query("select * from user where user_id=?", RowUSerDto(), user_id);
        if(!query.isEmpty()){
            
            if(query.get(0).getUser_id().equals(user_id)){
                return "이미있는회원";
            }
        }else{
            //simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplate) 이거로도 할 수 있지만 너무 길어진다
            jdbcTemplate.update("insert into user (user_id,user_pwd) value (?,?)",user_id,user_pwd);
            return "회원가입완료"; //회원 가입하기
        }

        return null;
    }




    public RowMapper<UserEntity> RowUSerDto(){
        return new RowMapper<UserEntity>() {
            @Override
            public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserEntity userDto=new UserEntity();
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
