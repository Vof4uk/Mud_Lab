package ua.mykytenko.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.user.User;
import ua.mykytenko.entities.user.UserRole;
import ua.mykytenko.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile(Profiles.JDBC)
@Repository
public class JdbcUserRepositoryImpl implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @DependsOn("dataSource")
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User get(int id) {
        String sql = "SELECT users.id, users.name, user_roles.role FROM users " +
                "LEFT JOIN user_roles ON users.id = user_roles.user_id WHERE users.id =" + id + ";";
        Optional<User> user = mergeUserRoles(this.jdbcTemplate.query(
                sql,
                new userMapper()))
                .stream().findFirst();
        return user.isPresent()? user.get() : null;

    }

    @Override
    public User save(User user) {
        try {
            MapSqlParameterSource map = new MapSqlParameterSource()
                    .addValue("name", user.getName())
                    .addValue("id", user.getId());
            if (user.isNew()) {
                int id = simpleJdbcInsert.executeAndReturnKey(map).intValue();
                user.setId(id);
            }else {
                namedParameterJdbcTemplate.update("UPDATE users SET name=:name WHERE id=:id;", map);
                clearRoles(user.getId());
            }
            namedParameterJdbcTemplate.batchUpdate("INSERT INTO user_roles(user_id, role) VALUES(:user_id, :user_role);", getRolesBatch(user));
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean delete(int userId) {
        if(jdbcTemplate.update("DELETE FROM users WHERE id = ?", userId) > 0)
        {
            clearRoles(userId);
            return true;
        }
            return false;
    }

    @Override
    public Collection<User> getAll() {
        return mergeUserRoles(this.jdbcTemplate.query(
                "SELECT users.id, users.name, user_roles.role FROM users LEFT JOIN user_roles ON users.id = user_roles.user_id",
                new userMapper()));
    }

    private MapSqlParameterSource[] getRolesBatch(User user){
        return user.getRoles().stream()
                .map((r) -> ((new MapSqlParameterSource())
                        .addValue("user_id", user.getId())
                        .addValue("user_role", r.toString())))
                .toArray(MapSqlParameterSource[]::new);
    }

    private void clearRoles(int userId){
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id = ?", userId);
    }

    private Collection<User> mergeUserRoles(Collection<User> users){
        return users.stream().collect(HashMap<Integer, User>::new,
                (map, user) -> map.merge(user.getId(), user, (u1, u2) -> {
                    if(u1 == null || u2 == null) return u1 == null ? u2 : u1;
                    u1.getRoles().forEach(u2::addRole);
                    return u2;
                }), (a, b) -> {}).values();
    }

    private static class userMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            String s = rs.getString("role");
            user.addRole(s == null ? null : UserRole.valueOf(s));
            return user;
        }
    }
}
