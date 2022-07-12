package network.controller;

import network.annotation.PathParameter;
import network.annotation.RequestBody;
import network.annotation.RequestMapping;
import network.annotation.RequestMethod;
import network.annotation.RestController;
import network.dto.GoodDto;
import network.service.GoodService;
import network.service.implementation.DefaultGoodService;

@RestController
public class GoodController {

    private GoodService goodService = new DefaultGoodService();

    @RequestMapping(path = "/api/good/{id}", method = RequestMethod.GET)
    public GoodDto getGoodById(@PathParameter("id") String id) {
        return goodService.getGoodById(id);
    }

    @RequestMapping(path = "/api/good", method = RequestMethod.PUT)
    public GoodDto createNewGood(@RequestBody GoodDto goodDto) {
        return goodService.createNewGood(goodDto);
    }

    @RequestMapping(path = "/api/good/{id}", method = RequestMethod.POST)
    public void updateGoodById(@PathParameter("id") String id,
            @RequestBody GoodDto goodDto) {
        goodService.updateGoodById(id, goodDto);
    }

    @RequestMapping(path = "/api/good/{id}", method = RequestMethod.DELETE)
    public void deleteGoodById(@PathParameter("id") String id) {
        goodService.deleteGoodById(id);
    }
}
