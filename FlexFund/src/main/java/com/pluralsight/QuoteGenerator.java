package com.pluralsight;
import java.util.Random;

public class QuoteGenerator {
    private String[] funnyQuotes = {
            "Saving money is like going on a diet – painful but worth it!",
            "Budgeting: Because you can't have champagne taste on a ramen noodle wallet.",
            "FlexFund: Where cents turn into sense!",
            "Money talks... but all mine says is 'Goodbye!'",
            "Saving money feels like putting your snacks away for future you."
    };

    private String[] motivationalQuotes = {
            "Believe you can and you're halfway there.",
            "Don’t watch the clock; do what it does. Keep going.",
            "The secret of getting ahead is getting started.",
            "Success is not final, failure is not fatal: it is the courage to continue that counts.",
    };

    public String getRandomFunnyQuote() {
        Random random = new Random();
        int index = random.nextInt(funnyQuotes.length);
        return funnyQuotes[index];
    }

    public String getRandomMotivationalQuote() {
        Random random = new Random();
        int index = random.nextInt(motivationalQuotes.length);
        return motivationalQuotes[index];
    }
}
