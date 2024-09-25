package com.example.stock.mysql.service;

import com.example.stock.mysql.domain.Stock;
import com.example.stock.mysql.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PessimisticStockService {

    private final StockRepository stockRepository;

    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);

        stock.decrease(quantity);

        stockRepository.save(stock);
    }

}
