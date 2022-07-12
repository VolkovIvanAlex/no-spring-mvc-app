package network.service.implementation;

import java.sql.SQLException;
import java.util.Optional;

import network.dao.GoodDao;
import network.dao.implementation.DefaultGoodDao;
import network.dto.GoodDto;
import network.exception.ResponseErrorException;
import network.service.GoodService;


public class DefaultGoodService implements GoodService
{

    private GoodDao goodDao = new DefaultGoodDao();

    @Override
    public GoodDto getGoodById(final String stringId) {
        try {
            final int id = Integer.valueOf(stringId);
            Optional<GoodDto> goodDto = goodDao.getGoodById(id);
            if (goodDto.isPresent()) {
                return goodDto.get();
            } else {
                throw new ResponseErrorException(404, "No good with such id");
            }
        } catch (NumberFormatException e) {
            throw new ResponseErrorException(400, "ID is not an integer number");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseErrorException(500);
        }
    }

    @Override
    public GoodDto createNewGood(GoodDto goodDto) {
        return new GoodDto();
    }

    @Override
    public void updateGoodById(String id, GoodDto goodDto) {

    }

    @Override
    public void deleteGoodById(final String stringId) {
        try {
            final int id = Integer.valueOf(stringId);
            int rowCount = goodDao.deleteGoodById(id);
            if (rowCount != 1) {
                throw new ResponseErrorException(404, "No good with such id");
            }
        } catch (NumberFormatException e) {
            throw new ResponseErrorException(400, "ID is not an integer number");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseErrorException(500);
        }
    }

}
