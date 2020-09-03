package test;

import dao.IUserDao;
import domain.QueryVo;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void setUp() throws Exception {
        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.创建SqlSession工厂对象
        factory = builder.build(in);
        // 4.创建SqlSession对象
        session = factory.openSession();
        // 5.创建Dao的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void testFindOne() {
        // 6.执行操作
        User user = userDao.findById(41);
        System.out.println(user);
        Assert.assertEquals("张三",user.getUsername());
        //assert user.getUsername().equals("张三");
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("华泰");
        user.setAddress("南京市建邺区");
        user.setSex("男");
        user.setBirthday(new Date());
        // 1.执行保存方法
        int id = userDao.saveUser(user);
        int affectedRows = userDao.saveUser(user);
        // 2. 验证保存结果
        Assert.assertEquals(1,affectedRows);
        User savedUser = userDao.findById(id);
        Assert.assertEquals("华泰",user.getUsername());
        //assert user.getUsername().equals("华泰");
    }

    @Test
    public void testUpdateUser() {
        int id = 46;
        //1.根据id查询
        User user = userDao.findById(id);
        //2.更新操作
        user.setAddress("北京市顺义区");
        int res = userDao.updateUser(user);
        // 3. 验证保存结果
        User savedUser = userDao.findById(id);
        Assert.assertEquals("北京市顺义区",user.getAddress());
        //assert user.getAddress().equals("北京市顺义区");
        }
    @Test
    public void testDeleteUser() {
            // 1.执行操作
            int res = userDao.deleteUser(60);
            Assert.assertEquals(1,res);
            //assert res == 1;
    }
    @Test
    public void testFindByName() {
        // 1.执行查询一个方法
        List<User> users = userDao.findByName("%王%");
        Assert.assertEquals(2,users.size());
        //assert users.size() == 2;
        for(User user : users) {
            System.out.println(user); } }
    @Test
    public void testFindByNameNew() {
        // 1.执行查询一个方法
        List<User> users = userDao.findByNameNew("王");
        Assert.assertEquals(2,users.size());
        //assert users.size() == 2;
        for(User user : users){
            System.out.println(user); } }
    @Test
    public void testCount() {
        // 1.执行操作
        int res = userDao.count();
        Assert.assertEquals(8,res);
    }
    @Test
    public void testQueryByVo(){
        QueryVo vo = new QueryVo();
        vo.setName("%王%");
        vo.setAddress("%南京%");
        List<User> users = userDao.findByVo(vo);
        Assert.assertEquals(1,users.size());

    }
    @After
    public void tearDown() throws Exception {
        session.commit();
        //7.释放资源
        session.close();
        in.close();
    }



}