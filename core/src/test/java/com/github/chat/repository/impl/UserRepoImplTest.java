package com.github.chat.repository.impl;

import com.github.chat.repository.UsersRepository;
import com.github.micro.orm.CustomJdbcTemplate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRepoImplTest {

    @Mock
    private CustomJdbcTemplate jdbcTemplate;

    @Mock
    private UsersRepository userRepo;

    @Before
    public void setup() {
    //    this.userRepo = new UserRepoImpl(this.jdbcTemplate);
    }

//    @Test
//    public void save() {
//        User exp = expectedInsertData();
//        String query = "Insert into users (first_name, last_name) values(?, ?)";
//        Mockito.when(this.jdbcTemplate.insert(
//                query,
//                UserRowMapper.getRowMapper(),
//                USER_INSERT_NICKNAME,
//                USER_INSERT_FIRST_NAME,
//                USER_INSERT_LAST_NAME,
//                USER_INSERT_EMAIL,
//                USER_INSERT_LOGIN,
//                USER_INSERT_PASSWORD,
//                USER_INSERT_PASSWORD,
//                USER_INSERT_PHONE
//        )).thenReturn(exp);
//        User act = this.userRepo.save(insertData());
//        Assert.assertEquals(exp, act);
//    }
}