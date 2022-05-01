package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("item1",10000,100);
        Item saveItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1",10000,100);
        Item item2 = new Item("item2",20000,200);
        Item item3 = new Item("item3",30000,300);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        List<Item> all = itemRepository.findAll();

        all.forEach(item -> System.out.println("item = " + item));

        Assertions.assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void updateTest() {

        Item item1 = new Item("item1",10000,100);
        itemRepository.save(item1);

        Item updateItem = new Item("item3",20000,200);
        itemRepository.update(item1.getId(), updateItem);

        Item byId = itemRepository.findById(item1.getId());
        System.out.println("byId = " + byId);

        Assertions.assertThat(byId.getItemName()).isEqualTo("item3");
    }
}
