package com.mtm.test.mustafa.controller;


import com.mtm.test.mustafa.entity.DefCity;
import com.mtm.test.mustafa.exception.MtmException;
import com.mtm.test.mustafa.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apiCity")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //-----------------------------------------------------REPOSITORY
    @GetMapping("/cities")
    public Iterable<DefCity> cityList() {
        return cityService.cityList();
    }

    @PostMapping("/addCity")
    public String addCity(@RequestParam String name) {
        return cityService.addCity(name);
    }

    @PostMapping("/deleteCity{id}")
    public String deleteCity(@PathVariable Integer id) throws Exception {
        return cityService.deleteCity(id);
    }

    @PutMapping("/updateCity{id}")
    public String updateCity(@PathVariable Integer id, @RequestParam String name) throws Exception {
        return cityService.updateCity(id, name);
    }

    @GetMapping("infoCity{id}")
    public DefCity infoCity(@PathVariable Integer id) throws Exception {
        return cityService.infoCity(id);
    }

    //-----------------------------------------------------JdbcTemplate
    @GetMapping("citiesJdbcTemplate")
    public List<DefCity> cityListJdbcTemplate() {
        return cityService.userListJdbcTemplate();
    }

    @PostMapping("addCityJdbcTemplate")
    public String addCityJdbcTemplate(String name) {
        return cityService.addCityJdbcTemplate(name);
    }

    @PostMapping("deleteCityJdbcTemplate{id}")
    public String deleteCityJdbcTemplate(@PathVariable Integer id) {
        return cityService.deleteCityJdbcTemplate(id);
    }

    @PutMapping("updateCityJdbcTemplate{id}")
    public String updateCityJdbcTemplate(@PathVariable Integer id, @RequestParam String name) {
        return cityService.updateCityJdbcTemplate(id, name);
    }

    @GetMapping("infoCityJdbcTemplate{id}")
    public DefCity infoCityJdbcTemplate(@PathVariable Integer id) throws MtmException {
        return cityService.infoCityJdbcTemplate(id);
    }

    /*
     //-----------------------------------------------------JSONManuelCode
    @GetMapping("/JSONCode/ByJSONCodeCities")
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

    @GetMapping("/JSONCode/ByJSONCodeCityInfo{id}")
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
     */

}
