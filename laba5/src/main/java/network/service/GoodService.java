package network.service;

import network.dto.GoodDto;


public interface GoodService {
    GoodDto getGoodById(final String id);

    GoodDto createNewGood(final GoodDto goodDto);

    public void updateGoodById(final String id, final GoodDto goodDto);

    public void deleteGoodById(String id);
}
