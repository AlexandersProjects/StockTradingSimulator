package de.beispiele.StockTradingSimulatorV2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Utils {
    public static void summarizePortfolio(List<Stock> portfolio) {
        // Create a map to store the total quantity and total cost for each stock
        Map<String, Double> totalCost = new HashMap<>();
        Map<String, Integer> totalQuantity = new HashMap<>();

        // Iterate through the portfolio and calculate total quantity and total cost for each stock
        for (Stock stock : portfolio) {
            String symbol = stock.getSymbol();
            double price = stock.getPrice();

            totalQuantity.put(symbol, totalQuantity.getOrDefault(symbol, 0) + 1);
            totalCost.put(symbol, totalCost.getOrDefault(symbol, 0.0) + price);
        }

        // Print the summary table
        System.out.println("Portfolio:");

        // Print the summary table header
        System.out.printf("%-10s | %-10s | %-15s |%n", "Share", "Amount", "Average Price");
        System.out.println("-------------------------------------------");

        // Print the summary table rows
        for (Map.Entry<String, Double> entry : totalCost.entrySet()) {
            String symbol = entry.getKey();
            double total = entry.getValue();
            int quantity = totalQuantity.get(symbol);
            double averagePrice = total / quantity;
            System.out.printf("%-10s | %-10d | $%-14.2f |%n", symbol, quantity, averagePrice);
        }
    }
}