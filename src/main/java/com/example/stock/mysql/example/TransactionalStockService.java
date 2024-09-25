package com.example.stock.mysql.example;

import com.example.stock.mysql.service.StockService;

public class TransactionalStockService {

    private StockService stockService;

    public TransactionalStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) {
        startTransaction();
        stockService.decrease(id, quantity);
        // 이 사이에서 다른 쓰레드가 접근 가능하다.
        endTransaction();
    }

    private void startTransaction() {
        System.out.println("Transaction started");
    }

    private void endTransaction() {
        System.out.println("commit");
    }
}
