package com.riosr.spring6restmvc.mappers;

import com.riosr.spring6restmvc.entities.Beer;
import com.riosr.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ricky
 * @created 2025-02-13
 */
@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);
    BeerDTO beerToBeerDTO(Beer beer);
}
