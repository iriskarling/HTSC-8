package test;

import dao.IAccountDao;
import dao.IRoleDao;
import dao.IUserDao;
import domain.AccountUser;
import domain.Role;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;
    private IAccountDao accountDao;
    private IRoleDao roleDao;
    @Before
    public void setUp() throws Exception {
        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.创建SqlSession工厂对象
        factory = builder.build(in);
        // 4.创建SqlSession对象
        session = factory.openSession(true);
        // 5.创建Dao的代理对象
        userDao = session.getMapper(IUserDao.class);
        accountDao = session.getMapper(IAccountDao.class);
        roleDao = session.getMapper(IRoleDao.class);

    }
    @Test
    public void testFindAll() {
    //6.执行操作
        List<AccountUser> accountusers = accountDao.findAll();
        for(AccountUser au : accountusers) {
            System.out.println(au);
        }
        Assert.assertEquals(3, accountusers.size());
    }

    @Test
    public void testFindAll2() {
        //6.执行操作
        List<User> users = accountDao.findAll2();
        for(User au : users) {
            System.out.println(au);
        }
        assert users.size() == 10;
    }
    @Test
    public void testFindAll3() {
        List<Role> roles = roleDao.findAll();
        Assert.assertEquals(3, roles.size());
        for(Role role : roles){
            System.out.println("---每个角色的信息----");
            System.out.println(role);
            System.out.println(role.getUsers()); }
    }
    @After
    public void tearDown() throws Exception {
        //session.commit();
        //7.释放资源
        session.close();
        in.close();
    }

}
