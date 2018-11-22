import com.base.sql.ModelRowMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:LiChong
 * @date:2018/11/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JDBCTest {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void test() {
		String sql = "select * from project where rownum < 5";
		List<Project> projects = jdbcTemplate.query(sql, new ModelRowMapper<Project>(Project.class));
		for (Project project : projects) {
			System.out.println(project);
		}
	}
}
