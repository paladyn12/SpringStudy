package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach // 각 테스트 이후 수행
    void afterEach(){
        itemRepository.clear();
    }



    @Test
    void findAll(){
        Item item1 = new Item();
        Item item2 = new Item();

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();

        assertThat(all).contains(item1, item2);
    }

    @Test
    void updateItem(){
        Item item = new Item("itemA",1000,50);

        Item savedItem = itemRepository.save(item);

        Item updateParam = new Item("itemB", 1000, 50);
        itemRepository.update(savedItem.getId(), updateParam);

        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem.getItemName()).isEqualTo("itemB");

    }
}