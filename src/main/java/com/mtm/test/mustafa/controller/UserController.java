package com.mtm.test.mustafa.controller;

import com.mtm.test.mustafa.entity.DefUsers;
import com.mtm.test.mustafa.exception.MtmException;
import com.mtm.test.mustafa.repository.DefUsersRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private BeanPropertyRowMapper<DefUsers> bprm= new BeanPropertyRowMapper<DefUsers>(DefUsers.class);
    private DefUsersRepository usersRepository;
    private JdbcTemplate jdbcTemplate;

    public UserController(DefUsersRepository usersRepository, JdbcTemplate jdbcTemplate)
    {
        this.usersRepository=usersRepository;
        this.jdbcTemplate=jdbcTemplate;
    }


    @GetMapping("/userList")
    public Iterable<DefUsers> usersList(){
        Iterable<DefUsers> data = usersRepository.findAll();
        return data;
    }

    @PostMapping("/addUserJdbcTemplate")
    public String userAddJdbcTemplate(@RequestParam String name, @RequestParam String pass){

        this.jdbcTemplate.update("INSERT INTO def_users (name, pass) VALUES ('" + name + "','" + pass + "')");
        return "Kayıt İşlemi Başarılı";
    }

    @PostMapping("/addUser")
    public DefUsers userAdd(@RequestParam String name, @RequestParam String pass){


          DefUsers defUsers = new DefUsers();
          defUsers.setName(name);
          defUsers.setPass(pass);

          DefUsers saved = usersRepository.save(defUsers);
    return saved;
      }



    @GetMapping("/userListJdbcTemplate")
    public List<DefUsers> userListJdbcTemplate(){
    String sql ="select * from def_users";
    List<DefUsers> usersList = jdbcTemplate.query(sql,bprm);
    return usersList;

    }

    @GetMapping("/userListCurdRepo")
    public Iterable<DefUsers> usersListCurd(){
        Iterable<DefUsers> data = usersRepository.findAll();
        return data;
    }
    //with JdbcTemplate
    @PutMapping("/updateUserJdbcTemplate{id}")
    public String updateUserJdbcTemplate(@PathVariable Integer id){
        this.jdbcTemplate.update("update def_users set name = 'subAdmin' where id = "+id);
        return "update calisti";
    }
    //with CurdRepo
    @PutMapping("/updateUserCurdRepo{id}")
    public DefUsers updateUser(@PathVariable Integer id,@RequestParam String name, @RequestParam String pass) throws Exception {
        Optional<DefUsers> data = this.usersRepository.findById(id);

            if (data.isPresent()) {
                data.get().setName(name);
                data.get().setPass(pass);
                this.usersRepository.save(data.get());
                return data.get();
            } else {    throw new MtmException();
            }


    }


    @PostMapping("/deleteUserJdbcTemplate{id}")
    public String deleteUserJdbcTemplate(@PathVariable Integer id)
    {
        this.jdbcTemplate.update("delete from def_users where id ="+id);
        return "Silme İşlemi Başarılı";
    }

    @PostMapping("/deleteUserCurdRepo{id}")
    public void deleteUser(@RequestParam Integer id) throws Exception {
        Optional<DefUsers> data = usersRepository.findById(id);
        if(data.isPresent()) {
            try {
                usersRepository.deleteById(id);
           // throw new MtmException("Silme İşlemi Başarılı");// Eğer ben buradaki kodu açarsam catch bloğu çalışacak

            } catch (Exception e) {
              throw new MtmException("Bu işlem yapılamadı");
            }
        }
        else {
//Burası çalışıyor -------------------------------------------------------<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            throw new MtmException("Bu id bulunamadı !");
        }
    }

}
