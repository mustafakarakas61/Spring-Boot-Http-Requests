package com.mtm.test.mustafa.controller;


import com.mtm.test.mustafa.exception.MtmException;
import com.mtm.test.mustafa.service.CityService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/apiCity")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //-----------------------------------------------------REPOSITORY
    @GetMapping("/cities")
    public void cityList() {
        cityService.cityList();
    }

    @GetMapping("/addCity")
    public void addCity(@RequestParam String name) {
        cityService.addCity(name);
    }

    @GetMapping("/deleteCity{id}")
    public void deleteCity(@PathVariable Integer id) throws Exception {
        cityService.deleteCity(id);
    }

    @PutMapping("/updateCity{id}")
    public void updateCity(@PathVariable Integer id, @RequestParam String name) throws Exception {
        cityService.updateCity(id, name);
    }

    @GetMapping("infoCity{id}")
    public void infoCity(@PathVariable Integer id) throws Exception {
        cityService.infoCity(id);
    }

    //-----------------------------------------------------JdbcTemplate
    @GetMapping("citiesJdbcTemplate")
    public void cityListJdbcTemplate() {
        cityService.userListJdbcTemplate();
    }

    @PostMapping("addCityJdbcTemplate")
    public void addCityJdbcTemplate(String name) {
        cityService.addCityJdbcTemplate(name);
    }

    @PostMapping("deleteCityJdbcTemplate{id}")
    public void deleteCityJdbcTemplate(@PathVariable Integer id) {
        cityService.deleteCityJdbcTemplate(id);
    }

    @PutMapping("updateCityJdbcTemplate{id}")
    public void updateCityJdbcTemplate(@PathVariable Integer id, @RequestParam String name) {
        cityService.updateCityJdbcTemplate(id, name);
    }

    @GetMapping("infoCityJdbcTemplate{id}")
    public void infoCityJdbcTemplate(@PathVariable Integer id) throws MtmException {
        cityService.infoCityJdbcTemplate(id);
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
