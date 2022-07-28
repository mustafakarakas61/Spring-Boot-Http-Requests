package com.mtm.test.mustafa.service;

import com.mtm.test.mustafa.entity.DefCity;
import com.mtm.test.mustafa.exception.MtmException;
import com.mtm.test.mustafa.repository.DefCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private DefCityRepository cityRepository;
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<DefCity> defCityBeanPropertyRowMapper = new BeanPropertyRowMapper<DefCity>(DefCity.class);

    @Autowired
    public CityService(DefCityRepository cityRepository, JdbcTemplate jdbcTemplate) {
        this.cityRepository = cityRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    //-----------------------------------------------------REPOSITORY
    public Iterable<DefCity> cityList() {
        Iterable<DefCity> data = this.cityRepository.findAll();
        return data;
    }

    public String addCity(String name) {
        DefCity defCity = new DefCity();
        defCity.setName(name);
        DefCity saved = cityRepository.save(defCity);
        return "Şehir eklendi";
    }

    public String deleteCity(Integer id) throws Exception {
        Optional<DefCity> data = cityRepository.findById(id);
        if (data.isPresent()) {
            cityRepository.deleteById(id);
            return "Şehir başarıyla silindi";
        } else {
            throw new MtmException("Şehir Bulunamadı !");
        }
    }

    public String updateCity(Integer id, String name) throws Exception {
        Optional<DefCity> data = this.cityRepository.findById(id);

        if (data.isPresent()) {
            data.get().setName(name);
            this.cityRepository.save(data.get());
            return "Şehir Güncellendi";
        } else {
            throw new MtmException(id + " id'sine karşılık var olan bir değer bulunamadı !");
        }
    }

    public DefCity infoCity(Integer id) throws Exception {
        Optional<DefCity> data = this.cityRepository.findById(id);
        if (data.isPresent()) {
            return data.get();
        } else {
            throw new MtmException("id bulunamadı");
        }
    }

    //-----------------------------------------------------JdbcTemplate
    public List<DefCity> userListJdbcTemplate() {
        String sql = "select * from def_city";
        List<DefCity> defCityList = jdbcTemplate.query(sql, defCityBeanPropertyRowMapper);
        return defCityList;
    }

    public String addCityJdbcTemplate(String name) {
        this.jdbcTemplate.update("INSERT INTO def_city (name) VALUES ('" + name + "')");
        return "Kayıt İşlemi Başarılı";
    }

    public String deleteCityJdbcTemplate(Integer id) {
        this.jdbcTemplate.update("delete from def_users where id =" + id);
        return "Silme İşlemi Başarılı";
    }

    public String updateCityJdbcTemplate(Integer id, String name) {
        this.jdbcTemplate.update("update from def_users set name = '" + name + "' where id = " + id);
        return "Update Çalıştı";
    }

    public DefCity infoCityJdbcTemplate(Integer id) throws MtmException {
        try {

            String sql = "from def_users where id =" + id;
            return (DefCity) this.jdbcTemplate.query(sql, defCityBeanPropertyRowMapper);
        } catch (Exception e) {
            throw new MtmException("id bulunamadı HATA:", e);
        }

    }
}

