package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS =
            "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS =
            "Sulfuras, Hand of Ragnaros";

    @Test
    void normalItemDecreasesQualityByOne() {
        // Normal items lose 1 Quality each day before expiry.
        Item[] items = {
                new Item("Normal Item", 5, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    void expiredNormalItemDecreasesQualityByTwo() {
        // Expired normal items degrade twice as fast.
        Item[] items = {
                new Item("Normal Item", 0, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void normalItemQualityDoesNotGoBelowZero() {
        // Quality must never become negative.
        Item[] items = {
                new Item("Normal Item", 5, 0)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void expiredNormalItemQualityDoesNotGoBelowZero() {
        // Expired items may lose 2 Quality, but must still stop at zero.
        Item[] items = {
                new Item("Normal Item", 0, 1)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void agedBrieIncreasesQualityByOne() {
        // Aged Brie gains 1 Quality before expiry.
        Item[] items = {
                new Item(AGED_BRIE, 5, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    void expiredAgedBrieIncreasesQualityByTwo() {
        // Expired Aged Brie gains Quality twice as fast.
        Item[] items = {
                new Item(AGED_BRIE, 0, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void agedBrieQualityDoesNotExceedFifty() {
        // Aged Brie cannot increase beyond Quality 50.
        Item[] items = {
                new Item(AGED_BRIE, 5, 50)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void backstagePassIncreasesQualityByOneWhenMoreThanTenDaysRemain() {
        // Backstage passes gain 1 Quality when more than 10 days remain.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 15, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(14, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    void backstagePassIncreasesQualityByOneWhenElevenDaysRemain() {
        // Eleven days is still outside the 10-day bonus window.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 11, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(10, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    void backstagePassIncreasesQualityByTwoWhenTenDaysRemain() {
        // At 10 days remaining, Quality increases by 2.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 10, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void backstagePassIncreasesQualityByTwoWhenSixDaysRemain() {
        // Six days gets the +2 increase, but not yet the +3 increase.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 6, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void backstagePassIncreasesQualityByThreeWhenFiveDaysRemain() {
        // At five days remaining, Quality increases by 3.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 5, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(13, items[0].quality);
    }

    @Test
    void backstagePassQualityDropsToZeroAfterConcert() {
        // Once the concert has passed, the pass has no value.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 0, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void backstagePassQualityDoesNotExceedFifty() {
        // Backstage pass Quality must still stop at 50.
        Item[] items = {
                new Item(BACKSTAGE_PASS, 5, 49)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void sulfurasNeverChanges() {
        // Sulfuras keeps both its SellIn and legendary Quality of 80.
        Item[] items = {
                new Item(SULFURAS, 5, 80)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }
}