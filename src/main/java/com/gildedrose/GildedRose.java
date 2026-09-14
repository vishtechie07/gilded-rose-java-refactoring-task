package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        // Sulfuras never changes, including its SellIn value.
        if (isSulfuras(item)) {
            return;
        }

        if (isAgedBrie(item)) {
            updateAgedBrie(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        } else if (isConjured(item)) {
            updateConjuredItem(item);
        } else {
            updateNormalItem(item);
        }

        item.sellIn--;
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item);

        // SellIn is reduced afterwards, so zero means the item expires today.
        if (item.sellIn <= 0) {
            decreaseQuality(item);
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item);

        if (item.sellIn <= 0) {
            increaseQuality(item);
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
            return;
        }

        increaseQuality(item);

        if (item.sellIn <= 10) {
            increaseQuality(item);
        }

        if (item.sellIn <= 5) {
            increaseQuality(item);
        }
    }

    private void updateConjuredItem(Item item) {
        // Conjured items degrade twice as fast as normal items.
        decreaseQuality(item);
        decreaseQuality(item);

        if (item.sellIn <= 0) {
            decreaseQuality(item);
            decreaseQuality(item);
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

    private boolean isConjured(Item item) {
        return item.name.startsWith(CONJURED);
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