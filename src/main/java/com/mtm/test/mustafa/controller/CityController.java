package com.mtm.test.mustafa.controller;



import com.mtm.test.mustafa.entity.DefCity;
import com.mtm.test.mustafa.exception.MtmException;
import com.mtm.test.mustafa.repository.DefCityRepository;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {
    private DefCityRepository cityRepository;

    private JdbcTemplate jdbcTemplate;

    public CityController(DefCityRepository cityRepository, JdbcTemplate jdbcTemplate) {
        this.cityRepository = cityRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/cities")
    public Iterable<DefCity> cityList() {

        Iterable<DefCity> data = this.cityRepository.findAll();

        return data;
    }

    @GetMapping("/addCity")
    public DefCity addNewCity(@RequestParam String name) {
        if(name !=null)
        {
            DefCity defCity = new DefCity();
            defCity.setName(name);

            DefCity saved =cityRepository.save(defCity);
            return saved;
        }

        return new DefCity();
    }
    @GetMapping("/deleteCity{id}")
    public String deleteCity(@PathVariable Integer id) throws Exception {
        Optional<DefCity> data = cityRepository.findById(id);
        if(data.isPresent())
        {
                cityRepository.deleteById(id);
                return "Şehir başarıyla silindi";

        }
        else {
            throw new MtmException("Şehir Bulunamadı !");
        }
    }

    @PutMapping("/updateCity{id}")
    public DefCity cityUpdate(@PathVariable Integer id, @RequestParam String name) throws Exception {

        Optional<DefCity> data = this.cityRepository.findById(id);

        if (data.isPresent()) {
            data.get().setName(name);
            this.cityRepository.save(data.get());
            return data.get();
        }
        else {
            throw new MtmException(id+" id'sine karşılık var olan bir değer bulunamadı !");
        }
    }

    //manuel updateCity
    @GetMapping(path = "/")
    public String homePage() {

        //Optional<DefCity> data = this.cityRepository.findById(1);

        //DefCity dat = this.cityRepository.findByNameAndId("İstanbul", 1);

        this.jdbcTemplate.update("update def_city set name = 'İstanbul 2' where id = 1");

        return "update calisti";

    }

    @GetMapping("/cityInfoJson")
    public String cityInfo() throws JSONException, ParseException {


        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();

        json.put("name", "Trabzon");
        json.put("Population", "19611461");
        json.put("cities", List.of("Beşikdüzü","Vakfıkebir","Tonya","Akçaabat"));

            json2.put("name", "İstanbul");
            json2.put("Population", "34343434");
            json2.put("cities", List.of("Bağcılar","Esenler","Fatih"));

            JSONArray response = new JSONArray();
            response.put(0,json);
            response.put(1,json2);

    return response.toString(4);
    }


    @GetMapping("/cityInfo{id}")
    public DefCity cityInfo(@PathVariable Integer id) throws Exception {
        Optional<DefCity> data = this.cityRepository.findById(id);

        if(data.isPresent()){
            return data.get();
        }else{
            throw new MtmException("id bulunamadı");
        }
    }

    @GetMapping("/cityInfoByJSONCode{id}")
    public String cityInfoByJSONCode(@PathVariable Integer id) throws Exception {
    Optional<DefCity> data = this.cityRepository.findById(id);

        if(data.isPresent()) {

            JSONObject json = new JSONObject();

            json.put("id", data.get().getId());
            json.put("name", data.get().getName());


            JSONArray response = new JSONArray();
            response.put(json);

            return response.toString(4);
        }else {
            throw new MtmException("Başarısız bir işlem !");
        }
    }


}
