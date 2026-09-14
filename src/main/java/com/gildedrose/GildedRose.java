package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (!isAgedBrie(item)
                    && !isBackstagePass(item)) {

                if (!isSulfuras(item)) {
                    decreaseQuality(item);
                }

            } else {

                if (item.quality < 50) {
                    increaseQuality(item);

                    if (isBackstagePass(item)) {

                        if (item.sellIn < 11) {
                            increaseQuality(item);
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item);
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {

                if (!isAgedBrie(item)) {

                    if (!isBackstagePass(item)) {

                        if (!isSulfuras(item)) {
                            decreaseQuality(item);
                        }

                    } else {
                        item.quality = item.quality - item.quality;
                    }

                } else {
                    increaseQuality(item);
                }
            }
        }
    }

    private boolean isSulfuras(Item item) {
        return SULFURAS.equals(item.name);
    }

    private boolean isAgedBrie(Item item) {
        return AGED_BRIE.equals(item.name);
    }

    private boolean isBackstagePass(Item item) {
        return BACKSTAGE_PASS.equals(item.name);
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}